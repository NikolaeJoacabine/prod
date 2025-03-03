package com.nikol.presentation.screens.di

import com.nikol.domain.repository.ProfileFeatureRepository
import com.nikol.domain.use_case.ExitUseCase
import com.nikol.domain.use_case.GetProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ProfileModule {

    @Provides
    fun provideGetUserProfileUseCase(profileFeatureRepository: ProfileFeatureRepository): GetProfileUseCase =
        GetProfileUseCase(profileFeatureRepository)

    @Provides
    fun provideExitUseCase(profileFeatureRepository: ProfileFeatureRepository): ExitUseCase =
        ExitUseCase(profileFeatureRepository)
}