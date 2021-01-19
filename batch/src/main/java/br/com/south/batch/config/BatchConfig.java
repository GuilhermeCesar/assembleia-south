package br.com.south.batch.config;

import br.com.south.core.ModuloRepository;
import br.com.south.core.helper.MessageHelper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({ModuloRepository.class})
@Configuration
public class BatchConfig {

    @Bean
    public MessageHelper messageHelper(MessageSource messageSource) {
        return new MessageHelper(messageSource);
    }
}
