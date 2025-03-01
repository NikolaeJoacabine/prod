package com.nikol.data.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nikol.data.remote.models.Movie
import com.nikol.data.remote.network.LibraryApi
import com.nikol.data.remote.repository.RemoteAuthFeatureRepository
import com.nikol.data.remote.repository.RemoteLibraryRepository
import com.nikol.data.remote.repository.RemoteLibraryRepositoryImpl
import com.nikol.data.repository.MainFeatureRepositoryImpl
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.repository.MainFeatureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MainFeatureModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): LibraryApi {
        return retrofit.create(LibraryApi::class.java)

    }

    @Provides
    @Singleton
    fun provideRemoteAuthFeatureRepository(
        libraryApi: LibraryApi,
        remoteAuthFeatureRepository: AuthFeatureRepository
    ): RemoteLibraryRepository {
        return RemoteLibraryRepositoryImpl(libraryApi,remoteAuthFeatureRepository)
    }

    @Provides
    @Singleton
    fun provideMainFeatureRepository(
        remoteAuthFeatureRepository: RemoteLibraryRepository
    ):  MainFeatureRepository{
        return MainFeatureRepositoryImpl(remoteAuthFeatureRepository)
    }
}