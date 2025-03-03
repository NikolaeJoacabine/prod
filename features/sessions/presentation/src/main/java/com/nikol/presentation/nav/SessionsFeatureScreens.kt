package com.nikol.presentation.nav

import android.net.Uri
import com.nikol.domain.model.Movie
import com.nikol.domain.model.MovieSession
import org.json.JSONObject

sealed class SessionsFeatureScreens(val route: String) {
    data object SessionsScreen : SessionsFeatureScreens(route = "sessions_screen")
    companion object {
        const val NAVIGATION_ROUTE = "sessions_feature_navigation"
        val startScreenRoute = SessionsScreen.route
    }


}