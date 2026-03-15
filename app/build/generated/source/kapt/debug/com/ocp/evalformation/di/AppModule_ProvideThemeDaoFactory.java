package com.ocp.evalformation.di;

import com.ocp.evalformation.data.local.OcpDatabase;
import com.ocp.evalformation.data.local.dao.ThemeDao;
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
public final class AppModule_ProvideThemeDaoFactory implements Factory<ThemeDao> {
  private final Provider<OcpDatabase> dbProvider;

  public AppModule_ProvideThemeDaoFactory(Provider<OcpDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ThemeDao get() {
    return provideThemeDao(dbProvider.get());
  }

  public static AppModule_ProvideThemeDaoFactory create(Provider<OcpDatabase> dbProvider) {
    return new AppModule_ProvideThemeDaoFactory(dbProvider);
  }

  public static ThemeDao provideThemeDao(OcpDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideThemeDao(db));
  }
}
