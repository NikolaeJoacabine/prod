package com.nikol.presentation.nav

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.nikol.domain.model.Movie
import com.nikol.domain.model.MovieSession
import com.nikol.navigation.FeatureApi
import com.nikol.presentation.screens.add.SessionScreen
import com.nikol.presentation.screens.detail.DetailScreen
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
                SessionScreen(navController)
            }
            composable(
                route = LibraryFeatureScreens.DetailScreen.route,
                arguments = listOf(
                    navArgument("movieJson") { type = NavType.StringType }
                ),
                enterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth -> fullWidth },
                        animationSpec = tween(durationMillis = 500)
                    ) + fadeIn(animationSpec = tween(durationMillis = 500))
                },
                exitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth -> fullWidth },
                        animationSpec = tween(durationMillis = 500)
                    ) + fadeOut(animationSpec = tween(durationMillis = 500))
                },
                popEnterTransition = {
                    slideInHorizontally(
                        initialOffsetX = { fullWidth -> -fullWidth },
                        animationSpec = tween(durationMillis = 500)
                    ) + fadeIn(animationSpec = tween(durationMillis = 500))
                },
                popExitTransition = {
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth -> fullWidth },
                        animationSpec = tween(durationMillis = 500)
                    ) + fadeOut(animationSpec = tween(durationMillis = 500))
                }
            ) { backStackEntry ->
                val movieJson = backStackEntry.arguments?.getString("movieJson")
                val movie = try {
                    LibraryFeatureScreens.DetailScreen.parseArguments(movieJson)
                } catch (e: Exception) {
                    Movie(0, "Error", description = "", imageUrl = "")
                }
                DetailScreen(navController, movie)
            }
        }
    }
}