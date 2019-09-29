package com.lyazgincompany.domain.repository

import com.lyazgincompany.domain.Rate
import io.reactivex.Observable

interface IConverterRepository {
    fun getRates(baseCurrency: String): Observable<List<Rate>>
}
