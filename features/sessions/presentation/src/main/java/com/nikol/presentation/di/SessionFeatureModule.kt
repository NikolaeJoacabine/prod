package com.nikol.presentation.di

import com.nikol.navigation.BottomBarItem
import com.nikol.navigation.FeatureApi
import com.nikol.presentation.nav.SessionFeatureBottomBarItem
import com.nikol.presentation.nav.SessionFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object SessionFeatureModule {
    @Provides
    @IntoSet
    fun provideSessionFeatureNavigationApi(): FeatureApi = SessionFeatureImpl()


    @Provides
    @IntoSet
    fun provideSessionFeatureBottomBarItem(): BottomBarItem = SessionFeatureBottomBarItem()
}