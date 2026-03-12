package com.ocp.evalformation.di;

import com.ocp.evalformation.data.local.OcpDatabase;
import com.ocp.evalformation.data.local.dao.CollaborateurDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideCollaborateurDaoFactory implements Factory<CollaborateurDao> {
  private final Provider<OcpDatabase> dbProvider;

  public AppModule_ProvideCollaborateurDaoFactory(Provider<OcpDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public CollaborateurDao get() {
    return provideCollaborateurDao(dbProvider.get());
  }

  public static AppModule_ProvideCollaborateurDaoFactory create(Provider<OcpDatabase> dbProvider) {
    return new AppModule_ProvideCollaborateurDaoFactory(dbProvider);
  }

  public static CollaborateurDao provideCollaborateurDao(OcpDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCollaborateurDao(db));
  }
}
