package br.com.south.batch.dto;

import br.com.south.core.helper.LocalDateTimeSerializer;
import br.com.south.core.helper.LocalTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.lang.Boolean.FALSE;

@Value
@With
@JsonDeserialize(builder = SessaoEncerradaDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class SessaoEncerradaDTO {

    private Long id;
    private String pauta;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dataInicio;
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime duracao;
    private Boolean encerrada = FALSE;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
