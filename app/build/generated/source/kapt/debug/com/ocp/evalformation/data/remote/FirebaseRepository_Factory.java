package com.ocp.evalformation.data.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ocp.evalformation.data.local.dao.FormationDao;
import com.ocp.evalformation.data.local.dao.ThemeDao;
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
public final class FirebaseRepository_Factory implements Factory<FirebaseRepository> {
  private final Provider<FirebaseAuth> authProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<ThemeDao> themeDaoProvider;

  private final Provider<FormationDao> formationDaoProvider;

  public FirebaseRepository_Factory(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> firestoreProvider, Provider<ThemeDao> themeDaoProvider,
      Provider<FormationDao> formationDaoProvider) {
    this.authProvider = authProvider;
    this.firestoreProvider = firestoreProvider;
    this.themeDaoProvider = themeDaoProvider;
    this.formationDaoProvider = formationDaoProvider;
  }

  @Override
  public FirebaseRepository get() {
    return newInstance(authProvider.get(), firestoreProvider.get(), themeDaoProvider.get(), formationDaoProvider.get());
  }

  public static FirebaseRepository_Factory create(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> firestoreProvider, Provider<ThemeDao> themeDaoProvider,
      Provider<FormationDao> formationDaoProvider) {
    return new FirebaseRepository_Factory(authProvider, firestoreProvider, themeDaoProvider, formationDaoProvider);
  }

  public static FirebaseRepository newInstance(FirebaseAuth auth, FirebaseFirestore firestore,
      ThemeDao themeDao, FormationDao formationDao) {
    return new FirebaseRepository(auth, firestore, themeDao, formationDao);
  }
}
