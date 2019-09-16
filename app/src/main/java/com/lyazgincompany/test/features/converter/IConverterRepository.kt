package com.lyazgincompany.test.features.converter

import com.lyazgincompany.test.data.Rate
import io.reactivex.Observable

interface IConverterRepository {
    var currentRate: Rate
    fun getRatesFlowObservable(): Observable<RatesList>
}
