package com.nikol.presentation.di

import com.nikol.navigation.BottomBarItem
import com.nikol.navigation.FeatureApi
import com.nikol.presentation.navigation.AuthFeatureBottomBarItem
import com.nikol.presentation.navigation.AuthFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object AuthFeatureModule {
    @Provides
    @IntoSet
    fun provideMainFeatureNavigationApi(): FeatureApi = AuthFeatureImpl()


    @Provides
    @IntoSet
    fun provideMainFeatureBottomBarItem(): BottomBarItem = AuthFeatureBottomBarItem()
}