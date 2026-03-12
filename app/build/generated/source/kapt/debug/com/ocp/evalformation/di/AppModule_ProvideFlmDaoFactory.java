package com.ocp.evalformation.di;

import com.ocp.evalformation.data.local.OcpDatabase;
import com.ocp.evalformation.data.local.dao.FlmDao;
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
public final class AppModule_ProvideFlmDaoFactory implements Factory<FlmDao> {
  private final Provider<OcpDatabase> dbProvider;

  public AppModule_ProvideFlmDaoFactory(Provider<OcpDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public FlmDao get() {
    return provideFlmDao(dbProvider.get());
  }

  public static AppModule_ProvideFlmDaoFactory create(Provider<OcpDatabase> dbProvider) {
    return new AppModule_ProvideFlmDaoFactory(dbProvider);
  }

  public static FlmDao provideFlmDao(OcpDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideFlmDao(db));
  }
}
