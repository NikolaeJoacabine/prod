package com.nikol.presentation.screens.di

import com.nikol.domain.repository.MainFeatureRepository
import com.nikol.domain.use_cases.AddMovieUseCase
import com.nikol.domain.use_cases.DeleteMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LibraryScreenModule {

    @Provides
    fun provideAddMovieUseCase(mainFeatureRepository: MainFeatureRepository): AddMovieUseCase =
        AddMovieUseCase(mainFeatureRepository)

    @Provides
    fun provideSearchUseCase(mainFeatureRepository: MainFeatureRepository): AddMovieUseCase =
        AddMovieUseCase(mainFeatureRepository)

    @Provides
    fun provideDeleteMovieUseCase(mainFeatureRepository: MainFeatureRepository): DeleteMovieUseCase =
        DeleteMovieUseCase(mainFeatureRepository)

}
