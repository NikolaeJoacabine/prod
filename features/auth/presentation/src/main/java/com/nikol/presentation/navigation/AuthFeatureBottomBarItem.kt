package com.nikol.presentation.navigation

import com.nikol.navigation.BottomBarItem
import com.nikol.presentation.R

class AuthFeatureBottomBarItem : BottomBarItem {
    override val navigationRoute: String = AuthFeatureScreens.NAVIGATION_ROUTE
    override val nameId: Int = R.string.profile
    override val iconId: Int = R.drawable.account_circle
}