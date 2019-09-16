package com.lyazgincompany.test.api

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class RatesResponse(

    @SerializedName("base")
    val base: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("rates")
    val ratesSchema: RatesSchema)

@JsonAdapter(RatesDeserializer::class)
class RatesSchema(
    val rates: Map<String, Double>
)