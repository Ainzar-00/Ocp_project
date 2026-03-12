package com.ocp.evalformation.ui.flm;

import com.ocp.evalformation.data.repository.MainRepository;
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
public final class FlmViewModel_Factory implements Factory<FlmViewModel> {
  private final Provider<MainRepository> repoProvider;

  public FlmViewModel_Factory(Provider<MainRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public FlmViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static FlmViewModel_Factory create(Provider<MainRepository> repoProvider) {
    return new FlmViewModel_Factory(repoProvider);
  }

  public static FlmViewModel newInstance(MainRepository repo) {
    return new FlmViewModel(repo);
  }
}
