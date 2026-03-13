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
import com.ocp.evalformation.data.local.entity.FormationEntity;
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
public final class FormationDao_Impl implements FormationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FormationEntity> __insertionAdapterOfFormationEntity;

  private final EntityDeletionOrUpdateAdapter<FormationEntity> __updateAdapterOfFormationEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfResetIds;

  public FormationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFormationEntity = new EntityInsertionAdapter<FormationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `formations` (`id`,`collaborateurMatricule`,`themeId`,`debut`,`fin`,`syncedToFirebase`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FormationEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getCollaborateurMatricule() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCollaborateurMatricule());
        }
        statement.bindLong(3, entity.getThemeId());
        if (entity.getDebut() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDebut());
        }
        if (entity.getFin() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFin());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(6, _tmp);
      }
    };
    this.__updateAdapterOfFormationEntity = new EntityDeletionOrUpdateAdapter<FormationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `formations` SET `id` = ?,`collaborateurMatricule` = ?,`themeId` = ?,`debut` = ?,`fin` = ?,`syncedToFirebase` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FormationEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getCollaborateurMatricule() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCollaborateurMatricule());
        }
        statement.bindLong(3, entity.getThemeId());
        if (entity.getDebut() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDebut());
        }
        if (entity.getFin() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFin());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM formations";
        return _query;
      }
    };
    this.__preparedStmtOfResetIds = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM sqlite_sequence WHERE name='formations'";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final FormationEntity formation,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfFormationEntity.insertAndReturnId(formation);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<FormationEntity> formations,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfFormationEntity.insertAndReturnIdsList(formations);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final FormationEntity formation,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFormationEntity.handle(formation);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
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
  public Object resetIds(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfResetIds.acquire();
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
          __preparedStmtOfResetIds.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<FormationEntity>> getAllLive() {
    final String _sql = "SELECT * FROM formations ORDER BY debut DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"formations"}, false, new Callable<List<FormationEntity>>() {
      @Override
      @Nullable
      public List<FormationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCollaborateurMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "collaborateurMatricule");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfDebut = CursorUtil.getColumnIndexOrThrow(_cursor, "debut");
          final int _cursorIndexOfFin = CursorUtil.getColumnIndexOrThrow(_cursor, "fin");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<FormationEntity> _result = new ArrayList<FormationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FormationEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCollaborateurMatricule;
            if (_cursor.isNull(_cursorIndexOfCollaborateurMatricule)) {
              _tmpCollaborateurMatricule = null;
            } else {
              _tmpCollaborateurMatricule = _cursor.getString(_cursorIndexOfCollaborateurMatricule);
            }
            final long _tmpThemeId;
            _tmpThemeId = _cursor.getLong(_cursorIndexOfThemeId);
            final String _tmpDebut;
            if (_cursor.isNull(_cursorIndexOfDebut)) {
              _tmpDebut = null;
            } else {
              _tmpDebut = _cursor.getString(_cursorIndexOfDebut);
            }
            final String _tmpFin;
            if (_cursor.isNull(_cursorIndexOfFin)) {
              _tmpFin = null;
            } else {
              _tmpFin = _cursor.getString(_cursorIndexOfFin);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpSyncedToFirebase);
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
  public Object getAll(final Continuation<? super List<FormationEntity>> $completion) {
    final String _sql = "SELECT * FROM formations ORDER BY debut DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FormationEntity>>() {
      @Override
      @NonNull
      public List<FormationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCollaborateurMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "collaborateurMatricule");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfDebut = CursorUtil.getColumnIndexOrThrow(_cursor, "debut");
          final int _cursorIndexOfFin = CursorUtil.getColumnIndexOrThrow(_cursor, "fin");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<FormationEntity> _result = new ArrayList<FormationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FormationEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCollaborateurMatricule;
            if (_cursor.isNull(_cursorIndexOfCollaborateurMatricule)) {
              _tmpCollaborateurMatricule = null;
            } else {
              _tmpCollaborateurMatricule = _cursor.getString(_cursorIndexOfCollaborateurMatricule);
            }
            final long _tmpThemeId;
            _tmpThemeId = _cursor.getLong(_cursorIndexOfThemeId);
            final String _tmpDebut;
            if (_cursor.isNull(_cursorIndexOfDebut)) {
              _tmpDebut = null;
            } else {
              _tmpDebut = _cursor.getString(_cursorIndexOfDebut);
            }
            final String _tmpFin;
            if (_cursor.isNull(_cursorIndexOfFin)) {
              _tmpFin = null;
            } else {
              _tmpFin = _cursor.getString(_cursorIndexOfFin);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpSyncedToFirebase);
            _result.add(_item);
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
  public Object getById(final long id, final Continuation<? super FormationEntity> $completion) {
    final String _sql = "SELECT * FROM formations WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<FormationEntity>() {
      @Override
      @Nullable
      public FormationEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCollaborateurMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "collaborateurMatricule");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfDebut = CursorUtil.getColumnIndexOrThrow(_cursor, "debut");
          final int _cursorIndexOfFin = CursorUtil.getColumnIndexOrThrow(_cursor, "fin");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final FormationEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCollaborateurMatricule;
            if (_cursor.isNull(_cursorIndexOfCollaborateurMatricule)) {
              _tmpCollaborateurMatricule = null;
            } else {
              _tmpCollaborateurMatricule = _cursor.getString(_cursorIndexOfCollaborateurMatricule);
            }
            final long _tmpThemeId;
            _tmpThemeId = _cursor.getLong(_cursorIndexOfThemeId);
            final String _tmpDebut;
            if (_cursor.isNull(_cursorIndexOfDebut)) {
              _tmpDebut = null;
            } else {
              _tmpDebut = _cursor.getString(_cursorIndexOfDebut);
            }
            final String _tmpFin;
            if (_cursor.isNull(_cursorIndexOfFin)) {
              _tmpFin = null;
            } else {
              _tmpFin = _cursor.getString(_cursorIndexOfFin);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _result = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpSyncedToFirebase);
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
  public Object getByCollaborateur(final String matricule,
      final Continuation<? super List<FormationEntity>> $completion) {
    final String _sql = "SELECT * FROM formations WHERE collaborateurMatricule = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (matricule == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, matricule);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FormationEntity>>() {
      @Override
      @NonNull
      public List<FormationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCollaborateurMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "collaborateurMatricule");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfDebut = CursorUtil.getColumnIndexOrThrow(_cursor, "debut");
          final int _cursorIndexOfFin = CursorUtil.getColumnIndexOrThrow(_cursor, "fin");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<FormationEntity> _result = new ArrayList<FormationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FormationEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCollaborateurMatricule;
            if (_cursor.isNull(_cursorIndexOfCollaborateurMatricule)) {
              _tmpCollaborateurMatricule = null;
            } else {
              _tmpCollaborateurMatricule = _cursor.getString(_cursorIndexOfCollaborateurMatricule);
            }
            final long _tmpThemeId;
            _tmpThemeId = _cursor.getLong(_cursorIndexOfThemeId);
            final String _tmpDebut;
            if (_cursor.isNull(_cursorIndexOfDebut)) {
              _tmpDebut = null;
            } else {
              _tmpDebut = _cursor.getString(_cursorIndexOfDebut);
            }
            final String _tmpFin;
            if (_cursor.isNull(_cursorIndexOfFin)) {
              _tmpFin = null;
            } else {
              _tmpFin = _cursor.getString(_cursorIndexOfFin);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpSyncedToFirebase);
            _result.add(_item);
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
  public Object getByTheme(final long themeId,
      final Continuation<? super List<FormationEntity>> $completion) {
    final String _sql = "SELECT * FROM formations WHERE themeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, themeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FormationEntity>>() {
      @Override
      @NonNull
      public List<FormationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCollaborateurMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "collaborateurMatricule");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfDebut = CursorUtil.getColumnIndexOrThrow(_cursor, "debut");
          final int _cursorIndexOfFin = CursorUtil.getColumnIndexOrThrow(_cursor, "fin");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<FormationEntity> _result = new ArrayList<FormationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FormationEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCollaborateurMatricule;
            if (_cursor.isNull(_cursorIndexOfCollaborateurMatricule)) {
              _tmpCollaborateurMatricule = null;
            } else {
              _tmpCollaborateurMatricule = _cursor.getString(_cursorIndexOfCollaborateurMatricule);
            }
            final long _tmpThemeId;
            _tmpThemeId = _cursor.getLong(_cursorIndexOfThemeId);
            final String _tmpDebut;
            if (_cursor.isNull(_cursorIndexOfDebut)) {
              _tmpDebut = null;
            } else {
              _tmpDebut = _cursor.getString(_cursorIndexOfDebut);
            }
            final String _tmpFin;
            if (_cursor.isNull(_cursorIndexOfFin)) {
              _tmpFin = null;
            } else {
              _tmpFin = _cursor.getString(_cursorIndexOfFin);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpSyncedToFirebase);
            _result.add(_item);
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
    final String _sql = "SELECT COUNT(*) FROM formations";
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
  public Object getUnsynced(final Continuation<? super List<FormationEntity>> $completion) {
    final String _sql = "SELECT * FROM formations WHERE syncedToFirebase = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FormationEntity>>() {
      @Override
      @NonNull
      public List<FormationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCollaborateurMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "collaborateurMatricule");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfDebut = CursorUtil.getColumnIndexOrThrow(_cursor, "debut");
          final int _cursorIndexOfFin = CursorUtil.getColumnIndexOrThrow(_cursor, "fin");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<FormationEntity> _result = new ArrayList<FormationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FormationEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCollaborateurMatricule;
            if (_cursor.isNull(_cursorIndexOfCollaborateurMatricule)) {
              _tmpCollaborateurMatricule = null;
            } else {
              _tmpCollaborateurMatricule = _cursor.getString(_cursorIndexOfCollaborateurMatricule);
            }
            final long _tmpThemeId;
            _tmpThemeId = _cursor.getLong(_cursorIndexOfThemeId);
            final String _tmpDebut;
            if (_cursor.isNull(_cursorIndexOfDebut)) {
              _tmpDebut = null;
            } else {
              _tmpDebut = _cursor.getString(_cursorIndexOfDebut);
            }
            final String _tmpFin;
            if (_cursor.isNull(_cursorIndexOfFin)) {
              _tmpFin = null;
            } else {
              _tmpFin = _cursor.getString(_cursorIndexOfFin);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpSyncedToFirebase);
            _result.add(_item);
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
  public Object markSynced(final List<Long> ids, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("UPDATE formations SET syncedToFirebase = 1 WHERE id IN (");
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
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
