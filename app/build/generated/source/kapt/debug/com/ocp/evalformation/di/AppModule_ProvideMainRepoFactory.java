package com.ocp.evalformation.di;

import android.content.Context;
import com.ocp.evalformation.data.local.OcpDatabase;
import com.ocp.evalformation.data.remote.FirebaseRepository;
import com.ocp.evalformation.data.repository.MainRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideMainRepoFactory implements Factory<MainRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<OcpDatabase> dbProvider;

  private final Provider<FirebaseRepository> firebaseProvider;

  public AppModule_ProvideMainRepoFactory(Provider<Context> contextProvider,
      Provider<OcpDatabase> dbProvider, Provider<FirebaseRepository> firebaseProvider) {
    this.contextProvider = contextProvider;
    this.dbProvider = dbProvider;
    this.firebaseProvider = firebaseProvider;
  }

  @Override
  public MainRepository get() {
    return provideMainRepo(contextProvider.get(), dbProvider.get(), firebaseProvider.get());
  }

  public static AppModule_ProvideMainRepoFactory create(Provider<Context> contextProvider,
      Provider<OcpDatabase> dbProvider, Provider<FirebaseRepository> firebaseProvider) {
    return new AppModule_ProvideMainRepoFactory(contextProvider, dbProvider, firebaseProvider);
  }

  public static MainRepository provideMainRepo(Context context, OcpDatabase db,
      FirebaseRepository firebase) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideMainRepo(context, db, firebase));
  }
}
