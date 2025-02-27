package com.nikol.prod.di.authSatate

import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.use_case.GetCurrentUserUseCase
import com.nikol.domain.use_case.LoginUseCase
import com.nikol.domain.use_case.LogoutUseCase
import com.nikol.domain.use_case.SignupUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(ViewModelComponent::class)
class AuthModule {

    @Provides
    fun provideGetCurrentUserUseCase(authFeatureRepository: AuthFeatureRepository): GetCurrentUserUseCase {
        return GetCurrentUserUseCase(authFeatureRepository)
    }

    @Provides
    fun provideLoginUseCase(authFeatureRepository: AuthFeatureRepository): LoginUseCase {
        return LoginUseCase(authFeatureRepository)
    }

    @Provides
    fun provideLogoutUseCase(authFeatureRepository: AuthFeatureRepository): LogoutUseCase {
        return LogoutUseCase(authFeatureRepository)
    }

    @Provides
    fun provideSignupUseCase(authFeatureRepository: AuthFeatureRepository): SignupUseCase {
        return SignupUseCase(authFeatureRepository)
    }
}