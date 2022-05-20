package kz.kamran.androidfinalexam.presentation.country_page.state

import kz.kamran.androidfinalexam.data.model.Report

sealed class CountryState {
    object Empty: CountryState()
    object Loading: CountryState()
    data class Error(val message:String): CountryState()
    data class Success(val report:Report): CountryState()
}