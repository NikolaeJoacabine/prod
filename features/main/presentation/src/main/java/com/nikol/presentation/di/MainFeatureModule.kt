package com.nikol.presentation.di

import com.nikol.navigation.BottomBarItem
import com.nikol.navigation.FeatureApi
import com.nikol.presentation.nav.LibraryFeatureBottomBarItem
import com.nikol.presentation.nav.LibraryFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object MainFeatureModule {
    @Provides
    @IntoSet
    fun provideMainFeatureNavigationApi(): FeatureApi = LibraryFeatureImpl()


    @Provides
    @IntoSet
    fun provideMainFeatureBottomBarItem(): BottomBarItem = LibraryFeatureBottomBarItem()
}