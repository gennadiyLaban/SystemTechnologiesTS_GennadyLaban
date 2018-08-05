package com.laban.systemtechnologies.currency.http;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "DailyExRates", strict = false)
public class CurrencyResponse {

    @ElementList(inline = true)
    private List<CurrencyCourse> courses;

    public List<CurrencyCourse> getCourses() {
        return courses;
    }

}
