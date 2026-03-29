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
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ocp.evalformation.data.local.entity.EntryIds;
import com.ocp.evalformation.data.local.entity.Forms;
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
public final class FormDao_Impl implements FormDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Forms> __insertionAdapterOfForms;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByThemeId;

  public FormDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfForms = new EntityInsertionAdapter<Forms>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `forms` (`id`,`themeId`,`formUrl`,`formationId`,`intituleAction`,`nomPrenom`,`matricule`,`service`,`formateur`,`dates`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Forms entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getThemeId());
        if (entity.getFormUrl() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFormUrl());
        }
        final EntryIds _tmpEntryIds = entity.getEntryIds();
        if (_tmpEntryIds.getFormationId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmpEntryIds.getFormationId());
        }
        if (_tmpEntryIds.getIntituleAction() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmpEntryIds.getIntituleAction());
        }
        if (_tmpEntryIds.getNomPrenom() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmpEntryIds.getNomPrenom());
        }
        if (_tmpEntryIds.getMatricule() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmpEntryIds.getMatricule());
        }
        if (_tmpEntryIds.getService() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmpEntryIds.getService());
        }
        if (_tmpEntryIds.getFormateur() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmpEntryIds.getFormateur());
        }
        if (_tmpEntryIds.getDates() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, _tmpEntryIds.getDates());
        }
      }
    };
    this.__preparedStmtOfDeleteByThemeId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM forms WHERE themeId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Forms form, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfForms.insertAndReturnId(form);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteByThemeId(final long themeId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByThemeId.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, themeId);
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
          __preparedStmtOfDeleteByThemeId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Forms>> getAllLive() {
    final String _sql = "SELECT * FROM forms";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"forms"}, false, new Callable<List<Forms>>() {
      @Override
      @Nullable
      public List<Forms> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfFormUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "formUrl");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfIntituleAction = CursorUtil.getColumnIndexOrThrow(_cursor, "intituleAction");
          final int _cursorIndexOfNomPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "nomPrenom");
          final int _cursorIndexOfMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "matricule");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "formateur");
          final int _cursorIndexOfDates = CursorUtil.getColumnIndexOrThrow(_cursor, "dates");
          final List<Forms> _result = new ArrayList<Forms>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Forms _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpThemeId;
            _tmpThemeId = _cursor.getLong(_cursorIndexOfThemeId);
            final String _tmpFormUrl;
            if (_cursor.isNull(_cursorIndexOfFormUrl)) {
              _tmpFormUrl = null;
            } else {
              _tmpFormUrl = _cursor.getString(_cursorIndexOfFormUrl);
            }
            final EntryIds _tmpEntryIds;
            final String _tmpFormationId;
            if (_cursor.isNull(_cursorIndexOfFormationId)) {
              _tmpFormationId = null;
            } else {
              _tmpFormationId = _cursor.getString(_cursorIndexOfFormationId);
            }
            final String _tmpIntituleAction;
            if (_cursor.isNull(_cursorIndexOfIntituleAction)) {
              _tmpIntituleAction = null;
            } else {
              _tmpIntituleAction = _cursor.getString(_cursorIndexOfIntituleAction);
            }
            final String _tmpNomPrenom;
            if (_cursor.isNull(_cursorIndexOfNomPrenom)) {
              _tmpNomPrenom = null;
            } else {
              _tmpNomPrenom = _cursor.getString(_cursorIndexOfNomPrenom);
            }
            final String _tmpMatricule;
            if (_cursor.isNull(_cursorIndexOfMatricule)) {
              _tmpMatricule = null;
            } else {
              _tmpMatricule = _cursor.getString(_cursorIndexOfMatricule);
            }
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final String _tmpFormateur;
            if (_cursor.isNull(_cursorIndexOfFormateur)) {
              _tmpFormateur = null;
            } else {
              _tmpFormateur = _cursor.getString(_cursorIndexOfFormateur);
            }
            final String _tmpDates;
            if (_cursor.isNull(_cursorIndexOfDates)) {
              _tmpDates = null;
            } else {
              _tmpDates = _cursor.getString(_cursorIndexOfDates);
            }
            _tmpEntryIds = new EntryIds(_tmpFormationId,_tmpIntituleAction,_tmpNomPrenom,_tmpMatricule,_tmpService,_tmpFormateur,_tmpDates);
            _item = new Forms(_tmpId,_tmpThemeId,_tmpFormUrl,_tmpEntryIds);
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
  public Object getByThemeId(final long themeId, final Continuation<? super Forms> $completion) {
    final String _sql = "SELECT * FROM forms WHERE themeId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, themeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Forms>() {
      @Override
      @Nullable
      public Forms call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfThemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "themeId");
          final int _cursorIndexOfFormUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "formUrl");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfIntituleAction = CursorUtil.getColumnIndexOrThrow(_cursor, "intituleAction");
          final int _cursorIndexOfNomPrenom = CursorUtil.getColumnIndexOrThrow(_cursor, "nomPrenom");
          final int _cursorIndexOfMatricule = CursorUtil.getColumnIndexOrThrow(_cursor, "matricule");
          final int _cursorIndexOfService = CursorUtil.getColumnIndexOrThrow(_cursor, "service");
          final int _cursorIndexOfFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "formateur");
          final int _cursorIndexOfDates = CursorUtil.getColumnIndexOrThrow(_cursor, "dates");
          final Forms _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpThemeId;
            _tmpThemeId = _cursor.getLong(_cursorIndexOfThemeId);
            final String _tmpFormUrl;
            if (_cursor.isNull(_cursorIndexOfFormUrl)) {
              _tmpFormUrl = null;
            } else {
              _tmpFormUrl = _cursor.getString(_cursorIndexOfFormUrl);
            }
            final EntryIds _tmpEntryIds;
            final String _tmpFormationId;
            if (_cursor.isNull(_cursorIndexOfFormationId)) {
              _tmpFormationId = null;
            } else {
              _tmpFormationId = _cursor.getString(_cursorIndexOfFormationId);
            }
            final String _tmpIntituleAction;
            if (_cursor.isNull(_cursorIndexOfIntituleAction)) {
              _tmpIntituleAction = null;
            } else {
              _tmpIntituleAction = _cursor.getString(_cursorIndexOfIntituleAction);
            }
            final String _tmpNomPrenom;
            if (_cursor.isNull(_cursorIndexOfNomPrenom)) {
              _tmpNomPrenom = null;
            } else {
              _tmpNomPrenom = _cursor.getString(_cursorIndexOfNomPrenom);
            }
            final String _tmpMatricule;
            if (_cursor.isNull(_cursorIndexOfMatricule)) {
              _tmpMatricule = null;
            } else {
              _tmpMatricule = _cursor.getString(_cursorIndexOfMatricule);
            }
            final String _tmpService;
            if (_cursor.isNull(_cursorIndexOfService)) {
              _tmpService = null;
            } else {
              _tmpService = _cursor.getString(_cursorIndexOfService);
            }
            final String _tmpFormateur;
            if (_cursor.isNull(_cursorIndexOfFormateur)) {
              _tmpFormateur = null;
            } else {
              _tmpFormateur = _cursor.getString(_cursorIndexOfFormateur);
            }
            final String _tmpDates;
            if (_cursor.isNull(_cursorIndexOfDates)) {
              _tmpDates = null;
            } else {
              _tmpDates = _cursor.getString(_cursorIndexOfDates);
            }
            _tmpEntryIds = new EntryIds(_tmpFormationId,_tmpIntituleAction,_tmpNomPrenom,_tmpMatricule,_tmpService,_tmpFormateur,_tmpDates);
            _result = new Forms(_tmpId,_tmpThemeId,_tmpFormUrl,_tmpEntryIds);
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
    final String _sql = "SELECT COUNT(*) FROM FORMS";
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
