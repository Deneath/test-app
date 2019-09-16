package com.lyazgincompany.test.features.converter

import com.lyazgincompany.test.AppConstants
import com.lyazgincompany.test.api.RatesApi
import com.lyazgincompany.test.api.RatesMapper
import com.lyazgincompany.test.applySchedulers
import com.lyazgincompany.test.data.Rate
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

typealias RatesList = List<Rate>

class ConverterRepository @Inject constructor(
    private val ratesApi: RatesApi
) :
    IConverterRepository {

    override var currentRate = Rate(AppConstants.DEFAULT_CURRENCY, AppConstants.DEFAULT_RATE_VALUE)

    override fun getRatesFlowObservable(): Observable<RatesList> {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
            .flatMap { ratesApi.getRates(currentRate.currency) }
            .map { RatesMapper.map(it) }
            .applySchedulers()
    }
}

