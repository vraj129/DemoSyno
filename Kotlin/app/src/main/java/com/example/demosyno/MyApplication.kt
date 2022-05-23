package com.example.demosyno

import android.app.Application
import com.example.demosyno.api.ApiInterface
import com.example.demosyno.api.ApiUtilities
import com.example.demosyno.repository.CountriesRepository
import com.example.demosyno.room.CountryDatabase

class MyApplication : Application() {

    lateinit var countriesRepository: CountriesRepository
    override fun onCreate() {
        super.onCreate()
        val apiInterface = ApiUtilities.getRetroInstance().create(ApiInterface::class.java)
        var database = CountryDatabase.getDatabase(applicationContext)
        countriesRepository = CountriesRepository(apiInterface,database,applicationContext)
    }
}