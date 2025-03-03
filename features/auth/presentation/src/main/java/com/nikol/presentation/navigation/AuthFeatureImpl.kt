package com.nikol.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nikol.navigation.FeatureApi
import com.nikol.presentation.screens.profile.ProfileScreen
import javax.inject.Inject


class AuthFeatureImpl @Inject constructor() : FeatureApi {

    override val navigationRoute = AuthFeatureScreens.NAVIGATION_ROUTE
    override val startDestination = AuthFeatureScreens.startScreenRoute

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
                route = AuthFeatureScreens.ProfileScreen.route
            ) {
               ProfileScreen(navController)
            }
        }
    }
}