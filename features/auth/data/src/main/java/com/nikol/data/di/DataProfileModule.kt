package com.nikol.data.di

import com.nikol.data.remote.network.UserApi
import com.nikol.data.remote.repository.RemoteProfileFeatureRepository
import com.nikol.data.remote.repository.RemoteProfileFeatureRepositoryImpl
import com.nikol.data.repository.ProfileFeatureRepositoryImpl
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.repository.ProfileFeatureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataProfileModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteAuthFeatureRepository(
        userApi: UserApi,
        remoteAuthFeatureRepository: AuthFeatureRepository
    ): RemoteProfileFeatureRepository {
        return RemoteProfileFeatureRepositoryImpl(userApi, remoteAuthFeatureRepository)
    }

    @Provides
    @Singleton
    fun provideMainFeatureRepository(
        remoteProfileFeatureRepository: RemoteProfileFeatureRepository,
        remoteAuthFeatureRepository: AuthFeatureRepository
    ): ProfileFeatureRepository {
        return ProfileFeatureRepositoryImpl(remoteProfileFeatureRepository, remoteAuthFeatureRepository)
    }
}