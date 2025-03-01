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
    fun provideAddUserIntoSessionUseCase(mainFeatureRepository: SessionFeatureRepository): AddUserIntoSessionUseCase =
        AddUserIntoSessionUseCase(mainFeatureRepository)

    @Provides
    fun provideGetLikedMoviesUseCase(mainFeatureRepository: SessionFeatureRepository): GetLikedMoviesUseCase =
        GetLikedMoviesUseCase(mainFeatureRepository)

    @Provides
    fun provideGetCommonGenresUseCase(mainFeatureRepository: SessionFeatureRepository): GetCommonGenresUseCase =
        GetCommonGenresUseCase(mainFeatureRepository)

}