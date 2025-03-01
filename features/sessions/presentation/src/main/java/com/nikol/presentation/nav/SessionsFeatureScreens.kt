package com.nikol.presentation.nav

sealed class SessionsFeatureScreens(val route: String) {
    data object SessionsScreen : SessionsFeatureScreens(route = "library_screen")

    companion object {
        const val NAVIGATION_ROUTE = "library_feature_navigation"
        val startScreenRoute = SessionsScreen.route
    }
}