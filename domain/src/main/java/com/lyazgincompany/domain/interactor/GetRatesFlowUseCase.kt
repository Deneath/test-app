package com.lyazgincompany.domain.interactor

import com.lyazgincompany.domain.DEFAULT_CURRENCY
import com.lyazgincompany.domain.DEFAULT_RATE_VALUE
import com.lyazgincompany.domain.Rate
import com.lyazgincompany.domain.repository.IConverterRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class GetRatesFlowUseCase(private val repository: IConverterRepository){

    var currentRate: Rate = Rate(DEFAULT_CURRENCY, DEFAULT_RATE_VALUE)

    fun execute(): Observable<List<Rate>> {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
            .flatMap { repository.getRates(currentRate.currency) }
            .subscribeOn(Schedulers.io())
    }
}