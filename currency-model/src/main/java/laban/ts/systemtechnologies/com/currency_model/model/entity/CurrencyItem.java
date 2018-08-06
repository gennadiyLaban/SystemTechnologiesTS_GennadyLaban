package laban.ts.systemtechnologies.com.currency_model.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyItem item = (CurrencyItem) o;
        return scale == item.scale &&
                Objects.equals(name, item.name) &&
                Objects.equals(rateCharCode, item.rateCharCode) &&
                Objects.equals(rate, item.rate) &&
                Objects.equals(scaleCharCode, item.scaleCharCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, rateCharCode, rate, scale, scaleCharCode);
    }
}
