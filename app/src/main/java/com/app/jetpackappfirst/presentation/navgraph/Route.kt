package com.app.jetpackappfirst.presentation.navgraph

sealed class Route(
    val route: String
) {

    data object OnBoardingScreen: Route(route = "OnBoardingScreen")
    data object HomeScreen: Route(route = "homeScreen")
    data object Searchscreen: Route(route = "searchScreen")
    data object BookmarkScreen: Route(route = "bookmarkScreen")
    data object DetailsScreen: Route(route = "detailScreen")
    data object AppStartNavigation: Route(route = "appStartNavigation")
    data object NewsNavigation: Route(route = "newsNavigation")
    data object NewsNavigatorScreen: Route(route = "newsNavigator")

}