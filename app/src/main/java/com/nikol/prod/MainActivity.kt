package com.nikol.prod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

