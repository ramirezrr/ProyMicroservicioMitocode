package com.mitocode.licencias.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mitocode.licencias.exception.ErrorGenericException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomDateDeserializer
        extends StdDeserializer<LocalDate> {

    private static DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CustomDateDeserializer() {
        this(null);
    }

    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDate deserialize(
            JsonParser jsonparser, DeserializationContext context)
            throws IOException {

        String date = jsonparser.getText();
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Error en el formato de la fecha, debe ser YYYY-MM-DD");
        }
    }
}