package com.ocp.evalformation.ui.rh;

import android.app.Application;
import com.ocp.evalformation.data.repository.EvaluationRepository;
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

  private final Provider<EvaluationRepository> evaluationRepoProvider;

  private final Provider<Application> applicationProvider;

  public RhViewModel_Factory(Provider<MainRepository> repoProvider,
      Provider<EvaluationRepository> evaluationRepoProvider,
      Provider<Application> applicationProvider) {
    this.repoProvider = repoProvider;
    this.evaluationRepoProvider = evaluationRepoProvider;
    this.applicationProvider = applicationProvider;
  }

  @Override
  public RhViewModel get() {
    return newInstance(repoProvider.get(), evaluationRepoProvider.get(), applicationProvider.get());
  }

  public static RhViewModel_Factory create(Provider<MainRepository> repoProvider,
      Provider<EvaluationRepository> evaluationRepoProvider,
      Provider<Application> applicationProvider) {
    return new RhViewModel_Factory(repoProvider, evaluationRepoProvider, applicationProvider);
  }

  public static RhViewModel newInstance(MainRepository repo, EvaluationRepository evaluationRepo,
      Application application) {
    return new RhViewModel(repo, evaluationRepo, application);
  }
}
