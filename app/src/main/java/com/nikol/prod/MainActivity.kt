package com.nikol.prod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.nikol.navigation.BottomBarItem
import com.nikol.navigation.FeatureApi
import com.nikol.prod.ui.theme.ProdTheme

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var bottomBarItems: List<@JvmSuppressWildcards BottomBarItem>

    @Inject
    lateinit var featureNavigationApis: List<@JvmSuppressWildcards FeatureApi>

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            ProdTheme {
                AppContent(
                    bottomBarItems = bottomBarItems.toList()
                        .sortedBy { it.navigationRoute },
                    featureNavigationApis = featureNavigationApis.toList()
                        .sortedBy { it.navigationRoute }
                )
            }
        }
    }
}

