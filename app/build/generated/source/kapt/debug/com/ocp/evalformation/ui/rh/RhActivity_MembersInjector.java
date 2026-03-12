package com.ocp.evalformation.ui.rh;

import com.google.firebase.auth.FirebaseAuth;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class RhActivity_MembersInjector implements MembersInjector<RhActivity> {
  private final Provider<FirebaseAuth> authProvider;

  public RhActivity_MembersInjector(Provider<FirebaseAuth> authProvider) {
    this.authProvider = authProvider;
  }

  public static MembersInjector<RhActivity> create(Provider<FirebaseAuth> authProvider) {
    return new RhActivity_MembersInjector(authProvider);
  }

  @Override
  public void injectMembers(RhActivity instance) {
    injectAuth(instance, authProvider.get());
  }

  @InjectedFieldSignature("com.ocp.evalformation.ui.rh.RhActivity.auth")
  public static void injectAuth(RhActivity instance, FirebaseAuth auth) {
    instance.auth = auth;
  }
}
