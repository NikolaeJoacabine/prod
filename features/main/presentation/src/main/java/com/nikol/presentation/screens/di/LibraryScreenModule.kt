package com.nikol.presentation.screens.di

import com.nikol.domain.repository.MainFeatureRepository
import com.nikol.domain.use_cases.AddInWatchedUseCase
import com.nikol.domain.use_cases.AddMovieUseCase
import com.nikol.domain.use_cases.AddNewMovieUseCase
import com.nikol.domain.use_cases.DeleteMovieUseCase
import com.nikol.domain.use_cases.GetFilmUseCase
import com.nikol.domain.use_cases.GetTopicsUseCase
import com.nikol.domain.use_cases.InspectLibraryUseCase
import com.nikol.domain.use_cases.SearchFilmsWithApiUseCase
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
    fun provideSearchUseCase(mainFeatureRepository: MainFeatureRepository): InspectLibraryUseCase =
        InspectLibraryUseCase(mainFeatureRepository)

    @Provides
    fun provideDeleteMovieUseCase(mainFeatureRepository: MainFeatureRepository): DeleteMovieUseCase =
        DeleteMovieUseCase(mainFeatureRepository)

    @Provides
    fun provideAddNewMovieUseCase(mainFeatureRepository: MainFeatureRepository): AddNewMovieUseCase =
        AddNewMovieUseCase(mainFeatureRepository)

    @Provides
    fun provideSearchFilmsWithApiUseCase(mainFeatureRepository: MainFeatureRepository): SearchFilmsWithApiUseCase =
        SearchFilmsWithApiUseCase(mainFeatureRepository)

    @Provides
    fun provideGetFilmDetail(mainFeatureRepository: MainFeatureRepository): GetFilmUseCase =
        GetFilmUseCase(mainFeatureRepository)

    @Provides
    fun provideAddInWatched(mainFeatureRepository: MainFeatureRepository): AddInWatchedUseCase =
        AddInWatchedUseCase(mainFeatureRepository)

    @Provides
    fun getTopics(mainFeatureRepository: MainFeatureRepository): GetTopicsUseCase =
        GetTopicsUseCase(mainFeatureRepository)
}
