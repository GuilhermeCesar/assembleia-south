package br.com.south.batch;

import br.com.south.batch.config.AmqpConfig;
import br.com.south.batch.dto.SessaoEncerradaDTO;
import br.com.south.core.entity.Sessao;
import br.com.south.core.repository.SessaoRepository;
import br.com.south.core.service.SessaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;

import static br.com.south.core.helper.MessageHelper.ErrorCode.ERROR_SESSAO_FINALIZADA;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@Slf4j
@Component
public class BatchExecutor {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final SessaoRepository sessaoRepository;
    private final AmqpConfig amqpConfig;
    private final RabbitTemplate rabbitTemplate;
    private final SessaoService sessaoService;

    @Scheduled(cron = "${batch.cron}")
    public void reportCurrentTime() {
        try{
            log.info("Rodou às {}", dateFormat.format(new Date()));
            Page<Sessao> sessaoPage;
            var page = 0;
            do {
                sessaoPage = this.sessaoRepository.findByEncerrada(Boolean.FALSE, PageRequest.of(page, 30));

                if(!sessaoPage.hasContent()){
                    break;
                }

                var sessoes= sessaoPage.getContent()
                        .parallelStream()
                        .filter(this::sessaoEncerrada)
                        .peek(sessao -> sessao.setEncerrada(Boolean.TRUE))
                        .peek(this::mandarMensagem)
                        .collect(Collectors.toList());

                this.sessaoRepository.saveAll(sessoes);
                ++page;
            } while (!sessaoPage.isLast());
        }catch (Exception ex){
            log.error("Falha no processamento do batch", ex);
        }
    }

    private void mandarMensagem(Sessao sessao) {
        log.debug("Mandou mensagem");
        this.rabbitTemplate.convertAndSend(this.amqpConfig.getSessaoExchange(),
                org.apache.logging.log4j.util.Strings.EMPTY,
                SessaoEncerradaDTO.builder()
                        .dataInicio(sessao.getDataInicio())
                        .duracao(sessao.getDuracao())
                        .pauta(sessao.getPauta())
                        .build()
        );
    }

    public Boolean sessaoEncerrada(Sessao sessao){
        return LocalDateTime.now().isAfter(sessao.getFinalSessao());
    }
}
