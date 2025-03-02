package com.nikol.data.di;

import com.nikol.data.remote.network.SessionApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

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
public final class SessionFeatureModule_ProvideAuthApiFactory implements Factory<SessionApi> {
  private final SessionFeatureModule module;

  private final Provider<Retrofit> retrofitProvider;

  public SessionFeatureModule_ProvideAuthApiFactory(SessionFeatureModule module,
      Provider<Retrofit> retrofitProvider) {
    this.module = module;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public SessionApi get() {
    return provideAuthApi(module, retrofitProvider.get());
  }

  public static SessionFeatureModule_ProvideAuthApiFactory create(SessionFeatureModule module,
      javax.inject.Provider<Retrofit> retrofitProvider) {
    return new SessionFeatureModule_ProvideAuthApiFactory(module, Providers.asDaggerProvider(retrofitProvider));
  }

  public static SessionFeatureModule_ProvideAuthApiFactory create(SessionFeatureModule module,
      Provider<Retrofit> retrofitProvider) {
    return new SessionFeatureModule_ProvideAuthApiFactory(module, retrofitProvider);
  }

  public static SessionApi provideAuthApi(SessionFeatureModule instance, Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(instance.provideAuthApi(retrofit));
  }
}
