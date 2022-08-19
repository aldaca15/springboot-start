package com.test.springboot.entity;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter {
        //extends XmlAdapter<String, LocalDate> {
    // Not useful or implemented code. Tested for marshalling and un marshalling json but only works on xml
    /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, formatter);
    }

    public String marshal(LocalDate v) throws Exception {
        return v.format(formatter);
    }*/
}
