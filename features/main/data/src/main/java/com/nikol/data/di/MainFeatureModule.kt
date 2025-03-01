package com.nikol.data.di

import com.nikol.data.remote.network.LibraryApi
import com.nikol.data.remote.repository.RemoteLibraryRepository
import com.nikol.data.remote.repository.RemoteLibraryRepositoryImpl
import com.nikol.data.repository.MainFeatureRepositoryImpl
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.repository.MainFeatureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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