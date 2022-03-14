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
            id : Int,
            adult : Boolean
        ) : String{
            return "${this.navigation}/$title/$id/$overview/$rate/$date/$adult/$url"
        }
    }
    object MovieNowPlayingDetail : Screen("MOVIE_NOWPLAYING_SCREEN"){
        fun navigateDetailMovieWithArgs(
            url : String,
            title : String,
            rate : String,
            date : String,
            overview : String,
            id : Int,
            adult : Boolean
        ) : String{
            return "${this.navigation}/$title/$id/$overview/$rate/$date/$adult/$url"
        }
    }
    object MovieSavedDetail : Screen("MOVIE_SAVED_SCREEN"){
        fun navigateDetailMovieWithArgs(
            url : String,
            title : String,
            rate : String,
            date : String,
            overview : String,
            id : Int,
            adult : Boolean
        ) : String{
            return "${this.navigation}/$title/$id/$overview/$rate/$date/$adult/$url"
        }
    }
    object TvShowDetail : Screen("TVSHOW_SCREEN"){
        fun navigateDetailTvShowWithArgs(
            url : String,
            title : String,
            rate : String,
            date : String,
            overview : String,
            id : Int
        ) : String{
            return "${this.navigation}/$title/$id/$overview/$rate/$date/$url"
        }
    }
    object TvShowTopRatedDetail : Screen("TVSHOW_TOPRATED_SCREEN"){
        fun navigateDetailTvShowWithArgs(
            url : String,
            title : String,
            rate : String,
            date : String,
            overview : String,
            id : Int
        ) : String{
            return "${this.navigation}/$title/$id/$overview/$rate/$date/$url"
        }
    }
    object TvShowSavedDetail : Screen("TVSHOW_SAVED_SCREEN"){
        fun navigateDetailTvShowWithArgs(
            url : String,
            title : String,
            rate : String,
            date : String,
            overview : String,
            id : Int
        ) : String{
            return "${this.navigation}/$title/$id/$overview/$rate/$date/$url"
        }
    }
    object MovieSearched : Screen("MOVIE_SEARCHED_SCREEN"){
        fun navigateDetailMovieWithArgs(
            url : String,
            title : String,
            rate : String,
            date : String,
            overview : String,
            id : Int,
            adult : Boolean
        ) : String{
            return "${this.navigation}/$title/$id/$overview/$rate/$date/$adult/$url"
        }
    }
    object TvShowSearched : Screen("TVSHOW_SEARCHED_SCREEN"){
        fun navigateDetailTvShowWithArgs(
            url : String,
            title : String,
            rate : String,
            date : String,
            overview : String,
            id : Int
        ) : String{
            return "${this.navigation}/$title/$id/$overview/$rate/$date/$url"
        }
    }
}
