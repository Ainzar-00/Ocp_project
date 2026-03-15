package com.ocp.evalformation.data.repository;

import com.ocp.evalformation.data.local.OcpDatabase;
import com.ocp.evalformation.data.remote.FirebaseRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "cast"
})
public final class MainRepository_Factory implements Factory<MainRepository> {
  private final Provider<OcpDatabase> dbProvider;

  private final Provider<FirebaseRepository> firebaseProvider;

  public MainRepository_Factory(Provider<OcpDatabase> dbProvider,
      Provider<FirebaseRepository> firebaseProvider) {
    this.dbProvider = dbProvider;
    this.firebaseProvider = firebaseProvider;
  }

  @Override
  public MainRepository get() {
    return newInstance(dbProvider.get(), firebaseProvider.get());
  }

  public static MainRepository_Factory create(Provider<OcpDatabase> dbProvider,
      Provider<FirebaseRepository> firebaseProvider) {
    return new MainRepository_Factory(dbProvider, firebaseProvider);
  }

  public static MainRepository newInstance(OcpDatabase db, FirebaseRepository firebase) {
    return new MainRepository(db, firebase);
  }
}
