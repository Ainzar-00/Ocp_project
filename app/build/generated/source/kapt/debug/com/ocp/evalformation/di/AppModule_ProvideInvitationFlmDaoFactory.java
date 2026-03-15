package com.ocp.evalformation.di;

import com.ocp.evalformation.data.local.OcpDatabase;
import com.ocp.evalformation.data.local.dao.InvitationFlmDao;
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
public final class AppModule_ProvideInvitationFlmDaoFactory implements Factory<InvitationFlmDao> {
  private final Provider<OcpDatabase> dbProvider;

  public AppModule_ProvideInvitationFlmDaoFactory(Provider<OcpDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public InvitationFlmDao get() {
    return provideInvitationFlmDao(dbProvider.get());
  }

  public static AppModule_ProvideInvitationFlmDaoFactory create(Provider<OcpDatabase> dbProvider) {
    return new AppModule_ProvideInvitationFlmDaoFactory(dbProvider);
  }

  public static InvitationFlmDao provideInvitationFlmDao(OcpDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideInvitationFlmDao(db));
  }
}
