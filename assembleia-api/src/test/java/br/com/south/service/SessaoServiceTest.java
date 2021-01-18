package br.com.south.service;

import br.com.south.exception.SessaoException;
import br.com.south.helper.MessageHelper;
import br.com.south.repository.SessaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @InjectMocks
    private SessaoService refinancingCreateService;
    @Mock
    private MessageHelper messageHelper;
    @Mock
    private SessaoRepository sessaoRepository;

    @Test
    void sessaoNaoEncontrada() {
        final Long idSessao = 150L;
        final String message = "Sessão não encontrada";

        Mockito.when(this.messageHelper.get(MessageHelper.ErrorCode.ERROR_SESSAO_NAO_ENCONTRADA))
                .thenReturn(message);
        Mockito.when(this.sessaoRepository.findById(idSessao))
                .thenReturn(Optional.empty());

        var error = Assertions.assertThrows(SessaoException.class, () -> this.refinancingCreateService.obterSessao(idSessao));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
    }
}
