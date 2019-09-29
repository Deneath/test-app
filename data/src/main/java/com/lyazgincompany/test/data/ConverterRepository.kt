package com.lyazgincompany.test.data

import com.lyazgincompany.domain.Rate
import com.lyazgincompany.domain.repository.IConverterRepository
import com.lyazgincompany.test.data.api.RatesApi
import com.lyazgincompany.test.data.entity.mapper.RatesMapper
import io.reactivex.Observable
import javax.inject.Inject

class ConverterRepository @Inject constructor(
    private val ratesApi: RatesApi
) :
    IConverterRepository {
    override fun getRates(baseCurrency: String): Observable<List<Rate>> {
        return ratesApi.getRates(baseCurrency)
            .map { RatesMapper.map(it) }
    }
}

