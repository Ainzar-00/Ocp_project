package com.ocp.evalformation.di;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ocp.evalformation.data.local.dao.FormationDao;
import com.ocp.evalformation.data.local.dao.ThemeDao;
import com.ocp.evalformation.data.remote.FirebaseRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideFirebaseRepoFactory implements Factory<FirebaseRepository> {
  private final Provider<FirebaseAuth> authProvider;

  private final Provider<FirebaseFirestore> fsProvider;

  private final Provider<ThemeDao> themeDaoProvider;

  private final Provider<FormationDao> formationDaoProvider;

  public AppModule_ProvideFirebaseRepoFactory(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> fsProvider, Provider<ThemeDao> themeDaoProvider,
      Provider<FormationDao> formationDaoProvider) {
    this.authProvider = authProvider;
    this.fsProvider = fsProvider;
    this.themeDaoProvider = themeDaoProvider;
    this.formationDaoProvider = formationDaoProvider;
  }

  @Override
  public FirebaseRepository get() {
    return provideFirebaseRepo(authProvider.get(), fsProvider.get(), themeDaoProvider.get(), formationDaoProvider.get());
  }

  public static AppModule_ProvideFirebaseRepoFactory create(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> fsProvider, Provider<ThemeDao> themeDaoProvider,
      Provider<FormationDao> formationDaoProvider) {
    return new AppModule_ProvideFirebaseRepoFactory(authProvider, fsProvider, themeDaoProvider, formationDaoProvider);
  }

  public static FirebaseRepository provideFirebaseRepo(FirebaseAuth auth, FirebaseFirestore fs,
      ThemeDao themeDao, FormationDao formationDao) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideFirebaseRepo(auth, fs, themeDao, formationDao));
  }
}
