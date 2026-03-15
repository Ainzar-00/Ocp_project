package com.ocp.evalformation.data.repository;

import com.ocp.evalformation.data.local.dao.CollaborateurDao;
import com.ocp.evalformation.data.local.dao.FlmDao;
import com.ocp.evalformation.data.local.dao.FormDao;
import com.ocp.evalformation.data.local.dao.FormationDao;
import com.ocp.evalformation.data.local.dao.InvitationFlmDao;
import com.ocp.evalformation.data.local.dao.ThemeDao;
import com.ocp.evalformation.data.remote.FirebaseRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class syncRepository_Factory implements Factory<syncRepository> {
  private final Provider<FirebaseRepository> firebaseProvider;

  private final Provider<ThemeDao> themeDaoProvider;

  private final Provider<FlmDao> flmDaoProvider;

  private final Provider<CollaborateurDao> collaborateurDaoProvider;

  private final Provider<FormationDao> formationDaoProvider;

  private final Provider<InvitationFlmDao> invitationFlmDaoProvider;

  private final Provider<FormDao> formDaoProvider;

  public syncRepository_Factory(Provider<FirebaseRepository> firebaseProvider,
      Provider<ThemeDao> themeDaoProvider, Provider<FlmDao> flmDaoProvider,
      Provider<CollaborateurDao> collaborateurDaoProvider,
      Provider<FormationDao> formationDaoProvider,
      Provider<InvitationFlmDao> invitationFlmDaoProvider, Provider<FormDao> formDaoProvider) {
    this.firebaseProvider = firebaseProvider;
    this.themeDaoProvider = themeDaoProvider;
    this.flmDaoProvider = flmDaoProvider;
    this.collaborateurDaoProvider = collaborateurDaoProvider;
    this.formationDaoProvider = formationDaoProvider;
    this.invitationFlmDaoProvider = invitationFlmDaoProvider;
    this.formDaoProvider = formDaoProvider;
  }

  @Override
  public syncRepository get() {
    return newInstance(firebaseProvider.get(), themeDaoProvider.get(), flmDaoProvider.get(), collaborateurDaoProvider.get(), formationDaoProvider.get(), invitationFlmDaoProvider.get(), formDaoProvider.get());
  }

  public static syncRepository_Factory create(Provider<FirebaseRepository> firebaseProvider,
      Provider<ThemeDao> themeDaoProvider, Provider<FlmDao> flmDaoProvider,
      Provider<CollaborateurDao> collaborateurDaoProvider,
      Provider<FormationDao> formationDaoProvider,
      Provider<InvitationFlmDao> invitationFlmDaoProvider, Provider<FormDao> formDaoProvider) {
    return new syncRepository_Factory(firebaseProvider, themeDaoProvider, flmDaoProvider, collaborateurDaoProvider, formationDaoProvider, invitationFlmDaoProvider, formDaoProvider);
  }

  public static syncRepository newInstance(FirebaseRepository firebase, ThemeDao themeDao,
      FlmDao flmDao, CollaborateurDao collaborateurDao, FormationDao formationDao,
      InvitationFlmDao InvitationFlmDao, FormDao formDao) {
    return new syncRepository(firebase, themeDao, flmDao, collaborateurDao, formationDao, InvitationFlmDao, formDao);
  }
}
