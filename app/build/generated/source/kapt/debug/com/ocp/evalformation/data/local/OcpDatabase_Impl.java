package com.ocp.evalformation.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.ocp.evalformation.data.local.dao.CollaborateurDao;
import com.ocp.evalformation.data.local.dao.CollaborateurDao_Impl;
import com.ocp.evalformation.data.local.dao.EvaluationDao;
import com.ocp.evalformation.data.local.dao.EvaluationDao_Impl;
import com.ocp.evalformation.data.local.dao.FlmDao;
import com.ocp.evalformation.data.local.dao.FlmDao_Impl;
import com.ocp.evalformation.data.local.dao.FormationDao;
import com.ocp.evalformation.data.local.dao.FormationDao_Impl;
import com.ocp.evalformation.data.local.dao.InvitationFlmDao;
import com.ocp.evalformation.data.local.dao.InvitationFlmDao_Impl;
import com.ocp.evalformation.data.local.dao.ThemeDao;
import com.ocp.evalformation.data.local.dao.ThemeDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class OcpDatabase_Impl extends OcpDatabase {
  private volatile ThemeDao _themeDao;

  private volatile FlmDao _flmDao;

  private volatile CollaborateurDao _collaborateurDao;

  private volatile FormationDao _formationDao;

  private volatile EvaluationDao _evaluationDao;

  private volatile InvitationFlmDao _invitationFlmDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `themes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nom` TEXT NOT NULL, `objectifPedagogique` TEXT NOT NULL, `syncedToFirebase` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `flms` (`matricule` TEXT NOT NULL, `nom` TEXT NOT NULL, `prenom` TEXT NOT NULL, `email` TEXT NOT NULL, `service` TEXT NOT NULL, `syncedToFirebase` INTEGER NOT NULL, PRIMARY KEY(`matricule`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `collaborateurs` (`matricule` TEXT NOT NULL, `nom` TEXT NOT NULL, `prenom` TEXT NOT NULL, `service` TEXT NOT NULL, `flmMatricule` TEXT, `syncedToFirebase` INTEGER NOT NULL, PRIMARY KEY(`matricule`), FOREIGN KEY(`flmMatricule`) REFERENCES `flms`(`matricule`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_collaborateurs_flmMatricule` ON `collaborateurs` (`flmMatricule`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `formations` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `collaborateurMatricule` TEXT NOT NULL, `themeId` INTEGER NOT NULL, `debut` TEXT NOT NULL, `fin` TEXT NOT NULL, `syncedToFirebase` INTEGER NOT NULL, FOREIGN KEY(`collaborateurMatricule`) REFERENCES `collaborateurs`(`matricule`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`themeId`) REFERENCES `themes`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_formations_collaborateurMatricule` ON `formations` (`collaborateurMatricule`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_formations_themeId` ON `formations` (`themeId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `evaluations` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `formationId` INTEGER NOT NULL, `dateEvaluation` TEXT NOT NULL, `flmMatricule` TEXT NOT NULL, `flmNom` TEXT NOT NULL, `organisationContenu` INTEGER NOT NULL, `qualitePedagogique` INTEGER NOT NULL, `adaptationPublic` INTEGER NOT NULL, `maitriseSujet` INTEGER NOT NULL, `disponibiliteFormateur` INTEGER NOT NULL, `qualiteSupports` INTEGER NOT NULL, `atteinteObjectifs` INTEGER NOT NULL, `applicationTerrain` INTEGER NOT NULL, `propositionsAmelioration` TEXT NOT NULL, `commentaireGeneral` TEXT NOT NULL, `competencesAcquises` TEXT NOT NULL, `statut` TEXT NOT NULL, `googleFormResponseId` TEXT NOT NULL, `syncedToFirebase` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`formationId`) REFERENCES `formations`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_evaluations_formationId` ON `evaluations` (`formationId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `invitations_flm` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `formationId` INTEGER NOT NULL, `matriculeCollaborateur` TEXT NOT NULL, `nomCompletCollaborateur` TEXT NOT NULL, `themeNom` TEXT NOT NULL, `emailFlm` TEXT NOT NULL, `nomFlm` TEXT NOT NULL, `googleFormUrl` TEXT NOT NULL, `statut` TEXT NOT NULL, `dateEnvoi` INTEGER NOT NULL, `dateReponse` INTEGER, `evaluationId` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c1147b1fbfc8755ef7dbbe0b475fa2a2')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `themes`");
        db.execSQL("DROP TABLE IF EXISTS `flms`");
        db.execSQL("DROP TABLE IF EXISTS `collaborateurs`");
        db.execSQL("DROP TABLE IF EXISTS `formations`");
        db.execSQL("DROP TABLE IF EXISTS `evaluations`");
        db.execSQL("DROP TABLE IF EXISTS `invitations_flm`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsThemes = new HashMap<String, TableInfo.Column>(4);
        _columnsThemes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThemes.put("nom", new TableInfo.Column("nom", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThemes.put("objectifPedagogique", new TableInfo.Column("objectifPedagogique", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsThemes.put("syncedToFirebase", new TableInfo.Column("syncedToFirebase", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysThemes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesThemes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoThemes = new TableInfo("themes", _columnsThemes, _foreignKeysThemes, _indicesThemes);
        final TableInfo _existingThemes = TableInfo.read(db, "themes");
        if (!_infoThemes.equals(_existingThemes)) {
          return new RoomOpenHelper.ValidationResult(false, "themes(com.ocp.evalformation.data.local.entity.ThemeEntity).\n"
                  + " Expected:\n" + _infoThemes + "\n"
                  + " Found:\n" + _existingThemes);
        }
        final HashMap<String, TableInfo.Column> _columnsFlms = new HashMap<String, TableInfo.Column>(6);
        _columnsFlms.put("matricule", new TableInfo.Column("matricule", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlms.put("nom", new TableInfo.Column("nom", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlms.put("prenom", new TableInfo.Column("prenom", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlms.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlms.put("service", new TableInfo.Column("service", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlms.put("syncedToFirebase", new TableInfo.Column("syncedToFirebase", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFlms = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFlms = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFlms = new TableInfo("flms", _columnsFlms, _foreignKeysFlms, _indicesFlms);
        final TableInfo _existingFlms = TableInfo.read(db, "flms");
        if (!_infoFlms.equals(_existingFlms)) {
          return new RoomOpenHelper.ValidationResult(false, "flms(com.ocp.evalformation.data.local.entity.FlmEntity).\n"
                  + " Expected:\n" + _infoFlms + "\n"
                  + " Found:\n" + _existingFlms);
        }
        final HashMap<String, TableInfo.Column> _columnsCollaborateurs = new HashMap<String, TableInfo.Column>(6);
        _columnsCollaborateurs.put("matricule", new TableInfo.Column("matricule", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCollaborateurs.put("nom", new TableInfo.Column("nom", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCollaborateurs.put("prenom", new TableInfo.Column("prenom", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCollaborateurs.put("service", new TableInfo.Column("service", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCollaborateurs.put("flmMatricule", new TableInfo.Column("flmMatricule", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCollaborateurs.put("syncedToFirebase", new TableInfo.Column("syncedToFirebase", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCollaborateurs = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysCollaborateurs.add(new TableInfo.ForeignKey("flms", "SET NULL", "NO ACTION", Arrays.asList("flmMatricule"), Arrays.asList("matricule")));
        final HashSet<TableInfo.Index> _indicesCollaborateurs = new HashSet<TableInfo.Index>(1);
        _indicesCollaborateurs.add(new TableInfo.Index("index_collaborateurs_flmMatricule", false, Arrays.asList("flmMatricule"), Arrays.asList("ASC")));
        final TableInfo _infoCollaborateurs = new TableInfo("collaborateurs", _columnsCollaborateurs, _foreignKeysCollaborateurs, _indicesCollaborateurs);
        final TableInfo _existingCollaborateurs = TableInfo.read(db, "collaborateurs");
        if (!_infoCollaborateurs.equals(_existingCollaborateurs)) {
          return new RoomOpenHelper.ValidationResult(false, "collaborateurs(com.ocp.evalformation.data.local.entity.CollaborateurEntity).\n"
                  + " Expected:\n" + _infoCollaborateurs + "\n"
                  + " Found:\n" + _existingCollaborateurs);
        }
        final HashMap<String, TableInfo.Column> _columnsFormations = new HashMap<String, TableInfo.Column>(6);
        _columnsFormations.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFormations.put("collaborateurMatricule", new TableInfo.Column("collaborateurMatricule", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFormations.put("themeId", new TableInfo.Column("themeId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFormations.put("debut", new TableInfo.Column("debut", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFormations.put("fin", new TableInfo.Column("fin", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFormations.put("syncedToFirebase", new TableInfo.Column("syncedToFirebase", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFormations = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysFormations.add(new TableInfo.ForeignKey("collaborateurs", "CASCADE", "NO ACTION", Arrays.asList("collaborateurMatricule"), Arrays.asList("matricule")));
        _foreignKeysFormations.add(new TableInfo.ForeignKey("themes", "CASCADE", "NO ACTION", Arrays.asList("themeId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesFormations = new HashSet<TableInfo.Index>(2);
        _indicesFormations.add(new TableInfo.Index("index_formations_collaborateurMatricule", false, Arrays.asList("collaborateurMatricule"), Arrays.asList("ASC")));
        _indicesFormations.add(new TableInfo.Index("index_formations_themeId", false, Arrays.asList("themeId"), Arrays.asList("ASC")));
        final TableInfo _infoFormations = new TableInfo("formations", _columnsFormations, _foreignKeysFormations, _indicesFormations);
        final TableInfo _existingFormations = TableInfo.read(db, "formations");
        if (!_infoFormations.equals(_existingFormations)) {
          return new RoomOpenHelper.ValidationResult(false, "formations(com.ocp.evalformation.data.local.entity.FormationEntity).\n"
                  + " Expected:\n" + _infoFormations + "\n"
                  + " Found:\n" + _existingFormations);
        }
        final HashMap<String, TableInfo.Column> _columnsEvaluations = new HashMap<String, TableInfo.Column>(20);
        _columnsEvaluations.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("formationId", new TableInfo.Column("formationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("dateEvaluation", new TableInfo.Column("dateEvaluation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("flmMatricule", new TableInfo.Column("flmMatricule", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("flmNom", new TableInfo.Column("flmNom", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("organisationContenu", new TableInfo.Column("organisationContenu", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("qualitePedagogique", new TableInfo.Column("qualitePedagogique", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("adaptationPublic", new TableInfo.Column("adaptationPublic", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("maitriseSujet", new TableInfo.Column("maitriseSujet", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("disponibiliteFormateur", new TableInfo.Column("disponibiliteFormateur", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("qualiteSupports", new TableInfo.Column("qualiteSupports", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("atteinteObjectifs", new TableInfo.Column("atteinteObjectifs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("applicationTerrain", new TableInfo.Column("applicationTerrain", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("propositionsAmelioration", new TableInfo.Column("propositionsAmelioration", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("commentaireGeneral", new TableInfo.Column("commentaireGeneral", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("competencesAcquises", new TableInfo.Column("competencesAcquises", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("statut", new TableInfo.Column("statut", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("googleFormResponseId", new TableInfo.Column("googleFormResponseId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("syncedToFirebase", new TableInfo.Column("syncedToFirebase", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluations.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEvaluations = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysEvaluations.add(new TableInfo.ForeignKey("formations", "CASCADE", "NO ACTION", Arrays.asList("formationId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesEvaluations = new HashSet<TableInfo.Index>(1);
        _indicesEvaluations.add(new TableInfo.Index("index_evaluations_formationId", false, Arrays.asList("formationId"), Arrays.asList("ASC")));
        final TableInfo _infoEvaluations = new TableInfo("evaluations", _columnsEvaluations, _foreignKeysEvaluations, _indicesEvaluations);
        final TableInfo _existingEvaluations = TableInfo.read(db, "evaluations");
        if (!_infoEvaluations.equals(_existingEvaluations)) {
          return new RoomOpenHelper.ValidationResult(false, "evaluations(com.ocp.evalformation.data.local.entity.EvaluationEntity).\n"
                  + " Expected:\n" + _infoEvaluations + "\n"
                  + " Found:\n" + _existingEvaluations);
        }
        final HashMap<String, TableInfo.Column> _columnsInvitationsFlm = new HashMap<String, TableInfo.Column>(12);
        _columnsInvitationsFlm.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvitationsFlm.put("formationId", new TableInfo.Column("formationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvitationsFlm.put("matriculeCollaborateur", new TableInfo.Column("matriculeCollaborateur", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvitationsFlm.put("nomCompletCollaborateur", new TableInfo.Column("nomCompletCollaborateur", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvitationsFlm.put("themeNom", new TableInfo.Column("themeNom", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvitationsFlm.put("emailFlm", new TableInfo.Column("emailFlm", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvitationsFlm.put("nomFlm", new TableInfo.Column("nomFlm", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvitationsFlm.put("googleFormUrl", new TableInfo.Column("googleFormUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvitationsFlm.put("statut", new TableInfo.Column("statut", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvitationsFlm.put("dateEnvoi", new TableInfo.Column("dateEnvoi", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvitationsFlm.put("dateReponse", new TableInfo.Column("dateReponse", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvitationsFlm.put("evaluationId", new TableInfo.Column("evaluationId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInvitationsFlm = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInvitationsFlm = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInvitationsFlm = new TableInfo("invitations_flm", _columnsInvitationsFlm, _foreignKeysInvitationsFlm, _indicesInvitationsFlm);
        final TableInfo _existingInvitationsFlm = TableInfo.read(db, "invitations_flm");
        if (!_infoInvitationsFlm.equals(_existingInvitationsFlm)) {
          return new RoomOpenHelper.ValidationResult(false, "invitations_flm(com.ocp.evalformation.data.local.entity.InvitationFlmEntity).\n"
                  + " Expected:\n" + _infoInvitationsFlm + "\n"
                  + " Found:\n" + _existingInvitationsFlm);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c1147b1fbfc8755ef7dbbe0b475fa2a2", "b41dd3deb0b019ec1e677134bc8d607f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "themes","flms","collaborateurs","formations","evaluations","invitations_flm");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `themes`");
      _db.execSQL("DELETE FROM `flms`");
      _db.execSQL("DELETE FROM `collaborateurs`");
      _db.execSQL("DELETE FROM `formations`");
      _db.execSQL("DELETE FROM `evaluations`");
      _db.execSQL("DELETE FROM `invitations_flm`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ThemeDao.class, ThemeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FlmDao.class, FlmDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CollaborateurDao.class, CollaborateurDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FormationDao.class, FormationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EvaluationDao.class, EvaluationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(InvitationFlmDao.class, InvitationFlmDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ThemeDao themeDao() {
    if (_themeDao != null) {
      return _themeDao;
    } else {
      synchronized(this) {
        if(_themeDao == null) {
          _themeDao = new ThemeDao_Impl(this);
        }
        return _themeDao;
      }
    }
  }

  @Override
  public FlmDao flmDao() {
    if (_flmDao != null) {
      return _flmDao;
    } else {
      synchronized(this) {
        if(_flmDao == null) {
          _flmDao = new FlmDao_Impl(this);
        }
        return _flmDao;
      }
    }
  }

  @Override
  public CollaborateurDao collaborateurDao() {
    if (_collaborateurDao != null) {
      return _collaborateurDao;
    } else {
      synchronized(this) {
        if(_collaborateurDao == null) {
          _collaborateurDao = new CollaborateurDao_Impl(this);
        }
        return _collaborateurDao;
      }
    }
  }

  @Override
  public FormationDao formationDao() {
    if (_formationDao != null) {
      return _formationDao;
    } else {
      synchronized(this) {
        if(_formationDao == null) {
          _formationDao = new FormationDao_Impl(this);
        }
        return _formationDao;
      }
    }
  }

  @Override
  public EvaluationDao evaluationDao() {
    if (_evaluationDao != null) {
      return _evaluationDao;
    } else {
      synchronized(this) {
        if(_evaluationDao == null) {
          _evaluationDao = new EvaluationDao_Impl(this);
        }
        return _evaluationDao;
      }
    }
  }

  @Override
  public InvitationFlmDao invitationFlmDao() {
    if (_invitationFlmDao != null) {
      return _invitationFlmDao;
    } else {
      synchronized(this) {
        if(_invitationFlmDao == null) {
          _invitationFlmDao = new InvitationFlmDao_Impl(this);
        }
        return _invitationFlmDao;
      }
    }
  }
}
