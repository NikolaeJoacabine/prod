package com.nikol.presentation.nav

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nikol.navigation.FeatureApi
import com.nikol.presentation.screens.add.AddScreen
import com.nikol.presentation.screens.library.LibraryScreen
import javax.inject.Inject


class LibraryFeatureImpl @Inject constructor() : FeatureApi {

    override val navigationRoute = LibraryFeatureScreens.NAVIGATION_ROUTE
    override val startDestination = LibraryFeatureScreens.startScreenRoute

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
                route = LibraryFeatureScreens.LibraryScreen.route
            ) {
                LibraryScreen(navController)
            }

            composable(
                route = LibraryFeatureScreens.AddScreen.route
            ) {
                AddScreen(navController)
            }
        }
    }
}