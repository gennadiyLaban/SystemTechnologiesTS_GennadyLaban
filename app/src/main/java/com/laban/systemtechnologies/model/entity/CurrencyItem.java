package com.laban.systemtechnologies.model.entity;

import java.math.BigDecimal;

public class CurrencyItem {
    private String name;
    private String rateCharCode;
    private BigDecimal rate;
    private int scale;
    private String scaleCharCode;

    public CurrencyItem() {
    }

    public String getRateCharCode() {
        return rateCharCode;
    }

    public void setRateCharCode(String rateCharCode) {
        this.rateCharCode = rateCharCode;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getScaleCharCode() {
        return scaleCharCode;
    }

    public void setScaleCharCode(String scaleCharCode) {
        this.scaleCharCode = scaleCharCode;
    }
}
