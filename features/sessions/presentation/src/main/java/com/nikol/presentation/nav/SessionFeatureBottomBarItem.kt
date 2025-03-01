package com.nikol.presentation.nav

import com.nikol.navigation.BottomBarItem
import com.nikol.presentation.R

class SessionFeatureBottomBarItem : BottomBarItem {
    override val navigationRoute: String = SessionsFeatureScreens.NAVIGATION_ROUTE
    override val nameId: Int = R.string.sessions
    override val iconId: Int = R.drawable.session_icon
}