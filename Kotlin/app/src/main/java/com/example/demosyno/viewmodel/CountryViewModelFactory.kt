package com.example.demosyno.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demosyno.repository.CountriesRepository

class CountryViewModelFactory(private val countryRepository: CountriesRepository):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryViewModel(countryRepository) as T
    }

}