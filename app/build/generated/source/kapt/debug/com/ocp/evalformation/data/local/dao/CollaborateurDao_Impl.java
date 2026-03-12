package com.ocp.evalformation.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ocp.evalformation.data.local.entity.CollaborateurEntity;
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
public final class CollaborateurDao_Impl implements CollaborateurDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CollaborateurEntity> __insertionAdapterOfCollaborateurEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public CollaborateurDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCollaborateurEntity = new EntityInsertionAdapter<CollaborateurEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `collaborateurs` (`matricule`,`nom`,`prenom`,`service`,`flmMatricule`,`syncedToFirebase`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CollaborateurEntity entity) {
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
        if (entity.getService() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getService());
        }
        if (entity.getFlmMatricule() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFlmMatricule());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(6, _tmp);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM collaborateurs";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final CollaborateurEntity collaborateur,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCollaborateurEntity.insertAndReturnId(collaborateur);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<CollaborateurEntity> collaborateurs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCollaborateurEntity.insert(collaborateurs);
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
  public LiveData<List<CollaborateurEntity>> getAllLive() {
    final String _sql = "SELECT * FROM collaborateurs ORDER BY nom ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"collaborateurs"}, false, new Callable<List<CollaborateurEntity>>() {
      @Override
      @Nullable
      public List<CollaborateurEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "matricule");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "prenom");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfFlmMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "flmMatricule");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<CollaborateurEntity> _result = new ArrayList<CollaborateurEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CollaborateurEntity _item;
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
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final String _tmpFlmMatricule;
            if (_cursor.isNull(_cursorIndexOfFlmMatricule)) {
              _tmpFlmMatricule = null;
            } else {
              _tmpFlmMatricule = _cursor.getString(_cursorIndexOfFlmMatricule);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new CollaborateurEntity(_tmpMatricule,_tmpNom,_tmpPrenom,_tmpService,_tmpFlmMatricule,_tmpSyncedToFirebase);
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
  public Object getAll(final Continuation<? super List<CollaborateurEntity>> $completion) {
    final String _sql = "SELECT * FROM collaborateurs ORDER BY nom ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CollaborateurEntity>>() {
      @Override
      @NonNull
      public List<CollaborateurEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "matricule");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "prenom");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfFlmMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "flmMatricule");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<CollaborateurEntity> _result = new ArrayList<CollaborateurEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CollaborateurEntity _item;
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
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final String _tmpFlmMatricule;
            if (_cursor.isNull(_cursorIndexOfFlmMatricule)) {
              _tmpFlmMatricule = null;
            } else {
              _tmpFlmMatricule = _cursor.getString(_cursorIndexOfFlmMatricule);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new CollaborateurEntity(_tmpMatricule,_tmpNom,_tmpPrenom,_tmpService,_tmpFlmMatricule,_tmpSyncedToFirebase);
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
  public Object getByMatricule(final String matricule,
      final Continuation<? super CollaborateurEntity> $completion) {
    final String _sql = "SELECT * FROM collaborateurs WHERE matricule = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (matricule == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, matricule);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CollaborateurEntity>() {
      @Override
      @Nullable
      public CollaborateurEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "matricule");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "prenom");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfFlmMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "flmMatricule");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final CollaborateurEntity _result;
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
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final String _tmpFlmMatricule;
            if (_cursor.isNull(_cursorIndexOfFlmMatricule)) {
              _tmpFlmMatricule = null;
            } else {
              _tmpFlmMatricule = _cursor.getString(_cursorIndexOfFlmMatricule);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _result = new CollaborateurEntity(_tmpMatricule,_tmpNom,_tmpPrenom,_tmpService,_tmpFlmMatricule,_tmpSyncedToFirebase);
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
  public Object getAllMatricules(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT matricule FROM collaborateurs";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
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
    final String _sql = "SELECT COUNT(*) FROM collaborateurs";
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
  public Object getUnsynced(final Continuation<? super List<CollaborateurEntity>> $completion) {
    final String _sql = "SELECT * FROM collaborateurs WHERE syncedToFirebase = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CollaborateurEntity>>() {
      @Override
      @NonNull
      public List<CollaborateurEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "matricule");
          final int _cursorIndexOfNom = CursorUtil.getColumnIndexOrThrow(_cursor, "nom");
          final int _cursorIndexOfPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "prenom");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfFlmMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "flmMatricule");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final List<CollaborateurEntity> _result = new ArrayList<CollaborateurEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CollaborateurEntity _item;
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
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final String _tmpFlmMatricule;
            if (_cursor.isNull(_cursorIndexOfFlmMatricule)) {
              _tmpFlmMatricule = null;
            } else {
              _tmpFlmMatricule = _cursor.getString(_cursorIndexOfFlmMatricule);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            _item = new CollaborateurEntity(_tmpMatricule,_tmpNom,_tmpPrenom,_tmpService,_tmpFlmMatricule,_tmpSyncedToFirebase);
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
        _stringBuilder.append("UPDATE collaborateurs SET syncedToFirebase = 1 WHERE matricule IN (");
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
