package com.nikol.prod.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nikol.navigation.FeatureApi
import com.nikol.navigation.register
import com.nikol.prod.LoginScreen
import com.nikol.prod.RegisterScreen

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
