package com.lyazgincompany.test.api

import com.lyazgincompany.test.AppConstants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesApi {

    @GET(AppConstants.REQUEST_RATES_URL)
    fun getRates(@Query("base") baseCurrency: String): Observable<RatesResponse>
}
