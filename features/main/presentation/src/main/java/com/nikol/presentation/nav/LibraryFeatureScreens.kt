package com.nikol.presentation.nav

sealed class LibraryFeatureScreens(val route: String) {
    data object LibraryScreen : LibraryFeatureScreens(route = "library_screen")
    data object AddScreen : LibraryFeatureScreens(route = "add_screen")
    data object DetailScreen : LibraryFeatureScreens(route = "detail_screen")

    companion object {
        const val NAVIGATION_ROUTE = "library_feature_navigation"
        val startScreenRoute = LibraryScreen.route
    }
}