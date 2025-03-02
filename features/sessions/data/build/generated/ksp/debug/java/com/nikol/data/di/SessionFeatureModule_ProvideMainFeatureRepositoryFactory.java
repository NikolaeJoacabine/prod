package com.nikol.data.di;

import com.nikol.data.remote.repository.RemoteSessionRepository;
import com.nikol.domain.repository.SessionFeatureRepository;
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
public final class SessionFeatureModule_ProvideMainFeatureRepositoryFactory implements Factory<SessionFeatureRepository> {
  private final SessionFeatureModule module;

  private final Provider<RemoteSessionRepository> remoteAuthFeatureRepositoryProvider;

  public SessionFeatureModule_ProvideMainFeatureRepositoryFactory(SessionFeatureModule module,
      Provider<RemoteSessionRepository> remoteAuthFeatureRepositoryProvider) {
    this.module = module;
    this.remoteAuthFeatureRepositoryProvider = remoteAuthFeatureRepositoryProvider;
  }

  @Override
  public SessionFeatureRepository get() {
    return provideMainFeatureRepository(module, remoteAuthFeatureRepositoryProvider.get());
  }

  public static SessionFeatureModule_ProvideMainFeatureRepositoryFactory create(
      SessionFeatureModule module,
      javax.inject.Provider<RemoteSessionRepository> remoteAuthFeatureRepositoryProvider) {
    return new SessionFeatureModule_ProvideMainFeatureRepositoryFactory(module, Providers.asDaggerProvider(remoteAuthFeatureRepositoryProvider));
  }

  public static SessionFeatureModule_ProvideMainFeatureRepositoryFactory create(
      SessionFeatureModule module,
      Provider<RemoteSessionRepository> remoteAuthFeatureRepositoryProvider) {
    return new SessionFeatureModule_ProvideMainFeatureRepositoryFactory(module, remoteAuthFeatureRepositoryProvider);
  }

  public static SessionFeatureRepository provideMainFeatureRepository(SessionFeatureModule instance,
      RemoteSessionRepository remoteAuthFeatureRepository) {
    return Preconditions.checkNotNullFromProvides(instance.provideMainFeatureRepository(remoteAuthFeatureRepository));
  }
}
