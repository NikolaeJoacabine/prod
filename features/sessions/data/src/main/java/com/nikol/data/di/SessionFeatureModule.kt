package com.nikol.data.di

import com.nikol.data.remote.network.SessionApi
import com.nikol.data.remote.repository.RemoteSessionRepository
import com.nikol.data.remote.repository.RemoteSessionRepositoryImpl
import com.nikol.data.repository.SessionFeatureRepositoryImpl
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.repository.SessionFeatureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SessionFeatureModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): SessionApi {
        return retrofit.create(SessionApi::class.java)

    }

    @Provides
    @Singleton
    fun provideRemoteAuthFeatureRepository(
        libraryApi: SessionApi,
        remoteAuthFeatureRepository: AuthFeatureRepository
    ): RemoteSessionRepository {
        return RemoteSessionRepositoryImpl(libraryApi,remoteAuthFeatureRepository)
    }

    @Provides
    @Singleton
    fun provideMainFeatureRepository(
        remoteAuthFeatureRepository: RemoteSessionRepository
    ):  SessionFeatureRepository{
        return SessionFeatureRepositoryImpl(remoteAuthFeatureRepository)
    }
}