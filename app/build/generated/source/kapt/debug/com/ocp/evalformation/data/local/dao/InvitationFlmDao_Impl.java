package com.ocp.evalformation.data.local.dao;

import android.database.Cursor;
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
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ocp.evalformation.data.local.entity.InvitationFlmEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
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
public final class InvitationFlmDao_Impl implements InvitationFlmDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<InvitationFlmEntity> __insertionAdapterOfInvitationFlmEntity;

  private final EntityDeletionOrUpdateAdapter<InvitationFlmEntity> __updateAdapterOfInvitationFlmEntity;

  private final SharedSQLiteStatement __preparedStmtOfMarkReplied;

  public InvitationFlmDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInvitationFlmEntity = new EntityInsertionAdapter<InvitationFlmEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `invitations_flm` (`id`,`formationId`,`matriculeCollaborateur`,`nomCompletCollaborateur`,`themeNom`,`emailFlm`,`nomFlm`,`googleFormUrl`,`statut`,`dateEnvoi`,`dateReponse`,`evaluationId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvitationFlmEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getFormationId());
        if (entity.getMatriculeCollaborateur() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMatriculeCollaborateur());
        }
        if (entity.getNomCompletCollaborateur() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getNomCompletCollaborateur());
        }
        if (entity.getThemeNom() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getThemeNom());
        }
        if (entity.getEmailFlm() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEmailFlm());
        }
        if (entity.getNomFlm() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNomFlm());
        }
        if (entity.getGoogleFormUrl() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getGoogleFormUrl());
        }
        if (entity.getStatut() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getStatut());
        }
        statement.bindLong(10, entity.getDateEnvoi());
        if (entity.getDateReponse() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getDateReponse());
        }
        if (entity.getEvaluationId() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getEvaluationId());
        }
      }
    };
    this.__updateAdapterOfInvitationFlmEntity = new EntityDeletionOrUpdateAdapter<InvitationFlmEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `invitations_flm` SET `id` = ?,`formationId` = ?,`matriculeCollaborateur` = ?,`nomCompletCollaborateur` = ?,`themeNom` = ?,`emailFlm` = ?,`nomFlm` = ?,`googleFormUrl` = ?,`statut` = ?,`dateEnvoi` = ?,`dateReponse` = ?,`evaluationId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvitationFlmEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getFormationId());
        if (entity.getMatriculeCollaborateur() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMatriculeCollaborateur());
        }
        if (entity.getNomCompletCollaborateur() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getNomCompletCollaborateur());
        }
        if (entity.getThemeNom() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getThemeNom());
        }
        if (entity.getEmailFlm() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEmailFlm());
        }
        if (entity.getNomFlm() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNomFlm());
        }
        if (entity.getGoogleFormUrl() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getGoogleFormUrl());
        }
        if (entity.getStatut() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getStatut());
        }
        statement.bindLong(10, entity.getDateEnvoi());
        if (entity.getDateReponse() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getDateReponse());
        }
        if (entity.getEvaluationId() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getEvaluationId());
        }
        statement.bindLong(13, entity.getId());
      }
    };
    this.__preparedStmtOfMarkReplied = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE invitations_flm SET statut = 'REPONDU', dateReponse = ?, evaluationId = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final InvitationFlmEntity invitation,
      final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfInvitationFlmEntity.insertAndReturnId(invitation);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object update(final InvitationFlmEntity invitation,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfInvitationFlmEntity.handle(invitation);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object markReplied(final long id, final long date, final long evalId,
      final Continuation<? super Unit> arg3) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkReplied.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, date);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, evalId);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, id);
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
          __preparedStmtOfMarkReplied.release(_stmt);
        }
      }
    }, arg3);
  }

  @Override
  public LiveData<List<InvitationFlmEntity>> getAllLive() {
    final String _sql = "SELECT * FROM invitations_flm ORDER BY dateEnvoi DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"invitations_flm"}, false, new Callable<List<InvitationFlmEntity>>() {
      @Override
      @Nullable
      public List<InvitationFlmEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfMatriculeCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "matriculeCollaborateur");
          final int _cursorIndexOfNomCompletCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "nomCompletCollaborateur");
          final int _cursorIndexOfThemeNom = CursorUtil.getColumnIndexOrThrow(_cursor, "themeNom");
          final int _cursorIndexOfEmailFlm = CursorUtil.getColumnIndexOrThrow(_cursor, "emailFlm");
          final int _cursorIndexOfNomFlm = CursorUtil.getColumnIndexOrThrow(_cursor, "nomFlm");
          final int _cursorIndexOfGoogleFormUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "googleFormUrl");
          final int _cursorIndexOfStatut = CursorUtil.getColumnIndexOrThrow(_cursor, "statut");
          final int _cursorIndexOfDateEnvoi = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEnvoi");
          final int _cursorIndexOfDateReponse = CursorUtil.getColumnIndexOrThrow(_cursor, "dateReponse");
          final int _cursorIndexOfEvaluationId = CursorUtil.getColumnIndexOrThrow(_cursor, "evaluationId");
          final List<InvitationFlmEntity> _result = new ArrayList<InvitationFlmEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvitationFlmEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpMatriculeCollaborateur;
            if (_cursor.isNull(_cursorIndexOfMatriculeCollaborateur)) {
              _tmpMatriculeCollaborateur = null;
            } else {
              _tmpMatriculeCollaborateur = _cursor.getString(_cursorIndexOfMatriculeCollaborateur);
            }
            final String _tmpNomCompletCollaborateur;
            if (_cursor.isNull(_cursorIndexOfNomCompletCollaborateur)) {
              _tmpNomCompletCollaborateur = null;
            } else {
              _tmpNomCompletCollaborateur = _cursor.getString(_cursorIndexOfNomCompletCollaborateur);
            }
            final String _tmpThemeNom;
            if (_cursor.isNull(_cursorIndexOfThemeNom)) {
              _tmpThemeNom = null;
            } else {
              _tmpThemeNom = _cursor.getString(_cursorIndexOfThemeNom);
            }
            final String _tmpEmailFlm;
            if (_cursor.isNull(_cursorIndexOfEmailFlm)) {
              _tmpEmailFlm = null;
            } else {
              _tmpEmailFlm = _cursor.getString(_cursorIndexOfEmailFlm);
            }
            final String _tmpNomFlm;
            if (_cursor.isNull(_cursorIndexOfNomFlm)) {
              _tmpNomFlm = null;
            } else {
              _tmpNomFlm = _cursor.getString(_cursorIndexOfNomFlm);
            }
            final String _tmpGoogleFormUrl;
            if (_cursor.isNull(_cursorIndexOfGoogleFormUrl)) {
              _tmpGoogleFormUrl = null;
            } else {
              _tmpGoogleFormUrl = _cursor.getString(_cursorIndexOfGoogleFormUrl);
            }
            final String _tmpStatut;
            if (_cursor.isNull(_cursorIndexOfStatut)) {
              _tmpStatut = null;
            } else {
              _tmpStatut = _cursor.getString(_cursorIndexOfStatut);
            }
            final long _tmpDateEnvoi;
            _tmpDateEnvoi = _cursor.getLong(_cursorIndexOfDateEnvoi);
            final Long _tmpDateReponse;
            if (_cursor.isNull(_cursorIndexOfDateReponse)) {
              _tmpDateReponse = null;
            } else {
              _tmpDateReponse = _cursor.getLong(_cursorIndexOfDateReponse);
            }
            final Long _tmpEvaluationId;
            if (_cursor.isNull(_cursorIndexOfEvaluationId)) {
              _tmpEvaluationId = null;
            } else {
              _tmpEvaluationId = _cursor.getLong(_cursorIndexOfEvaluationId);
            }
            _item = new InvitationFlmEntity(_tmpId,_tmpFormationId,_tmpMatriculeCollaborateur,_tmpNomCompletCollaborateur,_tmpThemeNom,_tmpEmailFlm,_tmpNomFlm,_tmpGoogleFormUrl,_tmpStatut,_tmpDateEnvoi,_tmpDateReponse,_tmpEvaluationId);
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
  public LiveData<List<InvitationFlmEntity>> getPendingLive() {
    final String _sql = "SELECT * FROM invitations_flm WHERE statut = 'ENVOYE'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"invitations_flm"}, false, new Callable<List<InvitationFlmEntity>>() {
      @Override
      @Nullable
      public List<InvitationFlmEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfMatriculeCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "matriculeCollaborateur");
          final int _cursorIndexOfNomCompletCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "nomCompletCollaborateur");
          final int _cursorIndexOfThemeNom = CursorUtil.getColumnIndexOrThrow(_cursor, "themeNom");
          final int _cursorIndexOfEmailFlm = CursorUtil.getColumnIndexOrThrow(_cursor, "emailFlm");
          final int _cursorIndexOfNomFlm = CursorUtil.getColumnIndexOrThrow(_cursor, "nomFlm");
          final int _cursorIndexOfGoogleFormUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "googleFormUrl");
          final int _cursorIndexOfStatut = CursorUtil.getColumnIndexOrThrow(_cursor, "statut");
          final int _cursorIndexOfDateEnvoi = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEnvoi");
          final int _cursorIndexOfDateReponse = CursorUtil.getColumnIndexOrThrow(_cursor, "dateReponse");
          final int _cursorIndexOfEvaluationId = CursorUtil.getColumnIndexOrThrow(_cursor, "evaluationId");
          final List<InvitationFlmEntity> _result = new ArrayList<InvitationFlmEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvitationFlmEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpMatriculeCollaborateur;
            if (_cursor.isNull(_cursorIndexOfMatriculeCollaborateur)) {
              _tmpMatriculeCollaborateur = null;
            } else {
              _tmpMatriculeCollaborateur = _cursor.getString(_cursorIndexOfMatriculeCollaborateur);
            }
            final String _tmpNomCompletCollaborateur;
            if (_cursor.isNull(_cursorIndexOfNomCompletCollaborateur)) {
              _tmpNomCompletCollaborateur = null;
            } else {
              _tmpNomCompletCollaborateur = _cursor.getString(_cursorIndexOfNomCompletCollaborateur);
            }
            final String _tmpThemeNom;
            if (_cursor.isNull(_cursorIndexOfThemeNom)) {
              _tmpThemeNom = null;
            } else {
              _tmpThemeNom = _cursor.getString(_cursorIndexOfThemeNom);
            }
            final String _tmpEmailFlm;
            if (_cursor.isNull(_cursorIndexOfEmailFlm)) {
              _tmpEmailFlm = null;
            } else {
              _tmpEmailFlm = _cursor.getString(_cursorIndexOfEmailFlm);
            }
            final String _tmpNomFlm;
            if (_cursor.isNull(_cursorIndexOfNomFlm)) {
              _tmpNomFlm = null;
            } else {
              _tmpNomFlm = _cursor.getString(_cursorIndexOfNomFlm);
            }
            final String _tmpGoogleFormUrl;
            if (_cursor.isNull(_cursorIndexOfGoogleFormUrl)) {
              _tmpGoogleFormUrl = null;
            } else {
              _tmpGoogleFormUrl = _cursor.getString(_cursorIndexOfGoogleFormUrl);
            }
            final String _tmpStatut;
            if (_cursor.isNull(_cursorIndexOfStatut)) {
              _tmpStatut = null;
            } else {
              _tmpStatut = _cursor.getString(_cursorIndexOfStatut);
            }
            final long _tmpDateEnvoi;
            _tmpDateEnvoi = _cursor.getLong(_cursorIndexOfDateEnvoi);
            final Long _tmpDateReponse;
            if (_cursor.isNull(_cursorIndexOfDateReponse)) {
              _tmpDateReponse = null;
            } else {
              _tmpDateReponse = _cursor.getLong(_cursorIndexOfDateReponse);
            }
            final Long _tmpEvaluationId;
            if (_cursor.isNull(_cursorIndexOfEvaluationId)) {
              _tmpEvaluationId = null;
            } else {
              _tmpEvaluationId = _cursor.getLong(_cursorIndexOfEvaluationId);
            }
            _item = new InvitationFlmEntity(_tmpId,_tmpFormationId,_tmpMatriculeCollaborateur,_tmpNomCompletCollaborateur,_tmpThemeNom,_tmpEmailFlm,_tmpNomFlm,_tmpGoogleFormUrl,_tmpStatut,_tmpDateEnvoi,_tmpDateReponse,_tmpEvaluationId);
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
  public LiveData<Integer> countPendingLive() {
    final String _sql = "SELECT COUNT(*) FROM invitations_flm WHERE statut = 'ENVOYE'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"invitations_flm"}, false, new Callable<Integer>() {
      @Override
      @Nullable
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
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
