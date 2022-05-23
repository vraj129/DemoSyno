package com.example.demosyno.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demosyno.api.ApiInterface
import com.example.demosyno.model.Country
import com.example.demosyno.model.CountryItem
import com.example.demosyno.room.CountryDatabase
import com.example.demosyno.util.NetworkUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesRepository(
    private val apiInterface: ApiInterface,
    private val countryDatabase: CountryDatabase,
    private val applicationContext: Context
) {
    private val countryLiveData = MutableLiveData<Country>()

    val country : LiveData<Country>
    get() = countryLiveData

    fun getAllRecords() : LiveData<MutableList<CountryItem>> {
        return countryDatabase.getRoomDao().getAllRecords()
    }
    fun getCountries(){
        if(NetworkUtil.isInternetAvailable(applicationContext)){
            val call : Call<Country> = apiInterface.getDataFromAPI()
            call.enqueue(object :Callback<Country>{
                override fun onResponse(call: Call<Country>, response: Response<Country>) {
                    if(response.isSuccessful){
                        countryDatabase.getRoomDao().deleteAllRecords()
                        response.body()?.forEach {
                            Log.d("Country Data",it.toString())
                            countryDatabase.getRoomDao().insertRecords(it)
                        }
                    }
                }
                override fun onFailure(call: Call<Country>, t: Throwable) {

                }

            })
        }
        else {
            getAllRecords()
        }
   }
}