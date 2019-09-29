package com.lyazgincompany.test.data.entity.mapper

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.lyazgincompany.test.data.entity.RatesSchema
import java.lang.reflect.Type

class RatesDeserializer : JsonDeserializer<RatesSchema> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RatesSchema {
        val jsonObject = json!!.asJsonObject

        val valuesMap = hashMapOf<String, Double>()

        jsonObject.keySet().forEach {
            valuesMap[it] = jsonObject.get(it).asDouble
        }

        return RatesSchema(valuesMap)
    }
}
