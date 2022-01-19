package ec.edu.espe.arqsoftware.prueba_ii_parcial.utilities;

import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime> {
    @Override
    public ZonedDateTime convert(Date date) {
        return date.toInstant().atZone(ZoneId.of("America/Guayaquil"));
    }
}
