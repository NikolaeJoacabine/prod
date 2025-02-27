package com.nikol.prod.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nikol.navigation.FeatureApi
import com.nikol.navigation.register

@Composable
fun AppNavGraph(
    navController: NavHostController,
    featureNavigationApis: List<FeatureApi>,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = featureNavigationApis.first().navigationRoute,
        modifier = modifier
    ) {
        featureNavigationApis.forEach { featureNavigationApi ->
            register(
                featureApi = featureNavigationApi,
                navController = navController,
                modifier = modifier
            )
        }
    }
}
