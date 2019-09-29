package com.lyazgincompany.test.data.api

import com.lyazgincompany.test.data.REQUEST_RATES_URL
import com.lyazgincompany.test.data.entity.RatesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApi {

    @GET(REQUEST_RATES_URL)
    fun getRates(@Query("base") baseCurrency: String): Observable<RatesResponse>
}
