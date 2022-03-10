package com.example.compose_movie.ui.component

sealed class NavigateTo(val navigation : String){
    class Home(navigation: String = "HOME_SCREEN") : NavigateTo(navigation)
    class Favourite(navigation: String = "FAVOURITE_SCREEN") : NavigateTo(navigation)
    class Search(navigation: String = "SEARCH_SCREEN") : NavigateTo(navigation)
}
