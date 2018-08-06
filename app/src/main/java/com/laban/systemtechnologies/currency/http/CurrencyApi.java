package com.laban.systemtechnologies.currency.http;

import io.reactivex.Single;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;

public interface CurrencyApi {

    @GET("Services/XmlExRates.aspx")
    Single<Result<CurrencyResponse>> getCurrencyList();

    @GET("Services/NotFound")
    Single<Result<CurrencyResponse>> getNotFoundException();

    @GET("API/ExRates/Rates/USD?ParamMode=2")
    Single<Result<CurrencyResponse>> getContentException();

}
