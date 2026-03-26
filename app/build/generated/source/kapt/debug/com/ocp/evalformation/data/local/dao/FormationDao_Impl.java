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
import java.lang.Double;
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
        return "INSERT OR IGNORE INTO `formations` (`id`,`collaborateurMatricule`,`themeId`,`debut`,`fin`,`Formateur`,`dateAppreciation`,`syncedToFirebase`,`entite`,`categorie`,`division`,`convocation`,`presence`,`session`,`jsp`,`type`,`domaine`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        if (entity.getFormateur() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getFormateur());
        }
        if (entity.getDateAppreciation() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDateAppreciation());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(8, _tmp);
        if (entity.getEntite() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getEntite());
        }
        if (entity.getCategorie() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCategorie());
        }
        if (entity.getDivision() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDivision());
        }
        if (entity.getConvocation() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getConvocation());
        }
        if (entity.getPresence() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getPresence());
        }
        if (entity.getSession() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getSession());
        }
        if (entity.getJsp() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getJsp());
        }
        if (entity.getType() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getType());
        }
        if (entity.getDomaine() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getDomaine());
        }
      }
    };
    this.__updateAdapterOfFormationEntity = new EntityDeletionOrUpdateAdapter<FormationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `formations` SET `id` = ?,`collaborateurMatricule` = ?,`themeId` = ?,`debut` = ?,`fin` = ?,`Formateur` = ?,`dateAppreciation` = ?,`syncedToFirebase` = ?,`entite` = ?,`categorie` = ?,`division` = ?,`convocation` = ?,`presence` = ?,`session` = ?,`jsp` = ?,`type` = ?,`domaine` = ? WHERE `id` = ?";
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
        if (entity.getFormateur() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getFormateur());
        }
        if (entity.getDateAppreciation() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDateAppreciation());
        }
        final int _tmp = entity.getSyncedToFirebase() ? 1 : 0;
        statement.bindLong(8, _tmp);
        if (entity.getEntite() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getEntite());
        }
        if (entity.getCategorie() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCategorie());
        }
        if (entity.getDivision() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDivision());
        }
        if (entity.getConvocation() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getConvocation());
        }
        if (entity.getPresence() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getPresence());
        }
        if (entity.getSession() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getSession());
        }
        if (entity.getJsp() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getJsp());
        }
        if (entity.getType() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getType());
        }
        if (entity.getDomaine() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getDomaine());
        }
        statement.bindLong(18, entity.getId());
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
  public Object insert(final FormationEntity formation, final Continuation<? super Long> arg1) {
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
    }, arg1);
  }

  @Override
  public Object insertAll(final List<FormationEntity> formations,
      final Continuation<? super List<Long>> arg1) {
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
    }, arg1);
  }

  @Override
  public Object update(final FormationEntity formation, final Continuation<? super Unit> arg1) {
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
  public Object resetIds(final Continuation<? super Unit> arg0) {
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
    }, arg0);
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
          final int _cursorIndexOfFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "Formateur");
          final int _cursorIndexOfDateAppreciation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAppreciation");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final int _cursorIndexOfEntite = CursorUtil.getColumnIndexOrThrow(_cursor, "entite");
          final int _cursorIndexOfCategorie = CursorUtil.getColumnIndexOrThrow(_cursor, "categorie");
          final int _cursorIndexOfDivision = CursorUtil.getColumnIndexOrThrow(_cursor, "division");
          final int _cursorIndexOfConvocation = CursorUtil.getColumnIndexOrThrow(_cursor, "convocation");
          final int _cursorIndexOfPresence = CursorUtil.getColumnIndexOrThrow(_cursor, "presence");
          final int _cursorIndexOfSession = CursorUtil.getColumnIndexOrThrow(_cursor, "session");
          final int _cursorIndexOfJsp = CursorUtil.getColumnIndexOrThrow(_cursor, "jsp");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDomaine = CursorUtil.getColumnIndexOrThrow(_cursor, "domaine");
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
            final String _tmpFormateur;
            if (_cursor.isNull(_cursorIndexOfFormateur)) {
              _tmpFormateur = null;
            } else {
              _tmpFormateur = _cursor.getString(_cursorIndexOfFormateur);
            }
            final String _tmpDateAppreciation;
            if (_cursor.isNull(_cursorIndexOfDateAppreciation)) {
              _tmpDateAppreciation = null;
            } else {
              _tmpDateAppreciation = _cursor.getString(_cursorIndexOfDateAppreciation);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            final String _tmpEntite;
            if (_cursor.isNull(_cursorIndexOfEntite)) {
              _tmpEntite = null;
            } else {
              _tmpEntite = _cursor.getString(_cursorIndexOfEntite);
            }
            final String _tmpCategorie;
            if (_cursor.isNull(_cursorIndexOfCategorie)) {
              _tmpCategorie = null;
            } else {
              _tmpCategorie = _cursor.getString(_cursorIndexOfCategorie);
            }
            final String _tmpDivision;
            if (_cursor.isNull(_cursorIndexOfDivision)) {
              _tmpDivision = null;
            } else {
              _tmpDivision = _cursor.getString(_cursorIndexOfDivision);
            }
            final String _tmpConvocation;
            if (_cursor.isNull(_cursorIndexOfConvocation)) {
              _tmpConvocation = null;
            } else {
              _tmpConvocation = _cursor.getString(_cursorIndexOfConvocation);
            }
            final String _tmpPresence;
            if (_cursor.isNull(_cursorIndexOfPresence)) {
              _tmpPresence = null;
            } else {
              _tmpPresence = _cursor.getString(_cursorIndexOfPresence);
            }
            final String _tmpSession;
            if (_cursor.isNull(_cursorIndexOfSession)) {
              _tmpSession = null;
            } else {
              _tmpSession = _cursor.getString(_cursorIndexOfSession);
            }
            final String _tmpJsp;
            if (_cursor.isNull(_cursorIndexOfJsp)) {
              _tmpJsp = null;
            } else {
              _tmpJsp = _cursor.getString(_cursorIndexOfJsp);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpDomaine;
            if (_cursor.isNull(_cursorIndexOfDomaine)) {
              _tmpDomaine = null;
            } else {
              _tmpDomaine = _cursor.getString(_cursorIndexOfDomaine);
            }
            _item = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpFormateur,_tmpDateAppreciation,_tmpSyncedToFirebase,_tmpEntite,_tmpCategorie,_tmpDivision,_tmpConvocation,_tmpPresence,_tmpSession,_tmpJsp,_tmpType,_tmpDomaine);
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
  public Object getAll(final Continuation<? super List<FormationEntity>> arg0) {
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
          final int _cursorIndexOfFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "Formateur");
          final int _cursorIndexOfDateAppreciation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAppreciation");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final int _cursorIndexOfEntite = CursorUtil.getColumnIndexOrThrow(_cursor, "entite");
          final int _cursorIndexOfCategorie = CursorUtil.getColumnIndexOrThrow(_cursor, "categorie");
          final int _cursorIndexOfDivision = CursorUtil.getColumnIndexOrThrow(_cursor, "division");
          final int _cursorIndexOfConvocation = CursorUtil.getColumnIndexOrThrow(_cursor, "convocation");
          final int _cursorIndexOfPresence = CursorUtil.getColumnIndexOrThrow(_cursor, "presence");
          final int _cursorIndexOfSession = CursorUtil.getColumnIndexOrThrow(_cursor, "session");
          final int _cursorIndexOfJsp = CursorUtil.getColumnIndexOrThrow(_cursor, "jsp");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDomaine = CursorUtil.getColumnIndexOrThrow(_cursor, "domaine");
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
            final String _tmpFormateur;
            if (_cursor.isNull(_cursorIndexOfFormateur)) {
              _tmpFormateur = null;
            } else {
              _tmpFormateur = _cursor.getString(_cursorIndexOfFormateur);
            }
            final String _tmpDateAppreciation;
            if (_cursor.isNull(_cursorIndexOfDateAppreciation)) {
              _tmpDateAppreciation = null;
            } else {
              _tmpDateAppreciation = _cursor.getString(_cursorIndexOfDateAppreciation);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            final String _tmpEntite;
            if (_cursor.isNull(_cursorIndexOfEntite)) {
              _tmpEntite = null;
            } else {
              _tmpEntite = _cursor.getString(_cursorIndexOfEntite);
            }
            final String _tmpCategorie;
            if (_cursor.isNull(_cursorIndexOfCategorie)) {
              _tmpCategorie = null;
            } else {
              _tmpCategorie = _cursor.getString(_cursorIndexOfCategorie);
            }
            final String _tmpDivision;
            if (_cursor.isNull(_cursorIndexOfDivision)) {
              _tmpDivision = null;
            } else {
              _tmpDivision = _cursor.getString(_cursorIndexOfDivision);
            }
            final String _tmpConvocation;
            if (_cursor.isNull(_cursorIndexOfConvocation)) {
              _tmpConvocation = null;
            } else {
              _tmpConvocation = _cursor.getString(_cursorIndexOfConvocation);
            }
            final String _tmpPresence;
            if (_cursor.isNull(_cursorIndexOfPresence)) {
              _tmpPresence = null;
            } else {
              _tmpPresence = _cursor.getString(_cursorIndexOfPresence);
            }
            final String _tmpSession;
            if (_cursor.isNull(_cursorIndexOfSession)) {
              _tmpSession = null;
            } else {
              _tmpSession = _cursor.getString(_cursorIndexOfSession);
            }
            final String _tmpJsp;
            if (_cursor.isNull(_cursorIndexOfJsp)) {
              _tmpJsp = null;
            } else {
              _tmpJsp = _cursor.getString(_cursorIndexOfJsp);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpDomaine;
            if (_cursor.isNull(_cursorIndexOfDomaine)) {
              _tmpDomaine = null;
            } else {
              _tmpDomaine = _cursor.getString(_cursorIndexOfDomaine);
            }
            _item = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpFormateur,_tmpDateAppreciation,_tmpSyncedToFirebase,_tmpEntite,_tmpCategorie,_tmpDivision,_tmpConvocation,_tmpPresence,_tmpSession,_tmpJsp,_tmpType,_tmpDomaine);
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
  public Object getById(final long id, final Continuation<? super FormationEntity> arg1) {
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
          final int _cursorIndexOfFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "Formateur");
          final int _cursorIndexOfDateAppreciation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAppreciation");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final int _cursorIndexOfEntite = CursorUtil.getColumnIndexOrThrow(_cursor, "entite");
          final int _cursorIndexOfCategorie = CursorUtil.getColumnIndexOrThrow(_cursor, "categorie");
          final int _cursorIndexOfDivision = CursorUtil.getColumnIndexOrThrow(_cursor, "division");
          final int _cursorIndexOfConvocation = CursorUtil.getColumnIndexOrThrow(_cursor, "convocation");
          final int _cursorIndexOfPresence = CursorUtil.getColumnIndexOrThrow(_cursor, "presence");
          final int _cursorIndexOfSession = CursorUtil.getColumnIndexOrThrow(_cursor, "session");
          final int _cursorIndexOfJsp = CursorUtil.getColumnIndexOrThrow(_cursor, "jsp");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDomaine = CursorUtil.getColumnIndexOrThrow(_cursor, "domaine");
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
            final String _tmpFormateur;
            if (_cursor.isNull(_cursorIndexOfFormateur)) {
              _tmpFormateur = null;
            } else {
              _tmpFormateur = _cursor.getString(_cursorIndexOfFormateur);
            }
            final String _tmpDateAppreciation;
            if (_cursor.isNull(_cursorIndexOfDateAppreciation)) {
              _tmpDateAppreciation = null;
            } else {
              _tmpDateAppreciation = _cursor.getString(_cursorIndexOfDateAppreciation);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            final String _tmpEntite;
            if (_cursor.isNull(_cursorIndexOfEntite)) {
              _tmpEntite = null;
            } else {
              _tmpEntite = _cursor.getString(_cursorIndexOfEntite);
            }
            final String _tmpCategorie;
            if (_cursor.isNull(_cursorIndexOfCategorie)) {
              _tmpCategorie = null;
            } else {
              _tmpCategorie = _cursor.getString(_cursorIndexOfCategorie);
            }
            final String _tmpDivision;
            if (_cursor.isNull(_cursorIndexOfDivision)) {
              _tmpDivision = null;
            } else {
              _tmpDivision = _cursor.getString(_cursorIndexOfDivision);
            }
            final String _tmpConvocation;
            if (_cursor.isNull(_cursorIndexOfConvocation)) {
              _tmpConvocation = null;
            } else {
              _tmpConvocation = _cursor.getString(_cursorIndexOfConvocation);
            }
            final String _tmpPresence;
            if (_cursor.isNull(_cursorIndexOfPresence)) {
              _tmpPresence = null;
            } else {
              _tmpPresence = _cursor.getString(_cursorIndexOfPresence);
            }
            final String _tmpSession;
            if (_cursor.isNull(_cursorIndexOfSession)) {
              _tmpSession = null;
            } else {
              _tmpSession = _cursor.getString(_cursorIndexOfSession);
            }
            final String _tmpJsp;
            if (_cursor.isNull(_cursorIndexOfJsp)) {
              _tmpJsp = null;
            } else {
              _tmpJsp = _cursor.getString(_cursorIndexOfJsp);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpDomaine;
            if (_cursor.isNull(_cursorIndexOfDomaine)) {
              _tmpDomaine = null;
            } else {
              _tmpDomaine = _cursor.getString(_cursorIndexOfDomaine);
            }
            _result = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpFormateur,_tmpDateAppreciation,_tmpSyncedToFirebase,_tmpEntite,_tmpCategorie,_tmpDivision,_tmpConvocation,_tmpPresence,_tmpSession,_tmpJsp,_tmpType,_tmpDomaine);
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
  public Object getByCollaborateur(final String matricule,
      final Continuation<? super List<FormationEntity>> arg1) {
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
          final int _cursorIndexOfFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "Formateur");
          final int _cursorIndexOfDateAppreciation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAppreciation");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final int _cursorIndexOfEntite = CursorUtil.getColumnIndexOrThrow(_cursor, "entite");
          final int _cursorIndexOfCategorie = CursorUtil.getColumnIndexOrThrow(_cursor, "categorie");
          final int _cursorIndexOfDivision = CursorUtil.getColumnIndexOrThrow(_cursor, "division");
          final int _cursorIndexOfConvocation = CursorUtil.getColumnIndexOrThrow(_cursor, "convocation");
          final int _cursorIndexOfPresence = CursorUtil.getColumnIndexOrThrow(_cursor, "presence");
          final int _cursorIndexOfSession = CursorUtil.getColumnIndexOrThrow(_cursor, "session");
          final int _cursorIndexOfJsp = CursorUtil.getColumnIndexOrThrow(_cursor, "jsp");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDomaine = CursorUtil.getColumnIndexOrThrow(_cursor, "domaine");
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
            final String _tmpFormateur;
            if (_cursor.isNull(_cursorIndexOfFormateur)) {
              _tmpFormateur = null;
            } else {
              _tmpFormateur = _cursor.getString(_cursorIndexOfFormateur);
            }
            final String _tmpDateAppreciation;
            if (_cursor.isNull(_cursorIndexOfDateAppreciation)) {
              _tmpDateAppreciation = null;
            } else {
              _tmpDateAppreciation = _cursor.getString(_cursorIndexOfDateAppreciation);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            final String _tmpEntite;
            if (_cursor.isNull(_cursorIndexOfEntite)) {
              _tmpEntite = null;
            } else {
              _tmpEntite = _cursor.getString(_cursorIndexOfEntite);
            }
            final String _tmpCategorie;
            if (_cursor.isNull(_cursorIndexOfCategorie)) {
              _tmpCategorie = null;
            } else {
              _tmpCategorie = _cursor.getString(_cursorIndexOfCategorie);
            }
            final String _tmpDivision;
            if (_cursor.isNull(_cursorIndexOfDivision)) {
              _tmpDivision = null;
            } else {
              _tmpDivision = _cursor.getString(_cursorIndexOfDivision);
            }
            final String _tmpConvocation;
            if (_cursor.isNull(_cursorIndexOfConvocation)) {
              _tmpConvocation = null;
            } else {
              _tmpConvocation = _cursor.getString(_cursorIndexOfConvocation);
            }
            final String _tmpPresence;
            if (_cursor.isNull(_cursorIndexOfPresence)) {
              _tmpPresence = null;
            } else {
              _tmpPresence = _cursor.getString(_cursorIndexOfPresence);
            }
            final String _tmpSession;
            if (_cursor.isNull(_cursorIndexOfSession)) {
              _tmpSession = null;
            } else {
              _tmpSession = _cursor.getString(_cursorIndexOfSession);
            }
            final String _tmpJsp;
            if (_cursor.isNull(_cursorIndexOfJsp)) {
              _tmpJsp = null;
            } else {
              _tmpJsp = _cursor.getString(_cursorIndexOfJsp);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpDomaine;
            if (_cursor.isNull(_cursorIndexOfDomaine)) {
              _tmpDomaine = null;
            } else {
              _tmpDomaine = _cursor.getString(_cursorIndexOfDomaine);
            }
            _item = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpFormateur,_tmpDateAppreciation,_tmpSyncedToFirebase,_tmpEntite,_tmpCategorie,_tmpDivision,_tmpConvocation,_tmpPresence,_tmpSession,_tmpJsp,_tmpType,_tmpDomaine);
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
  public Object getByTheme(final long themeId,
      final Continuation<? super List<FormationEntity>> arg1) {
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
          final int _cursorIndexOfFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "Formateur");
          final int _cursorIndexOfDateAppreciation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAppreciation");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final int _cursorIndexOfEntite = CursorUtil.getColumnIndexOrThrow(_cursor, "entite");
          final int _cursorIndexOfCategorie = CursorUtil.getColumnIndexOrThrow(_cursor, "categorie");
          final int _cursorIndexOfDivision = CursorUtil.getColumnIndexOrThrow(_cursor, "division");
          final int _cursorIndexOfConvocation = CursorUtil.getColumnIndexOrThrow(_cursor, "convocation");
          final int _cursorIndexOfPresence = CursorUtil.getColumnIndexOrThrow(_cursor, "presence");
          final int _cursorIndexOfSession = CursorUtil.getColumnIndexOrThrow(_cursor, "session");
          final int _cursorIndexOfJsp = CursorUtil.getColumnIndexOrThrow(_cursor, "jsp");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDomaine = CursorUtil.getColumnIndexOrThrow(_cursor, "domaine");
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
            final String _tmpFormateur;
            if (_cursor.isNull(_cursorIndexOfFormateur)) {
              _tmpFormateur = null;
            } else {
              _tmpFormateur = _cursor.getString(_cursorIndexOfFormateur);
            }
            final String _tmpDateAppreciation;
            if (_cursor.isNull(_cursorIndexOfDateAppreciation)) {
              _tmpDateAppreciation = null;
            } else {
              _tmpDateAppreciation = _cursor.getString(_cursorIndexOfDateAppreciation);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            final String _tmpEntite;
            if (_cursor.isNull(_cursorIndexOfEntite)) {
              _tmpEntite = null;
            } else {
              _tmpEntite = _cursor.getString(_cursorIndexOfEntite);
            }
            final String _tmpCategorie;
            if (_cursor.isNull(_cursorIndexOfCategorie)) {
              _tmpCategorie = null;
            } else {
              _tmpCategorie = _cursor.getString(_cursorIndexOfCategorie);
            }
            final String _tmpDivision;
            if (_cursor.isNull(_cursorIndexOfDivision)) {
              _tmpDivision = null;
            } else {
              _tmpDivision = _cursor.getString(_cursorIndexOfDivision);
            }
            final String _tmpConvocation;
            if (_cursor.isNull(_cursorIndexOfConvocation)) {
              _tmpConvocation = null;
            } else {
              _tmpConvocation = _cursor.getString(_cursorIndexOfConvocation);
            }
            final String _tmpPresence;
            if (_cursor.isNull(_cursorIndexOfPresence)) {
              _tmpPresence = null;
            } else {
              _tmpPresence = _cursor.getString(_cursorIndexOfPresence);
            }
            final String _tmpSession;
            if (_cursor.isNull(_cursorIndexOfSession)) {
              _tmpSession = null;
            } else {
              _tmpSession = _cursor.getString(_cursorIndexOfSession);
            }
            final String _tmpJsp;
            if (_cursor.isNull(_cursorIndexOfJsp)) {
              _tmpJsp = null;
            } else {
              _tmpJsp = _cursor.getString(_cursorIndexOfJsp);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpDomaine;
            if (_cursor.isNull(_cursorIndexOfDomaine)) {
              _tmpDomaine = null;
            } else {
              _tmpDomaine = _cursor.getString(_cursorIndexOfDomaine);
            }
            _item = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpFormateur,_tmpDateAppreciation,_tmpSyncedToFirebase,_tmpEntite,_tmpCategorie,_tmpDivision,_tmpConvocation,_tmpPresence,_tmpSession,_tmpJsp,_tmpType,_tmpDomaine);
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
    }, arg0);
  }

  @Override
  public Object getUnsynced(final Continuation<? super List<FormationEntity>> arg0) {
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
          final int _cursorIndexOfFormateur = CursorUtil.getColumnIndexOrThrow(_cursor, "Formateur");
          final int _cursorIndexOfDateAppreciation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAppreciation");
          final int _cursorIndexOfSyncedToFirebase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToFirebase");
          final int _cursorIndexOfEntite = CursorUtil.getColumnIndexOrThrow(_cursor, "entite");
          final int _cursorIndexOfCategorie = CursorUtil.getColumnIndexOrThrow(_cursor, "categorie");
          final int _cursorIndexOfDivision = CursorUtil.getColumnIndexOrThrow(_cursor, "division");
          final int _cursorIndexOfConvocation = CursorUtil.getColumnIndexOrThrow(_cursor, "convocation");
          final int _cursorIndexOfPresence = CursorUtil.getColumnIndexOrThrow(_cursor, "presence");
          final int _cursorIndexOfSession = CursorUtil.getColumnIndexOrThrow(_cursor, "session");
          final int _cursorIndexOfJsp = CursorUtil.getColumnIndexOrThrow(_cursor, "jsp");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDomaine = CursorUtil.getColumnIndexOrThrow(_cursor, "domaine");
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
            final String _tmpFormateur;
            if (_cursor.isNull(_cursorIndexOfFormateur)) {
              _tmpFormateur = null;
            } else {
              _tmpFormateur = _cursor.getString(_cursorIndexOfFormateur);
            }
            final String _tmpDateAppreciation;
            if (_cursor.isNull(_cursorIndexOfDateAppreciation)) {
              _tmpDateAppreciation = null;
            } else {
              _tmpDateAppreciation = _cursor.getString(_cursorIndexOfDateAppreciation);
            }
            final boolean _tmpSyncedToFirebase;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncedToFirebase);
            _tmpSyncedToFirebase = _tmp != 0;
            final String _tmpEntite;
            if (_cursor.isNull(_cursorIndexOfEntite)) {
              _tmpEntite = null;
            } else {
              _tmpEntite = _cursor.getString(_cursorIndexOfEntite);
            }
            final String _tmpCategorie;
            if (_cursor.isNull(_cursorIndexOfCategorie)) {
              _tmpCategorie = null;
            } else {
              _tmpCategorie = _cursor.getString(_cursorIndexOfCategorie);
            }
            final String _tmpDivision;
            if (_cursor.isNull(_cursorIndexOfDivision)) {
              _tmpDivision = null;
            } else {
              _tmpDivision = _cursor.getString(_cursorIndexOfDivision);
            }
            final String _tmpConvocation;
            if (_cursor.isNull(_cursorIndexOfConvocation)) {
              _tmpConvocation = null;
            } else {
              _tmpConvocation = _cursor.getString(_cursorIndexOfConvocation);
            }
            final String _tmpPresence;
            if (_cursor.isNull(_cursorIndexOfPresence)) {
              _tmpPresence = null;
            } else {
              _tmpPresence = _cursor.getString(_cursorIndexOfPresence);
            }
            final String _tmpSession;
            if (_cursor.isNull(_cursorIndexOfSession)) {
              _tmpSession = null;
            } else {
              _tmpSession = _cursor.getString(_cursorIndexOfSession);
            }
            final String _tmpJsp;
            if (_cursor.isNull(_cursorIndexOfJsp)) {
              _tmpJsp = null;
            } else {
              _tmpJsp = _cursor.getString(_cursorIndexOfJsp);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpDomaine;
            if (_cursor.isNull(_cursorIndexOfDomaine)) {
              _tmpDomaine = null;
            } else {
              _tmpDomaine = _cursor.getString(_cursorIndexOfDomaine);
            }
            _item = new FormationEntity(_tmpId,_tmpCollaborateurMatricule,_tmpThemeId,_tmpDebut,_tmpFin,_tmpFormateur,_tmpDateAppreciation,_tmpSyncedToFirebase,_tmpEntite,_tmpCategorie,_tmpDivision,_tmpConvocation,_tmpPresence,_tmpSession,_tmpJsp,_tmpType,_tmpDomaine);
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
  public LiveData<Integer> countCollaborateursWithFormationByYear(final int start, final int end) {
    final String _sql = "SELECT COUNT(DISTINCT collaborateurMatricule) FROM formations WHERE CAST(dateAppreciation AS INTEGER) BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, start);
    _argIndex = 2;
    _statement.bindLong(_argIndex, end);
    return __db.getInvalidationTracker().createLiveData(new String[] {"formations"}, false, new Callable<Integer>() {
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
  public LiveData<Integer> countDistinctThemesByYear(final int start, final int end) {
    final String _sql = "SELECT COUNT(DISTINCT themeId) FROM formations WHERE CAST(dateAppreciation AS INTEGER) BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, start);
    _argIndex = 2;
    _statement.bindLong(_argIndex, end);
    return __db.getInvalidationTracker().createLiveData(new String[] {"formations"}, false, new Callable<Integer>() {
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
  public LiveData<Double> sumJspByYear(final int start, final int end) {
    final String _sql = "SELECT SUM(CAST(jsp AS REAL)) FROM formations WHERE (presence = 'true' OR presence = '1' OR presence = 'Présent' OR presence = 'présent') AND CAST(dateAppreciation AS INTEGER) BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, start);
    _argIndex = 2;
    _statement.bindLong(_argIndex, end);
    return __db.getInvalidationTracker().createLiveData(new String[] {"formations"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
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
  public LiveData<Long> getMostRecurrentThemeIdByYear(final int start, final int end) {
    final String _sql = "\n"
            + "        SELECT themeId FROM formations \n"
            + "        WHERE CAST(dateAppreciation AS INTEGER) BETWEEN ? AND ?\n"
            + "        GROUP BY themeId \n"
            + "        ORDER BY COUNT(*) DESC \n"
            + "        LIMIT 1\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, start);
    _argIndex = 2;
    _statement.bindLong(_argIndex, end);
    return __db.getInvalidationTracker().createLiveData(new String[] {"formations"}, false, new Callable<Long>() {
      @Override
      @Nullable
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getLong(0);
            }
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
  public Object markSynced(final List<Long> ids, final Continuation<? super Unit> arg1) {
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
    }, arg1);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
