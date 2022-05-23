package com.example.demosyno.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class CountryInfo(
    val flag: String,
)

class TypeConverterCountryInfo {
    val gson : Gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): CountryInfo? {
        if (data == null) return null
        var listType: Type = object : TypeToken<CountryInfo?>() {}.type
        return gson.fromJson<CountryInfo?>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObject: CountryInfo?): String? {
        return gson.toJson(someObject)
    }
}