package com.ocp.evalformation.com.ocp.evalformation.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.ocp.evalformation.data.GoogleScriptApiService.GoogleScriptApiService;
import com.ocp.evalformation.data.local.dao.FormDao;
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
public final class FormRepository_Factory implements Factory<FormRepository> {
  private final Provider<FormDao> formDaoProvider;

  private final Provider<GoogleScriptApiService> apiProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  public FormRepository_Factory(Provider<FormDao> formDaoProvider,
      Provider<GoogleScriptApiService> apiProvider, Provider<FirebaseFirestore> firestoreProvider) {
    this.formDaoProvider = formDaoProvider;
    this.apiProvider = apiProvider;
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public FormRepository get() {
    return newInstance(formDaoProvider.get(), apiProvider.get(), firestoreProvider.get());
  }

  public static FormRepository_Factory create(Provider<FormDao> formDaoProvider,
      Provider<GoogleScriptApiService> apiProvider, Provider<FirebaseFirestore> firestoreProvider) {
    return new FormRepository_Factory(formDaoProvider, apiProvider, firestoreProvider);
  }

  public static FormRepository newInstance(FormDao formDao, GoogleScriptApiService api,
      FirebaseFirestore firestore) {
    return new FormRepository(formDao, api, firestore);
  }
}
