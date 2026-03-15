package com.ocp.evalformation.ui.rh;

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
public final class RhViewModel_Factory implements Factory<RhViewModel> {
  private final Provider<MainRepository> repoProvider;

  public RhViewModel_Factory(Provider<MainRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public RhViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static RhViewModel_Factory create(Provider<MainRepository> repoProvider) {
    return new RhViewModel_Factory(repoProvider);
  }

  public static RhViewModel newInstance(MainRepository repo) {
    return new RhViewModel(repo);
  }
}
