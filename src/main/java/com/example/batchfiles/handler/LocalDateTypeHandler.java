package com.example.batchfiles.handler;

import org.beanio.types.TypeHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTypeHandler implements TypeHandler {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy");

    @Override
    public Object parse(String text) {
        return LocalDate.parse(text, format);
    }

    @Override
    public String format(Object value) {
        return ((LocalDate) value).format(format);
    }

    @Override
    public Class<?> getType() {
        return LocalDate.class;
    }

}
