package com.nikol.presentation.nav

import com.nikol.navigation.BottomBarItem
import com.nikol.presentation.R

class LibraryFeatureBottomBarItem : BottomBarItem {
    override val navigationRoute: String = LibraryFeatureScreens.NAVIGATION_ROUTE
    override val nameId: Int = R.string.library
    override val iconId: Int = R.drawable.list
}