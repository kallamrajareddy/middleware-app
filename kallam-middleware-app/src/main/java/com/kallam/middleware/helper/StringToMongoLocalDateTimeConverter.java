package com.kallam.middleware.helper;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class StringToMongoLocalDateTimeConverter implements Converter<String, MongoLocalDateTime> {

    private static final TypeDescriptor SOURCE = TypeDescriptor.valueOf(String.class);
    private static final TypeDescriptor TARGET = TypeDescriptor.valueOf(MongoLocalDateTime.class);

    @Override
    public MongoLocalDateTime convert(String source) {
        try {
            return MongoLocalDateTime.of(LocalDateTime.parse(source));
        } catch (DateTimeParseException ex) {
            throw new ConversionFailedException(SOURCE, TARGET, source, ex);
        }
    }
}
