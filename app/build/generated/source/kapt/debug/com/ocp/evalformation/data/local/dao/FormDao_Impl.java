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
        statement.bindLong(4, _tmpEntryIds.getFormationId());
        statement.bindLong(5, _tmpEntryIds.getIntituleAction());
        statement.bindLong(6, _tmpEntryIds.getNomPrenom());
        statement.bindLong(7, _tmpEntryIds.getMatricule());
        statement.bindLong(8, _tmpEntryIds.getService());
        statement.bindLong(9, _tmpEntryIds.getFormateur());
        statement.bindLong(10, _tmpEntryIds.getDates());
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
  public Object insert(final Forms form, final Continuation<? super Long> arg1) {
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
    }, arg1);
  }

  @Override
  public Object deleteByThemeId(final long themeId, final Continuation<? super Unit> arg1) {
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
    }, arg1);
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
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final long _tmpIntituleAction;
            _tmpIntituleAction = _cursor.getLong(_cursorIndexOfIntituleAction);
            final long _tmpNomPrenom;
            _tmpNomPrenom = _cursor.getLong(_cursorIndexOfNomPrenom);
            final long _tmpMatricule;
            _tmpMatricule = _cursor.getLong(_cursorIndexOfMatricule);
            final long _tmpService;
            _tmpService = _cursor.getLong(_cursorIndexOfService);
            final long _tmpFormateur;
            _tmpFormateur = _cursor.getLong(_cursorIndexOfFormateur);
            final long _tmpDates;
            _tmpDates = _cursor.getLong(_cursorIndexOfDates);
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
  public Object getByThemeId(final long themeId, final Continuation<? super Forms> arg1) {
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
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final long _tmpIntituleAction;
            _tmpIntituleAction = _cursor.getLong(_cursorIndexOfIntituleAction);
            final long _tmpNomPrenom;
            _tmpNomPrenom = _cursor.getLong(_cursorIndexOfNomPrenom);
            final long _tmpMatricule;
            _tmpMatricule = _cursor.getLong(_cursorIndexOfMatricule);
            final long _tmpService;
            _tmpService = _cursor.getLong(_cursorIndexOfService);
            final long _tmpFormateur;
            _tmpFormateur = _cursor.getLong(_cursorIndexOfFormateur);
            final long _tmpDates;
            _tmpDates = _cursor.getLong(_cursorIndexOfDates);
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
    }, arg1);
  }

  @Override
  public Object count(final Continuation<? super Integer> arg0) {
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
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
