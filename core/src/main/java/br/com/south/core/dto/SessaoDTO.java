package br.com.south.core.dto;


import br.com.south.core.helper.LocalDateTimeSerializer;
import br.com.south.core.helper.LocalTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Value
@With
@JsonDeserialize(builder = SessaoDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class SessaoDTO {

    String pauta;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime inicio;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @ApiModelProperty(example = "10:10:10", value = "HH:mm:ss")
    LocalTime duracao;
    Long idSessao;
    Boolean encerrada;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
