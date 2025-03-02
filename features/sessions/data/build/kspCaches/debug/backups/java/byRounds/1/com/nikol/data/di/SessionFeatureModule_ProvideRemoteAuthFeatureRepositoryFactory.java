package com.nikol.data.di;

import com.nikol.data.remote.network.SessionApi;
import com.nikol.data.remote.repository.RemoteSessionRepository;
import com.nikol.domain.repository.AuthFeatureRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class SessionFeatureModule_ProvideRemoteAuthFeatureRepositoryFactory implements Factory<RemoteSessionRepository> {
  private final SessionFeatureModule module;

  private final Provider<SessionApi> libraryApiProvider;

  private final Provider<AuthFeatureRepository> remoteAuthFeatureRepositoryProvider;

  public SessionFeatureModule_ProvideRemoteAuthFeatureRepositoryFactory(SessionFeatureModule module,
      Provider<SessionApi> libraryApiProvider,
      Provider<AuthFeatureRepository> remoteAuthFeatureRepositoryProvider) {
    this.module = module;
    this.libraryApiProvider = libraryApiProvider;
    this.remoteAuthFeatureRepositoryProvider = remoteAuthFeatureRepositoryProvider;
  }

  @Override
  public RemoteSessionRepository get() {
    return provideRemoteAuthFeatureRepository(module, libraryApiProvider.get(), remoteAuthFeatureRepositoryProvider.get());
  }

  public static SessionFeatureModule_ProvideRemoteAuthFeatureRepositoryFactory create(
      SessionFeatureModule module, javax.inject.Provider<SessionApi> libraryApiProvider,
      javax.inject.Provider<AuthFeatureRepository> remoteAuthFeatureRepositoryProvider) {
    return new SessionFeatureModule_ProvideRemoteAuthFeatureRepositoryFactory(module, Providers.asDaggerProvider(libraryApiProvider), Providers.asDaggerProvider(remoteAuthFeatureRepositoryProvider));
  }

  public static SessionFeatureModule_ProvideRemoteAuthFeatureRepositoryFactory create(
      SessionFeatureModule module, Provider<SessionApi> libraryApiProvider,
      Provider<AuthFeatureRepository> remoteAuthFeatureRepositoryProvider) {
    return new SessionFeatureModule_ProvideRemoteAuthFeatureRepositoryFactory(module, libraryApiProvider, remoteAuthFeatureRepositoryProvider);
  }

  public static RemoteSessionRepository provideRemoteAuthFeatureRepository(
      SessionFeatureModule instance, SessionApi libraryApi,
      AuthFeatureRepository remoteAuthFeatureRepository) {
    return Preconditions.checkNotNullFromProvides(instance.provideRemoteAuthFeatureRepository(libraryApi, remoteAuthFeatureRepository));
  }
}
