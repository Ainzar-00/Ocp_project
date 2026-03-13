package com.ocp.evalformation.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ocp.evalformation.data.local.entity.EvaluationEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EvaluationDao_Impl implements EvaluationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EvaluationEntity> __insertionAdapterOfEvaluationEntity;

  private final EntityDeletionOrUpdateAdapter<EvaluationEntity> __updateAdapterOfEvaluationEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public EvaluationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEvaluationEntity = new EntityInsertionAdapter<EvaluationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `evaluations` (`id`,`formationId`,`dateEvaluation`,`flmMatricule`,`flmNom`,`organisationContenu`,`qualitePedagogique`,`adaptationPublic`,`maitriseSujet`,`disponibiliteFormateur`,`qualiteSupports`,`atteinteObjectifs`,`applicationTerrain`,`propositionsAmelioration`,`commentaireGeneral`,`competencesAcquises`,`statut`,`googleFormResponseId`,`syncedToFirebase`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EvaluationEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getFormationId());
        if (entity.getDateEvaluation() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDateEvaluation());
        }
        if (entity.getFlmMatricule() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getFlmMatricule());
        }
        if (entity.getFlmNom() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFlmNom());
        }
        statement.bindLong(6, entity.getOrganisationContenu());
        statement.bindLong(7, entity.getQualitePedagogique());
        statement.bindLong(8, entity.getAdaptationPublic());
        statement.bindLong(9, entity.getMaitriseSujet());
        statement.bindLong(10, entity.getDisponibiliteFormateur());
        statement.bindLong(11, entity.getQualiteSupports());
        statement.bindLong(12, entity.getAtteinteObjectifs());
        statement.bindLong(13, entity.getApplicationTerrain());
        if (entity.getPropositionsAmelioration() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getPropositionsAmelioration());
        }
        if (entity.getCommentaireGeneral() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getCommentaireGeneral());
        }
        if (entity.getCompetencesAcquises() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getCompetencesAcquises());
        }
        if (entity.getStatut() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getStatut());
        }
        if (entity.getGoogleFormResponseId() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getGoogleFormResponseId());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(19, _tmp);
        statement.bindLong(20, entity.getCreatedAt());
      }
    };
    this.__updateAdapterOfEvaluationEntity = new EntityDeletionOrUpdateAdapter<EvaluationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `evaluations` SET `id` = ?,`formationId` = ?,`dateEvaluation` = ?,`flmMatricule` = ?,`flmNom` = ?,`organisationContenu` = ?,`qualitePedagogique` = ?,`adaptationPublic` = ?,`maitriseSujet` = ?,`disponibiliteFormateur` = ?,`qualiteSupports` = ?,`atteinteObjectifs` = ?,`applicationTerrain` = ?,`propositionsAmelioration` = ?,`commentaireGeneral` = ?,`competencesAcquises` = ?,`statut` = ?,`googleFormResponseId` = ?,`syncedToFirebase` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EvaluationEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getFormationId());
        if (entity.getDateEvaluation() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDateEvaluation());
        }
        if (entity.getFlmMatricule() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getFlmMatricule());
        }
        if (entity.getFlmNom() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFlmNom());
        }
        statement.bindLong(6, entity.getOrganisationContenu());
        statement.bindLong(7, entity.getQualitePedagogique());
        statement.bindLong(8, entity.getAdaptationPublic());
        statement.bindLong(9, entity.getMaitriseSujet());
        statement.bindLong(10, entity.getDisponibiliteFormateur());
        statement.bindLong(11, entity.getQualiteSupports());
        statement.bindLong(12, entity.getAtteinteObjectifs());
        statement.bindLong(13, entity.getApplicationTerrain());
        if (entity.getPropositionsAmelioration() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getPropositionsAmelioration());
        }
        if (entity.getCommentaireGeneral() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getCommentaireGeneral());
        }
        if (entity.getCompetencesAcquises() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getCompetencesAcquises());
        }
        if (entity.getStatut() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getStatut());
        }
        if (entity.getGoogleFormResponseId() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getGoogleFormResponseId());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(19, _tmp);
        statement.bindLong(20, entity.getCreatedAt());
        statement.bindLong(21, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM evaluations";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final EvaluationEntity evaluation, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfEvaluationEntity.insertAndReturnId(evaluation);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object update(final EvaluationEntity evaluation, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfEvaluationEntity.handle(evaluation);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> arg0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, arg0);
  }

  @Override
  public LiveData<List<EvaluationEntity>> getAllLive() {
    final String _sql = "SELECT * FROM evaluations ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"evaluations"}, false, new Callable<List<EvaluationEntity>>() {
      @Override
      @Nullable
      public List<EvaluationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfDateEvaluation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEvaluation");
          final int _cursorIndexOfFlmMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "flmMatricule");
          final int _cursorIndexOfFlmNom = CursorUtil.getColumnIndexOrThrow(_cursor, "flmNom");
          final int _cursorIndexOfOrganisationContenu = CursorUtil.getColumnIndexOrThrow(_cursor, "organisationContenu");
          final int _cursorIndexOfQualitePedagogique = CursorUtil.getColumnIndexOrThrow(_cursor, "qualitePedagogique");
          final int _cursorIndexOfAdaptationPublic = CursorUtil.getColumnIndexOrThrow(_cursor, "adaptationPublic");
          final int _cursorIndexOfMaitriseSujet = CursorUtil.getColumnIndexOrThrow(_cursor, "maitriseSujet");
          final int _cursorIndexOfDisponibiliteFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "disponibiliteFormateur");
          final int _cursorIndexOfQualiteSupports = CursorUtil.getColumnIndexOrThrow(_cursor, "qualiteSupports");
          final int _cursorIndexOfAtteinteObjectifs = CursorUtil.getColumnIndexOrThrow(_cursor, "atteinteObjectifs");
          final int _cursorIndexOfApplicationTerrain = CursorUtil.getColumnIndexOrThrow(_cursor, "applicationTerrain");
          final int _cursorIndexOfPropositionsAmelioration = CursorUtil.getColumnIndexOrThrow(_cursor, "propositionsAmelioration");
          final int _cursorIndexOfCommentaireGeneral = CursorUtil.getColumnIndexOrThrow(_cursor, "commentaireGeneral");
          final int _cursorIndexOfCompetencesAcquises = CursorUtil.getColumnIndexOrThrow(_cursor, "competencesAcquises");
          final int _cursorIndexOfStatut = CursorUtil.getColumnIndexOrThrow(_cursor, "statut");
          final int _cursorIndexOfGoogleFormResponseId = CursorUtil.getColumnIndexOrThrow(_cursor, "googleFormResponseId");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<EvaluationEntity> _result = new ArrayList<EvaluationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EvaluationEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpDateEvaluation;
            if (_cursor.isNull(_cursorIndexOfDateEvaluation)) {
              _tmpDateEvaluation = null;
            } else {
              _tmpDateEvaluation = _cursor.getString(_cursorIndexOfDateEvaluation);
            }
            final String _tmpFlmMatricule;
            if (_cursor.isNull(_cursorIndexOfFlmMatricule)) {
              _tmpFlmMatricule = null;
            } else {
              _tmpFlmMatricule = _cursor.getString(_cursorIndexOfFlmMatricule);
            }
            final String _tmpFlmNom;
            if (_cursor.isNull(_cursorIndexOfFlmNom)) {
              _tmpFlmNom = null;
            } else {
              _tmpFlmNom = _cursor.getString(_cursorIndexOfFlmNom);
            }
            final int _tmpOrganisationContenu;
            _tmpOrganisationContenu = _cursor.getInt(_cursorIndexOfOrganisationContenu);
            final int _tmpQualitePedagogique;
            _tmpQualitePedagogique = _cursor.getInt(_cursorIndexOfQualitePedagogique);
            final int _tmpAdaptationPublic;
            _tmpAdaptationPublic = _cursor.getInt(_cursorIndexOfAdaptationPublic);
            final int _tmpMaitriseSujet;
            _tmpMaitriseSujet = _cursor.getInt(_cursorIndexOfMaitriseSujet);
            final int _tmpDisponibiliteFormateur;
            _tmpDisponibiliteFormateur = _cursor.getInt(_cursorIndexOfDisponibiliteFormateur);
            final int _tmpQualiteSupports;
            _tmpQualiteSupports = _cursor.getInt(_cursorIndexOfQualiteSupports);
            final int _tmpAtteinteObjectifs;
            _tmpAtteinteObjectifs = _cursor.getInt(_cursorIndexOfAtteinteObjectifs);
            final int _tmpApplicationTerrain;
            _tmpApplicationTerrain = _cursor.getInt(_cursorIndexOfApplicationTerrain);
            final String _tmpPropositionsAmelioration;
            if (_cursor.isNull(_cursorIndexOfPropositionsAmelioration)) {
              _tmpPropositionsAmelioration = null;
            } else {
              _tmpPropositionsAmelioration = _cursor.getString(_cursorIndexOfPropositionsAmelioration);
            }
            final String _tmpCommentaireGeneral;
            if (_cursor.isNull(_cursorIndexOfCommentaireGeneral)) {
              _tmpCommentaireGeneral = null;
            } else {
              _tmpCommentaireGeneral = _cursor.getString(_cursorIndexOfCommentaireGeneral);
            }
            final String _tmpCompetencesAcquises;
            if (_cursor.isNull(_cursorIndexOfCompetencesAcquises)) {
              _tmpCompetencesAcquises = null;
            } else {
              _tmpCompetencesAcquises = _cursor.getString(_cursorIndexOfCompetencesAcquises);
            }
            final String _tmpStatut;
            if (_cursor.isNull(_cursorIndexOfStatut)) {
              _tmpStatut = null;
            } else {
              _tmpStatut = _cursor.getString(_cursorIndexOfStatut);
            }
            final String _tmpGoogleFormResponseId;
            if (_cursor.isNull(_cursorIndexOfGoogleFormResponseId)) {
              _tmpGoogleFormResponseId = null;
            } else {
              _tmpGoogleFormResponseId = _cursor.getString(_cursorIndexOfGoogleFormResponseId);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new EvaluationEntity(_tmpId,_tmpFormationId,_tmpDateEvaluation,_tmpFlmMatricule,_tmpFlmNom,_tmpOrganisationContenu,_tmpQualitePedagogique,_tmpAdaptationPublic,_tmpMaitriseSujet,_tmpDisponibiliteFormateur,_tmpQualiteSupports,_tmpAtteinteObjectifs,_tmpApplicationTerrain,_tmpPropositionsAmelioration,_tmpCommentaireGeneral,_tmpCompetencesAcquises,_tmpStatut,_tmpGoogleFormResponseId,_tmpSyncedToFirebase,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAll(final Continuation<? super List<EvaluationEntity>> arg0) {
    final String _sql = "SELECT * FROM evaluations ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<EvaluationEntity>>() {
      @Override
      @NonNull
      public List<EvaluationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfDateEvaluation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEvaluation");
          final int _cursorIndexOfFlmMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "flmMatricule");
          final int _cursorIndexOfFlmNom = CursorUtil.getColumnIndexOrThrow(_cursor, "flmNom");
          final int _cursorIndexOfOrganisationContenu = CursorUtil.getColumnIndexOrThrow(_cursor, "organisationContenu");
          final int _cursorIndexOfQualitePedagogique = CursorUtil.getColumnIndexOrThrow(_cursor, "qualitePedagogique");
          final int _cursorIndexOfAdaptationPublic = CursorUtil.getColumnIndexOrThrow(_cursor, "adaptationPublic");
          final int _cursorIndexOfMaitriseSujet = CursorUtil.getColumnIndexOrThrow(_cursor, "maitriseSujet");
          final int _cursorIndexOfDisponibiliteFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "disponibiliteFormateur");
          final int _cursorIndexOfQualiteSupports = CursorUtil.getColumnIndexOrThrow(_cursor, "qualiteSupports");
          final int _cursorIndexOfAtteinteObjectifs = CursorUtil.getColumnIndexOrThrow(_cursor, "atteinteObjectifs");
          final int _cursorIndexOfApplicationTerrain = CursorUtil.getColumnIndexOrThrow(_cursor, "applicationTerrain");
          final int _cursorIndexOfPropositionsAmelioration = CursorUtil.getColumnIndexOrThrow(_cursor, "propositionsAmelioration");
          final int _cursorIndexOfCommentaireGeneral = CursorUtil.getColumnIndexOrThrow(_cursor, "commentaireGeneral");
          final int _cursorIndexOfCompetencesAcquises = CursorUtil.getColumnIndexOrThrow(_cursor, "competencesAcquises");
          final int _cursorIndexOfStatut = CursorUtil.getColumnIndexOrThrow(_cursor, "statut");
          final int _cursorIndexOfGoogleFormResponseId = CursorUtil.getColumnIndexOrThrow(_cursor, "googleFormResponseId");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<EvaluationEntity> _result = new ArrayList<EvaluationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EvaluationEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpDateEvaluation;
            if (_cursor.isNull(_cursorIndexOfDateEvaluation)) {
              _tmpDateEvaluation = null;
            } else {
              _tmpDateEvaluation = _cursor.getString(_cursorIndexOfDateEvaluation);
            }
            final String _tmpFlmMatricule;
            if (_cursor.isNull(_cursorIndexOfFlmMatricule)) {
              _tmpFlmMatricule = null;
            } else {
              _tmpFlmMatricule = _cursor.getString(_cursorIndexOfFlmMatricule);
            }
            final String _tmpFlmNom;
            if (_cursor.isNull(_cursorIndexOfFlmNom)) {
              _tmpFlmNom = null;
            } else {
              _tmpFlmNom = _cursor.getString(_cursorIndexOfFlmNom);
            }
            final int _tmpOrganisationContenu;
            _tmpOrganisationContenu = _cursor.getInt(_cursorIndexOfOrganisationContenu);
            final int _tmpQualitePedagogique;
            _tmpQualitePedagogique = _cursor.getInt(_cursorIndexOfQualitePedagogique);
            final int _tmpAdaptationPublic;
            _tmpAdaptationPublic = _cursor.getInt(_cursorIndexOfAdaptationPublic);
            final int _tmpMaitriseSujet;
            _tmpMaitriseSujet = _cursor.getInt(_cursorIndexOfMaitriseSujet);
            final int _tmpDisponibiliteFormateur;
            _tmpDisponibiliteFormateur = _cursor.getInt(_cursorIndexOfDisponibiliteFormateur);
            final int _tmpQualiteSupports;
            _tmpQualiteSupports = _cursor.getInt(_cursorIndexOfQualiteSupports);
            final int _tmpAtteinteObjectifs;
            _tmpAtteinteObjectifs = _cursor.getInt(_cursorIndexOfAtteinteObjectifs);
            final int _tmpApplicationTerrain;
            _tmpApplicationTerrain = _cursor.getInt(_cursorIndexOfApplicationTerrain);
            final String _tmpPropositionsAmelioration;
            if (_cursor.isNull(_cursorIndexOfPropositionsAmelioration)) {
              _tmpPropositionsAmelioration = null;
            } else {
              _tmpPropositionsAmelioration = _cursor.getString(_cursorIndexOfPropositionsAmelioration);
            }
            final String _tmpCommentaireGeneral;
            if (_cursor.isNull(_cursorIndexOfCommentaireGeneral)) {
              _tmpCommentaireGeneral = null;
            } else {
              _tmpCommentaireGeneral = _cursor.getString(_cursorIndexOfCommentaireGeneral);
            }
            final String _tmpCompetencesAcquises;
            if (_cursor.isNull(_cursorIndexOfCompetencesAcquises)) {
              _tmpCompetencesAcquises = null;
            } else {
              _tmpCompetencesAcquises = _cursor.getString(_cursorIndexOfCompetencesAcquises);
            }
            final String _tmpStatut;
            if (_cursor.isNull(_cursorIndexOfStatut)) {
              _tmpStatut = null;
            } else {
              _tmpStatut = _cursor.getString(_cursorIndexOfStatut);
            }
            final String _tmpGoogleFormResponseId;
            if (_cursor.isNull(_cursorIndexOfGoogleFormResponseId)) {
              _tmpGoogleFormResponseId = null;
            } else {
              _tmpGoogleFormResponseId = _cursor.getString(_cursorIndexOfGoogleFormResponseId);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new EvaluationEntity(_tmpId,_tmpFormationId,_tmpDateEvaluation,_tmpFlmMatricule,_tmpFlmNom,_tmpOrganisationContenu,_tmpQualitePedagogique,_tmpAdaptationPublic,_tmpMaitriseSujet,_tmpDisponibiliteFormateur,_tmpQualiteSupports,_tmpAtteinteObjectifs,_tmpApplicationTerrain,_tmpPropositionsAmelioration,_tmpCommentaireGeneral,_tmpCompetencesAcquises,_tmpStatut,_tmpGoogleFormResponseId,_tmpSyncedToFirebase,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @Override
  public Object getById(final long id, final Continuation<? super EvaluationEntity> arg1) {
    final String _sql = "SELECT * FROM evaluations WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<EvaluationEntity>() {
      @Override
      @Nullable
      public EvaluationEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfDateEvaluation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEvaluation");
          final int _cursorIndexOfFlmMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "flmMatricule");
          final int _cursorIndexOfFlmNom = CursorUtil.getColumnIndexOrThrow(_cursor, "flmNom");
          final int _cursorIndexOfOrganisationContenu = CursorUtil.getColumnIndexOrThrow(_cursor, "organisationContenu");
          final int _cursorIndexOfQualitePedagogique = CursorUtil.getColumnIndexOrThrow(_cursor, "qualitePedagogique");
          final int _cursorIndexOfAdaptationPublic = CursorUtil.getColumnIndexOrThrow(_cursor, "adaptationPublic");
          final int _cursorIndexOfMaitriseSujet = CursorUtil.getColumnIndexOrThrow(_cursor, "maitriseSujet");
          final int _cursorIndexOfDisponibiliteFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "disponibiliteFormateur");
          final int _cursorIndexOfQualiteSupports = CursorUtil.getColumnIndexOrThrow(_cursor, "qualiteSupports");
          final int _cursorIndexOfAtteinteObjectifs = CursorUtil.getColumnIndexOrThrow(_cursor, "atteinteObjectifs");
          final int _cursorIndexOfApplicationTerrain = CursorUtil.getColumnIndexOrThrow(_cursor, "applicationTerrain");
          final int _cursorIndexOfPropositionsAmelioration = CursorUtil.getColumnIndexOrThrow(_cursor, "propositionsAmelioration");
          final int _cursorIndexOfCommentaireGeneral = CursorUtil.getColumnIndexOrThrow(_cursor, "commentaireGeneral");
          final int _cursorIndexOfCompetencesAcquises = CursorUtil.getColumnIndexOrThrow(_cursor, "competencesAcquises");
          final int _cursorIndexOfStatut = CursorUtil.getColumnIndexOrThrow(_cursor, "statut");
          final int _cursorIndexOfGoogleFormResponseId = CursorUtil.getColumnIndexOrThrow(_cursor, "googleFormResponseId");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final EvaluationEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpDateEvaluation;
            if (_cursor.isNull(_cursorIndexOfDateEvaluation)) {
              _tmpDateEvaluation = null;
            } else {
              _tmpDateEvaluation = _cursor.getString(_cursorIndexOfDateEvaluation);
            }
            final String _tmpFlmMatricule;
            if (_cursor.isNull(_cursorIndexOfFlmMatricule)) {
              _tmpFlmMatricule = null;
            } else {
              _tmpFlmMatricule = _cursor.getString(_cursorIndexOfFlmMatricule);
            }
            final String _tmpFlmNom;
            if (_cursor.isNull(_cursorIndexOfFlmNom)) {
              _tmpFlmNom = null;
            } else {
              _tmpFlmNom = _cursor.getString(_cursorIndexOfFlmNom);
            }
            final int _tmpOrganisationContenu;
            _tmpOrganisationContenu = _cursor.getInt(_cursorIndexOfOrganisationContenu);
            final int _tmpQualitePedagogique;
            _tmpQualitePedagogique = _cursor.getInt(_cursorIndexOfQualitePedagogique);
            final int _tmpAdaptationPublic;
            _tmpAdaptationPublic = _cursor.getInt(_cursorIndexOfAdaptationPublic);
            final int _tmpMaitriseSujet;
            _tmpMaitriseSujet = _cursor.getInt(_cursorIndexOfMaitriseSujet);
            final int _tmpDisponibiliteFormateur;
            _tmpDisponibiliteFormateur = _cursor.getInt(_cursorIndexOfDisponibiliteFormateur);
            final int _tmpQualiteSupports;
            _tmpQualiteSupports = _cursor.getInt(_cursorIndexOfQualiteSupports);
            final int _tmpAtteinteObjectifs;
            _tmpAtteinteObjectifs = _cursor.getInt(_cursorIndexOfAtteinteObjectifs);
            final int _tmpApplicationTerrain;
            _tmpApplicationTerrain = _cursor.getInt(_cursorIndexOfApplicationTerrain);
            final String _tmpPropositionsAmelioration;
            if (_cursor.isNull(_cursorIndexOfPropositionsAmelioration)) {
              _tmpPropositionsAmelioration = null;
            } else {
              _tmpPropositionsAmelioration = _cursor.getString(_cursorIndexOfPropositionsAmelioration);
            }
            final String _tmpCommentaireGeneral;
            if (_cursor.isNull(_cursorIndexOfCommentaireGeneral)) {
              _tmpCommentaireGeneral = null;
            } else {
              _tmpCommentaireGeneral = _cursor.getString(_cursorIndexOfCommentaireGeneral);
            }
            final String _tmpCompetencesAcquises;
            if (_cursor.isNull(_cursorIndexOfCompetencesAcquises)) {
              _tmpCompetencesAcquises = null;
            } else {
              _tmpCompetencesAcquises = _cursor.getString(_cursorIndexOfCompetencesAcquises);
            }
            final String _tmpStatut;
            if (_cursor.isNull(_cursorIndexOfStatut)) {
              _tmpStatut = null;
            } else {
              _tmpStatut = _cursor.getString(_cursorIndexOfStatut);
            }
            final String _tmpGoogleFormResponseId;
            if (_cursor.isNull(_cursorIndexOfGoogleFormResponseId)) {
              _tmpGoogleFormResponseId = null;
            } else {
              _tmpGoogleFormResponseId = _cursor.getString(_cursorIndexOfGoogleFormResponseId);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new EvaluationEntity(_tmpId,_tmpFormationId,_tmpDateEvaluation,_tmpFlmMatricule,_tmpFlmNom,_tmpOrganisationContenu,_tmpQualitePedagogique,_tmpAdaptationPublic,_tmpMaitriseSujet,_tmpDisponibiliteFormateur,_tmpQualiteSupports,_tmpAtteinteObjectifs,_tmpApplicationTerrain,_tmpPropositionsAmelioration,_tmpCommentaireGeneral,_tmpCompetencesAcquises,_tmpStatut,_tmpGoogleFormResponseId,_tmpSyncedToFirebase,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Object getByFormationId(final long formationId,
      final Continuation<? super List<EvaluationEntity>> arg1) {
    final String _sql = "SELECT * FROM evaluations WHERE formationId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, formationId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<EvaluationEntity>>() {
      @Override
      @NonNull
      public List<EvaluationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfDateEvaluation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEvaluation");
          final int _cursorIndexOfFlmMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "flmMatricule");
          final int _cursorIndexOfFlmNom = CursorUtil.getColumnIndexOrThrow(_cursor, "flmNom");
          final int _cursorIndexOfOrganisationContenu = CursorUtil.getColumnIndexOrThrow(_cursor, "organisationContenu");
          final int _cursorIndexOfQualitePedagogique = CursorUtil.getColumnIndexOrThrow(_cursor, "qualitePedagogique");
          final int _cursorIndexOfAdaptationPublic = CursorUtil.getColumnIndexOrThrow(_cursor, "adaptationPublic");
          final int _cursorIndexOfMaitriseSujet = CursorUtil.getColumnIndexOrThrow(_cursor, "maitriseSujet");
          final int _cursorIndexOfDisponibiliteFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "disponibiliteFormateur");
          final int _cursorIndexOfQualiteSupports = CursorUtil.getColumnIndexOrThrow(_cursor, "qualiteSupports");
          final int _cursorIndexOfAtteinteObjectifs = CursorUtil.getColumnIndexOrThrow(_cursor, "atteinteObjectifs");
          final int _cursorIndexOfApplicationTerrain = CursorUtil.getColumnIndexOrThrow(_cursor, "applicationTerrain");
          final int _cursorIndexOfPropositionsAmelioration = CursorUtil.getColumnIndexOrThrow(_cursor, "propositionsAmelioration");
          final int _cursorIndexOfCommentaireGeneral = CursorUtil.getColumnIndexOrThrow(_cursor, "commentaireGeneral");
          final int _cursorIndexOfCompetencesAcquises = CursorUtil.getColumnIndexOrThrow(_cursor, "competencesAcquises");
          final int _cursorIndexOfStatut = CursorUtil.getColumnIndexOrThrow(_cursor, "statut");
          final int _cursorIndexOfGoogleFormResponseId = CursorUtil.getColumnIndexOrThrow(_cursor, "googleFormResponseId");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<EvaluationEntity> _result = new ArrayList<EvaluationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EvaluationEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpDateEvaluation;
            if (_cursor.isNull(_cursorIndexOfDateEvaluation)) {
              _tmpDateEvaluation = null;
            } else {
              _tmpDateEvaluation = _cursor.getString(_cursorIndexOfDateEvaluation);
            }
            final String _tmpFlmMatricule;
            if (_cursor.isNull(_cursorIndexOfFlmMatricule)) {
              _tmpFlmMatricule = null;
            } else {
              _tmpFlmMatricule = _cursor.getString(_cursorIndexOfFlmMatricule);
            }
            final String _tmpFlmNom;
            if (_cursor.isNull(_cursorIndexOfFlmNom)) {
              _tmpFlmNom = null;
            } else {
              _tmpFlmNom = _cursor.getString(_cursorIndexOfFlmNom);
            }
            final int _tmpOrganisationContenu;
            _tmpOrganisationContenu = _cursor.getInt(_cursorIndexOfOrganisationContenu);
            final int _tmpQualitePedagogique;
            _tmpQualitePedagogique = _cursor.getInt(_cursorIndexOfQualitePedagogique);
            final int _tmpAdaptationPublic;
            _tmpAdaptationPublic = _cursor.getInt(_cursorIndexOfAdaptationPublic);
            final int _tmpMaitriseSujet;
            _tmpMaitriseSujet = _cursor.getInt(_cursorIndexOfMaitriseSujet);
            final int _tmpDisponibiliteFormateur;
            _tmpDisponibiliteFormateur = _cursor.getInt(_cursorIndexOfDisponibiliteFormateur);
            final int _tmpQualiteSupports;
            _tmpQualiteSupports = _cursor.getInt(_cursorIndexOfQualiteSupports);
            final int _tmpAtteinteObjectifs;
            _tmpAtteinteObjectifs = _cursor.getInt(_cursorIndexOfAtteinteObjectifs);
            final int _tmpApplicationTerrain;
            _tmpApplicationTerrain = _cursor.getInt(_cursorIndexOfApplicationTerrain);
            final String _tmpPropositionsAmelioration;
            if (_cursor.isNull(_cursorIndexOfPropositionsAmelioration)) {
              _tmpPropositionsAmelioration = null;
            } else {
              _tmpPropositionsAmelioration = _cursor.getString(_cursorIndexOfPropositionsAmelioration);
            }
            final String _tmpCommentaireGeneral;
            if (_cursor.isNull(_cursorIndexOfCommentaireGeneral)) {
              _tmpCommentaireGeneral = null;
            } else {
              _tmpCommentaireGeneral = _cursor.getString(_cursorIndexOfCommentaireGeneral);
            }
            final String _tmpCompetencesAcquises;
            if (_cursor.isNull(_cursorIndexOfCompetencesAcquises)) {
              _tmpCompetencesAcquises = null;
            } else {
              _tmpCompetencesAcquises = _cursor.getString(_cursorIndexOfCompetencesAcquises);
            }
            final String _tmpStatut;
            if (_cursor.isNull(_cursorIndexOfStatut)) {
              _tmpStatut = null;
            } else {
              _tmpStatut = _cursor.getString(_cursorIndexOfStatut);
            }
            final String _tmpGoogleFormResponseId;
            if (_cursor.isNull(_cursorIndexOfGoogleFormResponseId)) {
              _tmpGoogleFormResponseId = null;
            } else {
              _tmpGoogleFormResponseId = _cursor.getString(_cursorIndexOfGoogleFormResponseId);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new EvaluationEntity(_tmpId,_tmpFormationId,_tmpDateEvaluation,_tmpFlmMatricule,_tmpFlmNom,_tmpOrganisationContenu,_tmpQualitePedagogique,_tmpAdaptationPublic,_tmpMaitriseSujet,_tmpDisponibiliteFormateur,_tmpQualiteSupports,_tmpAtteinteObjectifs,_tmpApplicationTerrain,_tmpPropositionsAmelioration,_tmpCommentaireGeneral,_tmpCompetencesAcquises,_tmpStatut,_tmpGoogleFormResponseId,_tmpSyncedToFirebase,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Object count(final Continuation<? super Integer> arg0) {
    final String _sql = "SELECT COUNT(*) FROM evaluations";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @Override
  public Object getUnsynced(final Continuation<? super List<EvaluationEntity>> arg0) {
    final String _sql = "SELECT * FROM evaluations WHERE syncedToFirebase = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<EvaluationEntity>>() {
      @Override
      @NonNull
      public List<EvaluationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfDateEvaluation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEvaluation");
          final int _cursorIndexOfFlmMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "flmMatricule");
          final int _cursorIndexOfFlmNom = CursorUtil.getColumnIndexOrThrow(_cursor, "flmNom");
          final int _cursorIndexOfOrganisationContenu = CursorUtil.getColumnIndexOrThrow(_cursor, "organisationContenu");
          final int _cursorIndexOfQualitePedagogique = CursorUtil.getColumnIndexOrThrow(_cursor, "qualitePedagogique");
          final int _cursorIndexOfAdaptationPublic = CursorUtil.getColumnIndexOrThrow(_cursor, "adaptationPublic");
          final int _cursorIndexOfMaitriseSujet = CursorUtil.getColumnIndexOrThrow(_cursor, "maitriseSujet");
          final int _cursorIndexOfDisponibiliteFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "disponibiliteFormateur");
          final int _cursorIndexOfQualiteSupports = CursorUtil.getColumnIndexOrThrow(_cursor, "qualiteSupports");
          final int _cursorIndexOfAtteinteObjectifs = CursorUtil.getColumnIndexOrThrow(_cursor, "atteinteObjectifs");
          final int _cursorIndexOfApplicationTerrain = CursorUtil.getColumnIndexOrThrow(_cursor, "applicationTerrain");
          final int _cursorIndexOfPropositionsAmelioration = CursorUtil.getColumnIndexOrThrow(_cursor, "propositionsAmelioration");
          final int _cursorIndexOfCommentaireGeneral = CursorUtil.getColumnIndexOrThrow(_cursor, "commentaireGeneral");
          final int _cursorIndexOfCompetencesAcquises = CursorUtil.getColumnIndexOrThrow(_cursor, "competencesAcquises");
          final int _cursorIndexOfStatut = CursorUtil.getColumnIndexOrThrow(_cursor, "statut");
          final int _cursorIndexOfGoogleFormResponseId = CursorUtil.getColumnIndexOrThrow(_cursor, "googleFormResponseId");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<EvaluationEntity> _result = new ArrayList<EvaluationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EvaluationEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpDateEvaluation;
            if (_cursor.isNull(_cursorIndexOfDateEvaluation)) {
              _tmpDateEvaluation = null;
            } else {
              _tmpDateEvaluation = _cursor.getString(_cursorIndexOfDateEvaluation);
            }
            final String _tmpFlmMatricule;
            if (_cursor.isNull(_cursorIndexOfFlmMatricule)) {
              _tmpFlmMatricule = null;
            } else {
              _tmpFlmMatricule = _cursor.getString(_cursorIndexOfFlmMatricule);
            }
            final String _tmpFlmNom;
            if (_cursor.isNull(_cursorIndexOfFlmNom)) {
              _tmpFlmNom = null;
            } else {
              _tmpFlmNom = _cursor.getString(_cursorIndexOfFlmNom);
            }
            final int _tmpOrganisationContenu;
            _tmpOrganisationContenu = _cursor.getInt(_cursorIndexOfOrganisationContenu);
            final int _tmpQualitePedagogique;
            _tmpQualitePedagogique = _cursor.getInt(_cursorIndexOfQualitePedagogique);
            final int _tmpAdaptationPublic;
            _tmpAdaptationPublic = _cursor.getInt(_cursorIndexOfAdaptationPublic);
            final int _tmpMaitriseSujet;
            _tmpMaitriseSujet = _cursor.getInt(_cursorIndexOfMaitriseSujet);
            final int _tmpDisponibiliteFormateur;
            _tmpDisponibiliteFormateur = _cursor.getInt(_cursorIndexOfDisponibiliteFormateur);
            final int _tmpQualiteSupports;
            _tmpQualiteSupports = _cursor.getInt(_cursorIndexOfQualiteSupports);
            final int _tmpAtteinteObjectifs;
            _tmpAtteinteObjectifs = _cursor.getInt(_cursorIndexOfAtteinteObjectifs);
            final int _tmpApplicationTerrain;
            _tmpApplicationTerrain = _cursor.getInt(_cursorIndexOfApplicationTerrain);
            final String _tmpPropositionsAmelioration;
            if (_cursor.isNull(_cursorIndexOfPropositionsAmelioration)) {
              _tmpPropositionsAmelioration = null;
            } else {
              _tmpPropositionsAmelioration = _cursor.getString(_cursorIndexOfPropositionsAmelioration);
            }
            final String _tmpCommentaireGeneral;
            if (_cursor.isNull(_cursorIndexOfCommentaireGeneral)) {
              _tmpCommentaireGeneral = null;
            } else {
              _tmpCommentaireGeneral = _cursor.getString(_cursorIndexOfCommentaireGeneral);
            }
            final String _tmpCompetencesAcquises;
            if (_cursor.isNull(_cursorIndexOfCompetencesAcquises)) {
              _tmpCompetencesAcquises = null;
            } else {
              _tmpCompetencesAcquises = _cursor.getString(_cursorIndexOfCompetencesAcquises);
            }
            final String _tmpStatut;
            if (_cursor.isNull(_cursorIndexOfStatut)) {
              _tmpStatut = null;
            } else {
              _tmpStatut = _cursor.getString(_cursorIndexOfStatut);
            }
            final String _tmpGoogleFormResponseId;
            if (_cursor.isNull(_cursorIndexOfGoogleFormResponseId)) {
              _tmpGoogleFormResponseId = null;
            } else {
              _tmpGoogleFormResponseId = _cursor.getString(_cursorIndexOfGoogleFormResponseId);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new EvaluationEntity(_tmpId,_tmpFormationId,_tmpDateEvaluation,_tmpFlmMatricule,_tmpFlmNom,_tmpOrganisationContenu,_tmpQualitePedagogique,_tmpAdaptationPublic,_tmpMaitriseSujet,_tmpDisponibiliteFormateur,_tmpQualiteSupports,_tmpAtteinteObjectifs,_tmpApplicationTerrain,_tmpPropositionsAmelioration,_tmpCommentaireGeneral,_tmpCompetencesAcquises,_tmpStatut,_tmpGoogleFormResponseId,_tmpSyncedToFirebase,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @Override
  public Object markSynced(final List<Long> ids, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("UPDATE evaluations SET syncedToFirebase = 1 WHERE id IN (");
        final int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        for (Long _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindLong(_argIndex, _item);
          }
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
