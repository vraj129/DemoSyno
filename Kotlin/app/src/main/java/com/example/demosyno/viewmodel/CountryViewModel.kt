package com.example.demosyno.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demosyno.model.Country
import com.example.demosyno.model.CountryItem
import com.example.demosyno.repository.CountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel(private val countryRepository : CountriesRepository) : ViewModel() {
    fun getAllRecord():LiveData<MutableList<CountryItem>>{
        return countryRepository.getAllRecords()
    }
     fun makeCall(){
        countryRepository.getCountries()
    }
}