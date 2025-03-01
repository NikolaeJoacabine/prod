package com.nikol.presentation.nav

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nikol.navigation.FeatureApi
import com.nikol.presentation.screens.add.SessionScreen
import javax.inject.Inject


class SessionFeatureImpl @Inject constructor() : FeatureApi {

    override val navigationRoute = SessionsFeatureScreens.NAVIGATION_ROUTE
    override val startDestination = SessionsFeatureScreens.startScreenRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = startDestination,
            route = navigationRoute
        ) {
            composable(
                route = SessionsFeatureScreens.SessionsScreen.route
            ) {
//                Text("Session")
                SessionScreen()
            }
        }
    }
}