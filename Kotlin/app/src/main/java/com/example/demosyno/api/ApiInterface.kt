package com.example.demosyno.api

import com.example.demosyno.model.Country
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("countries")
    fun getDataFromAPI() : Call<Country>
}