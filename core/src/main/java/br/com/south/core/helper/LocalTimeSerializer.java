package br.com.south.core.helper;

import br.com.south.core.exception.SerializerException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static br.com.south.core.helper.MessageHelper.ErrorCode.ERROR_SERIALIZER_DATA;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@NoArgsConstructor
@Component
public class LocalTimeSerializer extends JsonSerializer<LocalTime> {

    @Autowired
    private MessageHelper messageHelper;

    @Override
    public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            gen.writeString(DateTimeFormatter.ISO_LOCAL_TIME.format(value));
        } catch (Exception ex) {
            log.error(this.messageHelper.get(ERROR_SERIALIZER_DATA), ex);
            throw new SerializerException(BAD_REQUEST, this.messageHelper.get(ERROR_SERIALIZER_DATA));
        }
    }
}
