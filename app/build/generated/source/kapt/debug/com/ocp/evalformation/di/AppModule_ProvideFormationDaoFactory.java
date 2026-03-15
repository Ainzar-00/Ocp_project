package com.ocp.evalformation.di;

import com.ocp.evalformation.data.local.OcpDatabase;
import com.ocp.evalformation.data.local.dao.FormationDao;
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
public final class AppModule_ProvideFormationDaoFactory implements Factory<FormationDao> {
  private final Provider<OcpDatabase> dbProvider;

  public AppModule_ProvideFormationDaoFactory(Provider<OcpDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public FormationDao get() {
    return provideFormationDao(dbProvider.get());
  }

  public static AppModule_ProvideFormationDaoFactory create(Provider<OcpDatabase> dbProvider) {
    return new AppModule_ProvideFormationDaoFactory(dbProvider);
  }

  public static FormationDao provideFormationDao(OcpDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideFormationDao(db));
  }
}
