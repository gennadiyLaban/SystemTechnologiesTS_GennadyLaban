package com.laban.systemtechnologies.http;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

@Root(name = "Currency", strict = false)
public class CurrencyCourse {
    @Element(name = "CharCode")
    private String charCode;
    @Element(name = "Scale")
    private int scale;
    @Element(name = "Name")
    private String name;
    @Element(name = "Rate")
    private BigDecimal rate;


    public String getCharCode() {
        return charCode;
    }

    public int getScale() {
        return scale;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
