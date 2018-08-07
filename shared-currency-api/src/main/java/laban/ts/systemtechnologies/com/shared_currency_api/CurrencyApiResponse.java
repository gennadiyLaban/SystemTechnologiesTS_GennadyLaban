package laban.ts.systemtechnologies.com.shared_currency_api;

import java.util.List;

import laban.ts.systemtechnologies.com.currency_model.model.entity.CurrencyItem;

public class CurrencyApiResponse {
    private List<CurrencyItem> currencyItems;
    private String stateMessage;

    public CurrencyApiResponse(List<CurrencyItem> currencyItems, String stateMessage) {
        this.currencyItems = currencyItems;
        this.stateMessage = stateMessage;
    }

    public List<CurrencyItem> getCurrencyItems() {
        return currencyItems;
    }

    public String getStateMessage() {
        return stateMessage;
    }
}
