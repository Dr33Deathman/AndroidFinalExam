package kz.kamran.androidfinalexam.presentation.home_page.state

import kz.kamran.androidfinalexam.data.model.Country

sealed class HomeState {
    object Empty: HomeState()
    object Loading:HomeState()
    data class Error(val message:String): HomeState()
    data class Success(val countries:List<Country>):HomeState()
}