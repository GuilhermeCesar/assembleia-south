package br.com.south.core.service;

import br.com.south.core.dto.CadastrarSessaoDTO;
import br.com.south.core.dto.SessaoDTO;
import br.com.south.core.entity.Sessao;
import br.com.south.core.exception.SessaoException;
import br.com.south.core.helper.MessageHelper;
import br.com.south.core.repository.SessaoRepository;
import br.com.south.core.repository.SessaoSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final MessageHelper messageHelper;

    public SessaoDTO criarSessao(final CadastrarSessaoDTO cadastrarSessaoDTO) {
        try {
            final var sessao = Sessao
                    .builder()
                    .dataInicio(cadastrarSessaoDTO.getInicio())
                    .duracao(cadastrarSessaoDTO.getDuracao())
                    .pauta(cadastrarSessaoDTO.getPauta())
                    .build();

            this.sessaoRepository.save(sessao);

            return getSessaoDTO(sessao);
        } catch (Exception ex) {
            throw new SessaoException(INTERNAL_SERVER_ERROR, this.messageHelper.get(MessageHelper.ErrorCode.ERROR_SESSAO));
        }
    }

    private SessaoDTO getSessaoDTO(Sessao sessao) {
        return SessaoDTO
                .builder()
                .idSessao(sessao.getId())
                .inicio(sessao.getDataInicio())
                .duracao(sessao.getDuracao())
                .pauta(sessao.getPauta())
                .encerrada(sessao.getEncerrada())
                .build();
    }

    public SessaoDTO obterSessao(Long idSessao) {
        final var sessao = this.sessaoRepository.findById(idSessao)
                .orElseThrow(() -> new SessaoException(NOT_FOUND, this.messageHelper.get(MessageHelper.ErrorCode.ERROR_SESSAO_NAO_ENCONTRADA)));

        return getSessaoDTO(sessao);
    }

    public Page<SessaoDTO> buscarSessao(String pauta, Boolean encerrada, Pageable pageable) {
        final var sessaoSpecification = SessaoSpecification
                .builder()
                .pauta(ofNullable(pauta))
                .encerrado(ofNullable(encerrada))
                .build();

        final var sessaoPage = this.sessaoRepository.findAll(sessaoSpecification, pageable);

        return sessaoPage.map(this::getSessaoDTO);
    }




}
