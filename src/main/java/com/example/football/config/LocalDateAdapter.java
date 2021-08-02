package com.example.football.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String s) throws Exception {
        LocalDate parsedLocalDate = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return parsedLocalDate;
//        return LocalDate.parse(s);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.toString();
    }
}
