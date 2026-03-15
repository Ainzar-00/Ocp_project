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
import com.ocp.evalformation.data.local.entity.FlmEntity;
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
public final class FlmDao_Impl implements FlmDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FlmEntity> __insertionAdapterOfFlmEntity;

  private final EntityDeletionOrUpdateAdapter<FlmEntity> __updateAdapterOfFlmEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public FlmDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFlmEntity = new EntityInsertionAdapter<FlmEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `flms` (`matricule`,`nom`,`prenom`,`email`,`service`,`syncedToFirebase`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FlmEntity entity) {
        if (entity.getMatricule() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getMatricule());
        }
        if (entity.getNom() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNom());
        }
        if (entity.getPrenom() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPrenom());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getEmail());
        }
        if (entity.getService() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getService());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(6, _tmp);
      }
    };
    this.__updateAdapterOfFlmEntity = new EntityDeletionOrUpdateAdapter<FlmEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `flms` SET `matricule` = ?,`nom` = ?,`prenom` = ?,`email` = ?,`service` = ?,`syncedToFirebase` = ? WHERE `matricule` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FlmEntity entity) {
        if (entity.getMatricule() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getMatricule());
        }
        if (entity.getNom() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNom());
        }
        if (entity.getPrenom() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPrenom());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getEmail());
        }
        if (entity.getService() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getService());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getMatricule() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getMatricule());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM flms";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final FlmEntity flm, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfFlmEntity.insertAndReturnId(flm);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<FlmEntity> flms,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFlmEntity.insert(flms);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final FlmEntity flm, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFlmEntity.handle(flm);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAll(final List<FlmEntity> flms,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFlmEntity.handleMultiple(flms);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertFlms(final List<FlmEntity> flms,
      final Continuation<? super List<String>> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> FlmDao.DefaultImpls.upsertFlms(FlmDao_Impl.this, flms, __cont), $completion);
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
  public LiveData<List<FlmEntity>> getAllLive() {
    final String _sql = "SELECT * FROM flms ORDER BY nom ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"flms"}, false, new Callable<List<FlmEntity>>() {
      @Override
      @Nullable
      public List<FlmEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "matricule");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "prenom");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<FlmEntity> _result = new ArrayList<FlmEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FlmEntity _item;
            final String _tmpMatricule;
            if (_cursor.isNull(_cursorIndexOfMatricule)) {
              _tmpMatricule = null;
            } else {
              _tmpMatricule = _cursor.getString(_cursorIndexOfMatricule);
            }
            final String _tmpNom;
            if (_cursor.isNull(_cursorIndexOfNom)) {
              _tmpNom = null;
            } else {
              _tmpNom = _cursor.getString(_cursorIndexOfNom);
            }
            final String _tmpPrenom;
            if (_cursor.isNull(_cursorIndexOfPrenom)) {
              _tmpPrenom = null;
            } else {
              _tmpPrenom = _cursor.getString(_cursorIndexOfPrenom);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new FlmEntity(_tmpMatricule,_tmpNom,_tmpPrenom,_tmpEmail,_tmpService,_tmpSyncedToFirebase);
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
  public Object getAll(final Continuation<? super List<FlmEntity>> $completion) {
    final String _sql = "SELECT * FROM flms ORDER BY nom ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FlmEntity>>() {
      @Override
      @NonNull
      public List<FlmEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "matricule");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "prenom");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<FlmEntity> _result = new ArrayList<FlmEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FlmEntity _item;
            final String _tmpMatricule;
            if (_cursor.isNull(_cursorIndexOfMatricule)) {
              _tmpMatricule = null;
            } else {
              _tmpMatricule = _cursor.getString(_cursorIndexOfMatricule);
            }
            final String _tmpNom;
            if (_cursor.isNull(_cursorIndexOfNom)) {
              _tmpNom = null;
            } else {
              _tmpNom = _cursor.getString(_cursorIndexOfNom);
            }
            final String _tmpPrenom;
            if (_cursor.isNull(_cursorIndexOfPrenom)) {
              _tmpPrenom = null;
            } else {
              _tmpPrenom = _cursor.getString(_cursorIndexOfPrenom);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new FlmEntity(_tmpMatricule,_tmpNom,_tmpPrenom,_tmpEmail,_tmpService,_tmpSyncedToFirebase);
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
    final String _sql = "SELECT COUNT(*) FROM flms";
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
  public Object getByMatricule(final String matricule,
      final Continuation<? super FlmEntity> $completion) {
    final String _sql = "SELECT * FROM flms WHERE matricule = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (matricule == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, matricule);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<FlmEntity>() {
      @Override
      @Nullable
      public FlmEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "matricule");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "prenom");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final FlmEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpMatricule;
            if (_cursor.isNull(_cursorIndexOfMatricule)) {
              _tmpMatricule = null;
            } else {
              _tmpMatricule = _cursor.getString(_cursorIndexOfMatricule);
            }
            final String _tmpNom;
            if (_cursor.isNull(_cursorIndexOfNom)) {
              _tmpNom = null;
            } else {
              _tmpNom = _cursor.getString(_cursorIndexOfNom);
            }
            final String _tmpPrenom;
            if (_cursor.isNull(_cursorIndexOfPrenom)) {
              _tmpPrenom = null;
            } else {
              _tmpPrenom = _cursor.getString(_cursorIndexOfPrenom);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _result = new FlmEntity(_tmpMatricule,_tmpNom,_tmpPrenom,_tmpEmail,_tmpService,_tmpSyncedToFirebase);
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
  public Object getUnsynced(final Continuation<? super List<FlmEntity>> $completion) {
    final String _sql = "SELECT * FROM flms WHERE syncedToFirebase = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FlmEntity>>() {
      @Override
      @NonNull
      public List<FlmEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "matricule");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "prenom");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<FlmEntity> _result = new ArrayList<FlmEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FlmEntity _item;
            final String _tmpMatricule;
            if (_cursor.isNull(_cursorIndexOfMatricule)) {
              _tmpMatricule = null;
            } else {
              _tmpMatricule = _cursor.getString(_cursorIndexOfMatricule);
            }
            final String _tmpNom;
            if (_cursor.isNull(_cursorIndexOfNom)) {
              _tmpNom = null;
            } else {
              _tmpNom = _cursor.getString(_cursorIndexOfNom);
            }
            final String _tmpPrenom;
            if (_cursor.isNull(_cursorIndexOfPrenom)) {
              _tmpPrenom = null;
            } else {
              _tmpPrenom = _cursor.getString(_cursorIndexOfPrenom);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new FlmEntity(_tmpMatricule,_tmpNom,_tmpPrenom,_tmpEmail,_tmpService,_tmpSyncedToFirebase);
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
  public Object markSynced(final List<String> matricules,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("UPDATE flms SET syncedToFirebase = 1 WHERE matricule IN (");
        final int _inputSize = matricules.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        for (String _item : matricules) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindString(_argIndex, _item);
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
