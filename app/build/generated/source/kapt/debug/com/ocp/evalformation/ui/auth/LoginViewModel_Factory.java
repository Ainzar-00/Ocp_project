package com.ocp.evalformation.ui.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ocp.evalformation.data.repository.syncRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<FirebaseAuth> authProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<syncRepository> syncRepositoryProvider;

  public LoginViewModel_Factory(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> firestoreProvider,
      Provider<syncRepository> syncRepositoryProvider) {
    this.authProvider = authProvider;
    this.firestoreProvider = firestoreProvider;
    this.syncRepositoryProvider = syncRepositoryProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(authProvider.get(), firestoreProvider.get(), syncRepositoryProvider.get());
  }

  public static LoginViewModel_Factory create(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> firestoreProvider,
      Provider<syncRepository> syncRepositoryProvider) {
    return new LoginViewModel_Factory(authProvider, firestoreProvider, syncRepositoryProvider);
  }

  public static LoginViewModel newInstance(FirebaseAuth auth, FirebaseFirestore firestore,
      syncRepository syncRepository) {
    return new LoginViewModel(auth, firestore, syncRepository);
  }
}
