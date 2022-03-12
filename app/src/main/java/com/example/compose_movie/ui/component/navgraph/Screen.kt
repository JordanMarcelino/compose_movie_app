package com.example.compose_movie.ui.component.navgraph


sealed class Screen(val navigation : String){
    object Home : Screen("HOME_SCREEN")
    object Search : Screen("SEARCH_SCREEN")
    object Favourite : Screen("FAVOURITE_SCREEN")
    object MovieDetail : Screen("MOVIE_SCREEN"){
        fun navigateDetailMovieWithArgs(
            url : String,
            title : String,
            rate : String,
            date : String,
            overview : String,
            adult : Boolean
        ) : String{
            return "${this.navigation}/$title/$overview/$rate/$date/$adult/$url"
        }
    }
}
