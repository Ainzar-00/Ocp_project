package com.ocp.evalformation.ui.rh.import_data;

import android.content.Context;
import com.ocp.evalformation.data.repository.MainRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ImportViewModel_Factory implements Factory<ImportViewModel> {
  private final Provider<MainRepository> repoProvider;

  private final Provider<Context> contextProvider;

  public ImportViewModel_Factory(Provider<MainRepository> repoProvider,
      Provider<Context> contextProvider) {
    this.repoProvider = repoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public ImportViewModel get() {
    return newInstance(repoProvider.get(), contextProvider.get());
  }

  public static ImportViewModel_Factory create(Provider<MainRepository> repoProvider,
      Provider<Context> contextProvider) {
    return new ImportViewModel_Factory(repoProvider, contextProvider);
  }

  public static ImportViewModel newInstance(MainRepository repo, Context context) {
    return new ImportViewModel(repo, context);
  }
}
