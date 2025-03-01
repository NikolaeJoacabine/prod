package com.nikol.presentation.screens.di

import com.nikol.domain.repository.SessionFeatureRepository
import com.nikol.domain.use_cases.AddUserIntoSessionUseCase
import com.nikol.domain.use_cases.GetCommonGenresUseCase
import com.nikol.domain.use_cases.GetLikedMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SessionScreenModule {

    @Provides
    fun provideAddUserIntoSessionUseCase(sessionFeatureRepository: SessionFeatureRepository): AddUserIntoSessionUseCase =
        AddUserIntoSessionUseCase(sessionFeatureRepository)

    @Provides
    fun provideGetLikedMoviesUseCase(sessionFeatureRepository: SessionFeatureRepository): GetLikedMoviesUseCase =
        GetLikedMoviesUseCase(sessionFeatureRepository)

    @Provides
    fun provideGetCommonGenresUseCase(sessionFeatureRepository: SessionFeatureRepository): GetCommonGenresUseCase =
        GetCommonGenresUseCase(sessionFeatureRepository)

}