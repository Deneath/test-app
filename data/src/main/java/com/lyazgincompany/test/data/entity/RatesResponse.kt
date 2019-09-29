package com.lyazgincompany.test.data.entity

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.lyazgincompany.test.data.entity.mapper.RatesDeserializer

data class RatesResponse(

    @SerializedName("base")
    val base: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("rates")
    val ratesSchema: RatesSchema
)

@JsonAdapter(RatesDeserializer::class)
class RatesSchema(
    val rates: Map<String, Double>
)