package com.spicecrispies.core.logging;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeXmlAdapter extends XmlAdapter<String, LocalDateTime> {

    public String marshal(LocalDateTime value) throws Exception {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return value.toString();

    }

    public LocalDateTime unmarshal(String value) throws Exception {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(value);

    }

}