package com.lyazgincompany.test.data.entity.mapper

import com.lyazgincompany.domain.Rate
import com.lyazgincompany.test.data.entity.RatesResponse

object RatesMapper{
    fun map(response: RatesResponse): List<Rate> {
        return response.ratesSchema.rates.map {
            Rate(
                it.key,
                it.value
            )
        }
    }
}
