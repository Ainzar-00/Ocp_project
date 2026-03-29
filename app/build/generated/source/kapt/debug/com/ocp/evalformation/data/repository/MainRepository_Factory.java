package com.ocp.evalformation.data.repository;

import android.content.Context;
import com.ocp.evalformation.data.local.OcpDatabase;
import com.ocp.evalformation.data.remote.FirebaseRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
  private final Provider<Context> contextProvider;

  private final Provider<OcpDatabase> dbProvider;

  private final Provider<FirebaseRepository> firebaseProvider;

  public MainRepository_Factory(Provider<Context> contextProvider, Provider<OcpDatabase> dbProvider,
      Provider<FirebaseRepository> firebaseProvider) {
    this.contextProvider = contextProvider;
    this.dbProvider = dbProvider;
    this.firebaseProvider = firebaseProvider;
  }

  @Override
  public MainRepository get() {
    return newInstance(contextProvider.get(), dbProvider.get(), firebaseProvider.get());
  }

  public static MainRepository_Factory create(Provider<Context> contextProvider,
      Provider<OcpDatabase> dbProvider, Provider<FirebaseRepository> firebaseProvider) {
    return new MainRepository_Factory(contextProvider, dbProvider, firebaseProvider);
  }

  public static MainRepository newInstance(Context context, OcpDatabase db,
      FirebaseRepository firebase) {
    return new MainRepository(context, db, firebase);
  }
}
