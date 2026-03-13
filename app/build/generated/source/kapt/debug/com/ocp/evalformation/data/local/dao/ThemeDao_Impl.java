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
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ocp.evalformation.data.local.entity.ThemeEntity;
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
public final class ThemeDao_Impl implements ThemeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ThemeEntity> __insertionAdapterOfThemeEntity;

  private final EntityInsertionAdapter<ThemeEntity> __insertionAdapterOfThemeEntity_1;

  private final EntityDeletionOrUpdateAdapter<ThemeEntity> __updateAdapterOfThemeEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfResetIds;

  public ThemeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfThemeEntity = new EntityInsertionAdapter<ThemeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `themes` (`id`,`nom`,`objectifPedagogique`,`syncedToFirebase`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ThemeEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNom() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNom());
        }
        if (entity.getObjectifPedagogique() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getObjectifPedagogique());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(4, _tmp);
      }
    };
    this.__insertionAdapterOfThemeEntity_1 = new EntityInsertionAdapter<ThemeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `themes` (`id`,`nom`,`objectifPedagogique`,`syncedToFirebase`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ThemeEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNom() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNom());
        }
        if (entity.getObjectifPedagogique() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getObjectifPedagogique());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(4, _tmp);
      }
    };
    this.__updateAdapterOfThemeEntity = new EntityDeletionOrUpdateAdapter<ThemeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `themes` SET `id` = ?,`nom` = ?,`objectifPedagogique` = ?,`syncedToFirebase` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ThemeEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNom() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNom());
        }
        if (entity.getObjectifPedagogique() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getObjectifPedagogique());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindLong(5, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM themes";
        return _query;
      }
    };
    this.__preparedStmtOfResetIds = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM sqlite_sequence WHERE name='themes'";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<ThemeEntity> themes,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfThemeEntity.insertAndReturnIdsList(themes);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insert(final ThemeEntity theme, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfThemeEntity_1.insertAndReturnId(theme);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final ThemeEntity theme, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfThemeEntity.handle(theme);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAll(final List<ThemeEntity> themes,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfThemeEntity.handleMultiple(themes);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertThemes(final List<ThemeEntity> themes,
      final Continuation<? super List<Long>> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> ThemeDao.DefaultImpls.upsertThemes(ThemeDao_Impl.this, themes, __cont), $completion);
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
  public Object count(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM themes";
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
  public LiveData<List<ThemeEntity>> getAllLive() {
    final String _sql = "SELECT * FROM themes ORDER BY nom ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"themes"}, false, new Callable<List<ThemeEntity>>() {
      @Override
      @Nullable
      public List<ThemeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfObjectifPedagogique = CursorUtil.getColumnIndexOrThrow(_cursor, "objectifPedagogique");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<ThemeEntity> _result = new ArrayList<ThemeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ThemeEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNom;
            if (_cursor.isNull(_cursorIndexOfNom)) {
              _tmpNom = null;
            } else {
              _tmpNom = _cursor.getString(_cursorIndexOfNom);
            }
            final String _tmpObjectifPedagogique;
            if (_cursor.isNull(_cursorIndexOfObjectifPedagogique)) {
              _tmpObjectifPedagogique = null;
            } else {
              _tmpObjectifPedagogique = _cursor.getString(_cursorIndexOfObjectifPedagogique);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new ThemeEntity(_tmpId,_tmpNom,_tmpObjectifPedagogique,_tmpSyncedToFirebase);
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
  public Object getAll(final Continuation<? super List<ThemeEntity>> $completion) {
    final String _sql = "SELECT * FROM themes ORDER BY nom ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ThemeEntity>>() {
      @Override
      @NonNull
      public List<ThemeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfObjectifPedagogique = CursorUtil.getColumnIndexOrThrow(_cursor, "objectifPedagogique");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<ThemeEntity> _result = new ArrayList<ThemeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ThemeEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNom;
            if (_cursor.isNull(_cursorIndexOfNom)) {
              _tmpNom = null;
            } else {
              _tmpNom = _cursor.getString(_cursorIndexOfNom);
            }
            final String _tmpObjectifPedagogique;
            if (_cursor.isNull(_cursorIndexOfObjectifPedagogique)) {
              _tmpObjectifPedagogique = null;
            } else {
              _tmpObjectifPedagogique = _cursor.getString(_cursorIndexOfObjectifPedagogique);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new ThemeEntity(_tmpId,_tmpNom,_tmpObjectifPedagogique,_tmpSyncedToFirebase);
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
  public Object getById(final long id, final Continuation<? super ThemeEntity> $completion) {
    final String _sql = "SELECT * FROM themes WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ThemeEntity>() {
      @Override
      @Nullable
      public ThemeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfObjectifPedagogique = CursorUtil.getColumnIndexOrThrow(_cursor, "objectifPedagogique");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final ThemeEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNom;
            if (_cursor.isNull(_cursorIndexOfNom)) {
              _tmpNom = null;
            } else {
              _tmpNom = _cursor.getString(_cursorIndexOfNom);
            }
            final String _tmpObjectifPedagogique;
            if (_cursor.isNull(_cursorIndexOfObjectifPedagogique)) {
              _tmpObjectifPedagogique = null;
            } else {
              _tmpObjectifPedagogique = _cursor.getString(_cursorIndexOfObjectifPedagogique);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _result = new ThemeEntity(_tmpId,_tmpNom,_tmpObjectifPedagogique,_tmpSyncedToFirebase);
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
  public Object getByName(final String nom, final Continuation<? super ThemeEntity> $completion) {
    final String _sql = "SELECT * FROM themes WHERE nom = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (nom == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nom);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ThemeEntity>() {
      @Override
      @Nullable
      public ThemeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfObjectifPedagogique = CursorUtil.getColumnIndexOrThrow(_cursor, "objectifPedagogique");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final ThemeEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNom;
            if (_cursor.isNull(_cursorIndexOfNom)) {
              _tmpNom = null;
            } else {
              _tmpNom = _cursor.getString(_cursorIndexOfNom);
            }
            final String _tmpObjectifPedagogique;
            if (_cursor.isNull(_cursorIndexOfObjectifPedagogique)) {
              _tmpObjectifPedagogique = null;
            } else {
              _tmpObjectifPedagogique = _cursor.getString(_cursorIndexOfObjectifPedagogique);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _result = new ThemeEntity(_tmpId,_tmpNom,_tmpObjectifPedagogique,_tmpSyncedToFirebase);
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
  public Object getUnsynced(final Continuation<? super List<ThemeEntity>> $completion) {
    final String _sql = "SELECT * FROM themes WHERE syncedToFirebase = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ThemeEntity>>() {
      @Override
      @NonNull
      public List<ThemeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfObjectifPedagogique = CursorUtil.getColumnIndexOrThrow(_cursor, "objectifPedagogique");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<ThemeEntity> _result = new ArrayList<ThemeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ThemeEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpNom;
            if (_cursor.isNull(_cursorIndexOfNom)) {
              _tmpNom = null;
            } else {
              _tmpNom = _cursor.getString(_cursorIndexOfNom);
            }
            final String _tmpObjectifPedagogique;
            if (_cursor.isNull(_cursorIndexOfObjectifPedagogique)) {
              _tmpObjectifPedagogique = null;
            } else {
              _tmpObjectifPedagogique = _cursor.getString(_cursorIndexOfObjectifPedagogique);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new ThemeEntity(_tmpId,_tmpNom,_tmpObjectifPedagogique,_tmpSyncedToFirebase);
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
        _stringBuilder.append("UPDATE themes SET syncedToFirebase = 1 WHERE id IN (");
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
