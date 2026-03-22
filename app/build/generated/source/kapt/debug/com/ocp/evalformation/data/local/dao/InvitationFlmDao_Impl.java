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
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ocp.evalformation.data.local.Converters;
import com.ocp.evalformation.data.local.entity.InvitationFlmEntity;
import com.ocp.evalformation.data.local.entity.InvitationStatus;
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

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<InvitationFlmEntity> __updateAdapterOfInvitationFlmEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateStatut;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsReplied;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public InvitationFlmDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInvitationFlmEntity = new EntityInsertionAdapter<InvitationFlmEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `invitations_flm` (`id`,`formationId`,`datesFormation`,`formateur`,`matriculeCollaborateur`,`nomCompletCollaborateur`,`service`,`themeNom`,`themeObjectives`,`emailFlm`,`nomFlm`,`statut`,`dateEnvoi`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvitationFlmEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getFormationId());
        if (entity.getDatesFormation() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDatesFormation());
        }
        if (entity.getFormateur() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getFormateur());
        }
        if (entity.getMatriculeCollaborateur() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getMatriculeCollaborateur());
        }
        if (entity.getNomCompletCollaborateur() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getNomCompletCollaborateur());
        }
        if (entity.getService() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getService());
        }
        if (entity.getThemeNom() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getThemeNom());
        }
        final String _tmp = __converters.fromStringList(entity.getThemeObjectives());
        if (_tmp == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp);
        }
        if (entity.getEmailFlm() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getEmailFlm());
        }
        if (entity.getNomFlm() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getNomFlm());
        }
        final String _tmp_1 = __converters.fromStatus(entity.getStatut());
        if (_tmp_1 == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, _tmp_1);
        }
        statement.bindLong(13, entity.getDateEnvoi());
      }
    };
    this.__updateAdapterOfInvitationFlmEntity = new EntityDeletionOrUpdateAdapter<InvitationFlmEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `invitations_flm` SET `id` = ?,`formationId` = ?,`datesFormation` = ?,`formateur` = ?,`matriculeCollaborateur` = ?,`nomCompletCollaborateur` = ?,`service` = ?,`themeNom` = ?,`themeObjectives` = ?,`emailFlm` = ?,`nomFlm` = ?,`statut` = ?,`dateEnvoi` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvitationFlmEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getFormationId());
        if (entity.getDatesFormation() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDatesFormation());
        }
        if (entity.getFormateur() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getFormateur());
        }
        if (entity.getMatriculeCollaborateur() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getMatriculeCollaborateur());
        }
        if (entity.getNomCompletCollaborateur() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getNomCompletCollaborateur());
        }
        if (entity.getService() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getService());
        }
        if (entity.getThemeNom() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getThemeNom());
        }
        final String _tmp = __converters.fromStringList(entity.getThemeObjectives());
        if (_tmp == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp);
        }
        if (entity.getEmailFlm() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getEmailFlm());
        }
        if (entity.getNomFlm() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getNomFlm());
        }
        final String _tmp_1 = __converters.fromStatus(entity.getStatut());
        if (_tmp_1 == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, _tmp_1);
        }
        statement.bindLong(13, entity.getDateEnvoi());
        statement.bindLong(14, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateStatut = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE invitations_flm SET statut = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAsReplied = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE invitations_flm SET statut = 'REPONDUE' WHERE formationId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM invitations_flm";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final InvitationFlmEntity invitation,
      final Continuation<? super Long> $completion) {
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
    }, $completion);
  }

  @Override
  public Object update(final InvitationFlmEntity invitation,
      final Continuation<? super Unit> $completion) {
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
    }, $completion);
  }

  @Override
  public Object updateStatut(final long id, final InvitationStatus statut,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateStatut.acquire();
        int _argIndex = 1;
        final String _tmp = __converters.fromStatus(statut);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp);
        }
        _argIndex = 2;
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
          __preparedStmtOfUpdateStatut.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsReplied(final long formationId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsReplied.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, formationId);
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
          __preparedStmtOfMarkAsReplied.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
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
    }, $completion);
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
          final int _cursorIndexOfDatesFormation = CursorUtil.getColumnIndexOrThrow(_cursor, "datesFormation");
          final int _cursorIndexOfFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "formateur");
          final int _cursorIndexOfMatriculeCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "matriculeCollaborateur");
          final int _cursorIndexOfNomCompletCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "nomCompletCollaborateur");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfThemeNom = CursorUtil.getColumnIndexOrThrow(_cursor, "themeNom");
          final int _cursorIndexOfThemeObjectives = CursorUtil.getColumnIndexOrThrow(_cursor, "themeObjectives");
          final int _cursorIndexOfEmailFlm = CursorUtil.getColumnIndexOrThrow(_cursor, "emailFlm");
          final int _cursorIndexOfNomFlm = CursorUtil.getColumnIndexOrThrow(_cursor, "nomFlm");
          final int _cursorIndexOfStatut = CursorUtil.getColumnIndexOrThrow(_cursor, "statut");
          final int _cursorIndexOfDateEnvoi = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEnvoi");
          final List<InvitationFlmEntity> _result = new ArrayList<InvitationFlmEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvitationFlmEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpDatesFormation;
            if (_cursor.isNull(_cursorIndexOfDatesFormation)) {
              _tmpDatesFormation = null;
            } else {
              _tmpDatesFormation = _cursor.getString(_cursorIndexOfDatesFormation);
            }
            final String _tmpFormateur;
            if (_cursor.isNull(_cursorIndexOfFormateur)) {
              _tmpFormateur = null;
            } else {
              _tmpFormateur = _cursor.getString(_cursorIndexOfFormateur);
            }
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
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final String _tmpThemeNom;
            if (_cursor.isNull(_cursorIndexOfThemeNom)) {
              _tmpThemeNom = null;
            } else {
              _tmpThemeNom = _cursor.getString(_cursorIndexOfThemeNom);
            }
            final List<String> _tmpThemeObjectives;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfThemeObjectives)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfThemeObjectives);
            }
            _tmpThemeObjectives = __converters.toStringList(_tmp);
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
            final InvitationStatus _tmpStatut;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatut)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatut);
            }
            _tmpStatut = __converters.toStatus(_tmp_1);
            final long _tmpDateEnvoi;
            _tmpDateEnvoi = _cursor.getLong(_cursorIndexOfDateEnvoi);
            _item = new InvitationFlmEntity(_tmpId,_tmpFormationId,_tmpDatesFormation,_tmpFormateur,_tmpMatriculeCollaborateur,_tmpNomCompletCollaborateur,_tmpService,_tmpThemeNom,_tmpThemeObjectives,_tmpEmailFlm,_tmpNomFlm,_tmpStatut,_tmpDateEnvoi);
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
    final String _sql = "SELECT * FROM invitations_flm WHERE statut = 'EN_ATTENTE' ORDER BY dateEnvoi DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"invitations_flm"}, false, new Callable<List<InvitationFlmEntity>>() {
      @Override
      @Nullable
      public List<InvitationFlmEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfDatesFormation = CursorUtil.getColumnIndexOrThrow(_cursor, "datesFormation");
          final int _cursorIndexOfFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "formateur");
          final int _cursorIndexOfMatriculeCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "matriculeCollaborateur");
          final int _cursorIndexOfNomCompletCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "nomCompletCollaborateur");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfThemeNom = CursorUtil.getColumnIndexOrThrow(_cursor, "themeNom");
          final int _cursorIndexOfThemeObjectives = CursorUtil.getColumnIndexOrThrow(_cursor, "themeObjectives");
          final int _cursorIndexOfEmailFlm = CursorUtil.getColumnIndexOrThrow(_cursor, "emailFlm");
          final int _cursorIndexOfNomFlm = CursorUtil.getColumnIndexOrThrow(_cursor, "nomFlm");
          final int _cursorIndexOfStatut = CursorUtil.getColumnIndexOrThrow(_cursor, "statut");
          final int _cursorIndexOfDateEnvoi = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEnvoi");
          final List<InvitationFlmEntity> _result = new ArrayList<InvitationFlmEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvitationFlmEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpDatesFormation;
            if (_cursor.isNull(_cursorIndexOfDatesFormation)) {
              _tmpDatesFormation = null;
            } else {
              _tmpDatesFormation = _cursor.getString(_cursorIndexOfDatesFormation);
            }
            final String _tmpFormateur;
            if (_cursor.isNull(_cursorIndexOfFormateur)) {
              _tmpFormateur = null;
            } else {
              _tmpFormateur = _cursor.getString(_cursorIndexOfFormateur);
            }
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
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final String _tmpThemeNom;
            if (_cursor.isNull(_cursorIndexOfThemeNom)) {
              _tmpThemeNom = null;
            } else {
              _tmpThemeNom = _cursor.getString(_cursorIndexOfThemeNom);
            }
            final List<String> _tmpThemeObjectives;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfThemeObjectives)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfThemeObjectives);
            }
            _tmpThemeObjectives = __converters.toStringList(_tmp);
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
            final InvitationStatus _tmpStatut;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatut)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatut);
            }
            _tmpStatut = __converters.toStatus(_tmp_1);
            final long _tmpDateEnvoi;
            _tmpDateEnvoi = _cursor.getLong(_cursorIndexOfDateEnvoi);
            _item = new InvitationFlmEntity(_tmpId,_tmpFormationId,_tmpDatesFormation,_tmpFormateur,_tmpMatriculeCollaborateur,_tmpNomCompletCollaborateur,_tmpService,_tmpThemeNom,_tmpThemeObjectives,_tmpEmailFlm,_tmpNomFlm,_tmpStatut,_tmpDateEnvoi);
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
    final String _sql = "SELECT COUNT(*) FROM invitations_flm WHERE statut = 'EN_ATTENTE'";
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

  @Override
  public Object getByFormationId(final long formationId,
      final Continuation<? super InvitationFlmEntity> $completion) {
    final String _sql = "SELECT * FROM invitations_flm WHERE formationId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, formationId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<InvitationFlmEntity>() {
      @Override
      @Nullable
      public InvitationFlmEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfDatesFormation = CursorUtil.getColumnIndexOrThrow(_cursor, "datesFormation");
          final int _cursorIndexOfFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "formateur");
          final int _cursorIndexOfMatriculeCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "matriculeCollaborateur");
          final int _cursorIndexOfNomCompletCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "nomCompletCollaborateur");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfThemeNom = CursorUtil.getColumnIndexOrThrow(_cursor, "themeNom");
          final int _cursorIndexOfThemeObjectives = CursorUtil.getColumnIndexOrThrow(_cursor, "themeObjectives");
          final int _cursorIndexOfEmailFlm = CursorUtil.getColumnIndexOrThrow(_cursor, "emailFlm");
          final int _cursorIndexOfNomFlm = CursorUtil.getColumnIndexOrThrow(_cursor, "nomFlm");
          final int _cursorIndexOfStatut = CursorUtil.getColumnIndexOrThrow(_cursor, "statut");
          final int _cursorIndexOfDateEnvoi = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEnvoi");
          final InvitationFlmEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpDatesFormation;
            if (_cursor.isNull(_cursorIndexOfDatesFormation)) {
              _tmpDatesFormation = null;
            } else {
              _tmpDatesFormation = _cursor.getString(_cursorIndexOfDatesFormation);
            }
            final String _tmpFormateur;
            if (_cursor.isNull(_cursorIndexOfFormateur)) {
              _tmpFormateur = null;
            } else {
              _tmpFormateur = _cursor.getString(_cursorIndexOfFormateur);
            }
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
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final String _tmpThemeNom;
            if (_cursor.isNull(_cursorIndexOfThemeNom)) {
              _tmpThemeNom = null;
            } else {
              _tmpThemeNom = _cursor.getString(_cursorIndexOfThemeNom);
            }
            final List<String> _tmpThemeObjectives;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfThemeObjectives)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfThemeObjectives);
            }
            _tmpThemeObjectives = __converters.toStringList(_tmp);
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
            final InvitationStatus _tmpStatut;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatut)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatut);
            }
            _tmpStatut = __converters.toStatus(_tmp_1);
            final long _tmpDateEnvoi;
            _tmpDateEnvoi = _cursor.getLong(_cursorIndexOfDateEnvoi);
            _result = new InvitationFlmEntity(_tmpId,_tmpFormationId,_tmpDatesFormation,_tmpFormateur,_tmpMatriculeCollaborateur,_tmpNomCompletCollaborateur,_tmpService,_tmpThemeNom,_tmpThemeObjectives,_tmpEmailFlm,_tmpNomFlm,_tmpStatut,_tmpDateEnvoi);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object count(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM invitations_flm";
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
    }, $completion);
  }

  @Override
  public LiveData<Integer> countEnAttenteLive() {
    final String _sql = "SELECT COUNT(*) FROM invitations_flm WHERE statut = 'EN_ATTENTE'";
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
