package com.nikol.data.di

import android.content.Context
import com.nikol.data.local.repository.LocalAuthFeaturesRepository
import com.nikol.data.local.repository.LocalAuthFeaturesRepositoryImpl
import com.nikol.data.local.storage.TokenStorage
import com.nikol.data.local.storage.UserStorage
import com.nikol.data.remote.network.AuthApi
import com.nikol.data.remote.repository.RemoteAuthFeatureRepository
import com.nikol.data.remote.repository.RemoteAuthFeatureRepositoryImpl
import com.nikol.data.reppository.AuthFeatureRepositoryImpl
import com.nikol.domain.repository.AuthFeatureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthFeatureDataModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserStorage(@ApplicationContext context: Context): UserStorage {
        return UserStorage(context)
    }

    @Provides
    @Singleton
    fun provideTokenStorage(@ApplicationContext context: Context): TokenStorage {
        return TokenStorage((context))
    }

    @Provides
    @Singleton
    fun provideLocalAuthFeatureRepository(userStorage: UserStorage): LocalAuthFeaturesRepository {
        return LocalAuthFeaturesRepositoryImpl(userStorage)
    }

    @Provides
    @Singleton
    fun provideRemoteAuthFeatureRepository(authApi: AuthApi): RemoteAuthFeatureRepository {
        return RemoteAuthFeatureRepositoryImpl(authApi)
    }

    @Provides
    @Singleton
    fun provideAuthFeatureRepository(
        remoteAuthFeatureRepository: RemoteAuthFeatureRepository,
        localAuthFeaturesRepository: LocalAuthFeaturesRepository,
        tokenStorage: TokenStorage
    ): AuthFeatureRepository {
        return AuthFeatureRepositoryImpl(
            remoteAuthFeatureRepository,
            localAuthFeaturesRepository,
            tokenStorage
        )
    }
}
