package com.ocp.evalformation.ui.flm;

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
public final class FlmActivity_MembersInjector implements MembersInjector<FlmActivity> {
  private final Provider<FirebaseAuth> authProvider;

  public FlmActivity_MembersInjector(Provider<FirebaseAuth> authProvider) {
    this.authProvider = authProvider;
  }

  public static MembersInjector<FlmActivity> create(Provider<FirebaseAuth> authProvider) {
    return new FlmActivity_MembersInjector(authProvider);
  }

  @Override
  public void injectMembers(FlmActivity instance) {
    injectAuth(instance, authProvider.get());
  }

  @InjectedFieldSignature("com.ocp.evalformation.ui.flm.FlmActivity.auth")
  public static void injectAuth(FlmActivity instance, FirebaseAuth auth) {
    instance.auth = auth;
  }
}
