package com.lyazgincompany.test.api

import com.lyazgincompany.test.data.Rate
import com.lyazgincompany.test.features.converter.RatesList

object RatesMapper{
    fun map(response: RatesResponse): RatesList {
        return response.ratesSchema.rates.map { Rate(it.key, it.value) }
    }
}
