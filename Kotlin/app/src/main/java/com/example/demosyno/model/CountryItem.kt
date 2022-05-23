package com.example.demosyno.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryItem(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")val id:Int = 0,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "countryInfo") val countryInfo: CountryInfo,
)