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
import com.ocp.evalformation.data.local.entity.CritieresEvaluation;
import com.ocp.evalformation.data.local.entity.EvaluationEntity;
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
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EvaluationDao_Impl implements EvaluationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EvaluationEntity> __insertionAdapterOfEvaluationEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<EvaluationEntity> __deletionAdapterOfEvaluationEntity;

  private final EntityDeletionOrUpdateAdapter<EvaluationEntity> __updateAdapterOfEvaluationEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public EvaluationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEvaluationEntity = new EntityInsertionAdapter<EvaluationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `evaluations` (`id`,`formationId`,`intituleAction`,`maticuleCollaborateur`,`datesFormation`,`dateEvaluation`,`moyensAppreciation`,`raisonsInsatisfaction`,`competencesAcquises`,`Suggestions`,`satisfactionBesoin`,`impactPerformance`,`applicationConnaissances`,`satisfactionGlobale`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EvaluationEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getFormationId());
        if (entity.getIntituleAction() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getIntituleAction());
        }
        if (entity.getMaticuleCollaborateur() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getMaticuleCollaborateur());
        }
        if (entity.getDatesFormation() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDatesFormation());
        }
        if (entity.getDateEvaluation() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDateEvaluation());
        }
        final String _tmp = __converters.fromStringList(entity.getMoyensAppreciation());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp);
        }
        final String _tmp_1 = __converters.fromStringList(entity.getRaisonsInsatisfaction());
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp_1);
        }
        final String _tmp_2 = __converters.fromStringList(entity.getCompetencesAcquises());
        if (_tmp_2 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_2);
        }
        if (entity.getSuggestions() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getSuggestions());
        }
        final CritieresEvaluation _tmpCritieres = entity.getCritieres();
        statement.bindLong(11, _tmpCritieres.getSatisfactionBesoin());
        statement.bindLong(12, _tmpCritieres.getImpactPerformance());
        statement.bindLong(13, _tmpCritieres.getApplicationConnaissances());
        statement.bindLong(14, _tmpCritieres.getSatisfactionGlobale());
      }
    };
    this.__deletionAdapterOfEvaluationEntity = new EntityDeletionOrUpdateAdapter<EvaluationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `evaluations` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EvaluationEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfEvaluationEntity = new EntityDeletionOrUpdateAdapter<EvaluationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `evaluations` SET `id` = ?,`formationId` = ?,`intituleAction` = ?,`maticuleCollaborateur` = ?,`datesFormation` = ?,`dateEvaluation` = ?,`moyensAppreciation` = ?,`raisonsInsatisfaction` = ?,`competencesAcquises` = ?,`Suggestions` = ?,`satisfactionBesoin` = ?,`impactPerformance` = ?,`applicationConnaissances` = ?,`satisfactionGlobale` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EvaluationEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getFormationId());
        if (entity.getIntituleAction() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getIntituleAction());
        }
        if (entity.getMaticuleCollaborateur() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getMaticuleCollaborateur());
        }
        if (entity.getDatesFormation() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDatesFormation());
        }
        if (entity.getDateEvaluation() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDateEvaluation());
        }
        final String _tmp = __converters.fromStringList(entity.getMoyensAppreciation());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp);
        }
        final String _tmp_1 = __converters.fromStringList(entity.getRaisonsInsatisfaction());
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp_1);
        }
        final String _tmp_2 = __converters.fromStringList(entity.getCompetencesAcquises());
        if (_tmp_2 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_2);
        }
        if (entity.getSuggestions() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getSuggestions());
        }
        final CritieresEvaluation _tmpCritieres = entity.getCritieres();
        statement.bindLong(11, _tmpCritieres.getSatisfactionBesoin());
        statement.bindLong(12, _tmpCritieres.getImpactPerformance());
        statement.bindLong(13, _tmpCritieres.getApplicationConnaissances());
        statement.bindLong(14, _tmpCritieres.getSatisfactionGlobale());
        statement.bindLong(15, entity.getId());
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
  public Object insertAll(final List<EvaluationEntity> evaluations,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEvaluationEntity.insert(evaluations);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object delete(final EvaluationEntity evaluation, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfEvaluationEntity.handle(evaluation);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
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
  public Flow<List<EvaluationEntity>> getAll() {
    final String _sql = "SELECT * FROM evaluations";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"evaluations"}, new Callable<List<EvaluationEntity>>() {
      @Override
      @NonNull
      public List<EvaluationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfIntituleAction = CursorUtil.getColumnIndexOrThrow(_cursor, "intituleAction");
          final int _cursorIndexOfMaticuleCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "maticuleCollaborateur");
          final int _cursorIndexOfDatesFormation = CursorUtil.getColumnIndexOrThrow(_cursor, "datesFormation");
          final int _cursorIndexOfDateEvaluation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEvaluation");
          final int _cursorIndexOfMoyensAppreciation = CursorUtil.getColumnIndexOrThrow(_cursor, "moyensAppreciation");
          final int _cursorIndexOfRaisonsInsatisfaction = CursorUtil.getColumnIndexOrThrow(_cursor, "raisonsInsatisfaction");
          final int _cursorIndexOfCompetencesAcquises = CursorUtil.getColumnIndexOrThrow(_cursor, "competencesAcquises");
          final int _cursorIndexOfSuggestions = CursorUtil.getColumnIndexOrThrow(_cursor, "Suggestions");
          final int _cursorIndexOfSatisfactionBesoin = CursorUtil.getColumnIndexOrThrow(_cursor, "satisfactionBesoin");
          final int _cursorIndexOfImpactPerformance = CursorUtil.getColumnIndexOrThrow(_cursor, "impactPerformance");
          final int _cursorIndexOfApplicationConnaissances = CursorUtil.getColumnIndexOrThrow(_cursor, "applicationConnaissances");
          final int _cursorIndexOfSatisfactionGlobale = CursorUtil.getColumnIndexOrThrow(_cursor, "satisfactionGlobale");
          final List<EvaluationEntity> _result = new ArrayList<EvaluationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EvaluationEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpIntituleAction;
            if (_cursor.isNull(_cursorIndexOfIntituleAction)) {
              _tmpIntituleAction = null;
            } else {
              _tmpIntituleAction = _cursor.getString(_cursorIndexOfIntituleAction);
            }
            final String _tmpMaticuleCollaborateur;
            if (_cursor.isNull(_cursorIndexOfMaticuleCollaborateur)) {
              _tmpMaticuleCollaborateur = null;
            } else {
              _tmpMaticuleCollaborateur = _cursor.getString(_cursorIndexOfMaticuleCollaborateur);
            }
            final String _tmpDatesFormation;
            if (_cursor.isNull(_cursorIndexOfDatesFormation)) {
              _tmpDatesFormation = null;
            } else {
              _tmpDatesFormation = _cursor.getString(_cursorIndexOfDatesFormation);
            }
            final String _tmpDateEvaluation;
            if (_cursor.isNull(_cursorIndexOfDateEvaluation)) {
              _tmpDateEvaluation = null;
            } else {
              _tmpDateEvaluation = _cursor.getString(_cursorIndexOfDateEvaluation);
            }
            final List<String> _tmpMoyensAppreciation;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfMoyensAppreciation)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfMoyensAppreciation);
            }
            _tmpMoyensAppreciation = __converters.toStringList(_tmp);
            final List<String> _tmpRaisonsInsatisfaction;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRaisonsInsatisfaction)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRaisonsInsatisfaction);
            }
            _tmpRaisonsInsatisfaction = __converters.toStringList(_tmp_1);
            final List<String> _tmpCompetencesAcquises;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCompetencesAcquises)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfCompetencesAcquises);
            }
            _tmpCompetencesAcquises = __converters.toStringList(_tmp_2);
            final String _tmpSuggestions;
            if (_cursor.isNull(_cursorIndexOfSuggestions)) {
              _tmpSuggestions = null;
            } else {
              _tmpSuggestions = _cursor.getString(_cursorIndexOfSuggestions);
            }
            final CritieresEvaluation _tmpCritieres;
            final int _tmpSatisfactionBesoin;
            _tmpSatisfactionBesoin = _cursor.getInt(_cursorIndexOfSatisfactionBesoin);
            final int _tmpImpactPerformance;
            _tmpImpactPerformance = _cursor.getInt(_cursorIndexOfImpactPerformance);
            final int _tmpApplicationConnaissances;
            _tmpApplicationConnaissances = _cursor.getInt(_cursorIndexOfApplicationConnaissances);
            final int _tmpSatisfactionGlobale;
            _tmpSatisfactionGlobale = _cursor.getInt(_cursorIndexOfSatisfactionGlobale);
            _tmpCritieres = new CritieresEvaluation(_tmpSatisfactionBesoin,_tmpImpactPerformance,_tmpApplicationConnaissances,_tmpSatisfactionGlobale);
            _item = new EvaluationEntity(_tmpId,_tmpFormationId,_tmpIntituleAction,_tmpMaticuleCollaborateur,_tmpDatesFormation,_tmpDateEvaluation,_tmpMoyensAppreciation,_tmpCritieres,_tmpRaisonsInsatisfaction,_tmpCompetencesAcquises,_tmpSuggestions);
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
  public Object getByFormationId(final long formationId,
      final Continuation<? super EvaluationEntity> arg1) {
    final String _sql = "SELECT * FROM evaluations WHERE formationId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, formationId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<EvaluationEntity>() {
      @Override
      @Nullable
      public EvaluationEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfIntituleAction = CursorUtil.getColumnIndexOrThrow(_cursor, "intituleAction");
          final int _cursorIndexOfMaticuleCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "maticuleCollaborateur");
          final int _cursorIndexOfDatesFormation = CursorUtil.getColumnIndexOrThrow(_cursor, "datesFormation");
          final int _cursorIndexOfDateEvaluation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEvaluation");
          final int _cursorIndexOfMoyensAppreciation = CursorUtil.getColumnIndexOrThrow(_cursor, "moyensAppreciation");
          final int _cursorIndexOfRaisonsInsatisfaction = CursorUtil.getColumnIndexOrThrow(_cursor, "raisonsInsatisfaction");
          final int _cursorIndexOfCompetencesAcquises = CursorUtil.getColumnIndexOrThrow(_cursor, "competencesAcquises");
          final int _cursorIndexOfSuggestions = CursorUtil.getColumnIndexOrThrow(_cursor, "Suggestions");
          final int _cursorIndexOfSatisfactionBesoin = CursorUtil.getColumnIndexOrThrow(_cursor, "satisfactionBesoin");
          final int _cursorIndexOfImpactPerformance = CursorUtil.getColumnIndexOrThrow(_cursor, "impactPerformance");
          final int _cursorIndexOfApplicationConnaissances = CursorUtil.getColumnIndexOrThrow(_cursor, "applicationConnaissances");
          final int _cursorIndexOfSatisfactionGlobale = CursorUtil.getColumnIndexOrThrow(_cursor, "satisfactionGlobale");
          final EvaluationEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpIntituleAction;
            if (_cursor.isNull(_cursorIndexOfIntituleAction)) {
              _tmpIntituleAction = null;
            } else {
              _tmpIntituleAction = _cursor.getString(_cursorIndexOfIntituleAction);
            }
            final String _tmpMaticuleCollaborateur;
            if (_cursor.isNull(_cursorIndexOfMaticuleCollaborateur)) {
              _tmpMaticuleCollaborateur = null;
            } else {
              _tmpMaticuleCollaborateur = _cursor.getString(_cursorIndexOfMaticuleCollaborateur);
            }
            final String _tmpDatesFormation;
            if (_cursor.isNull(_cursorIndexOfDatesFormation)) {
              _tmpDatesFormation = null;
            } else {
              _tmpDatesFormation = _cursor.getString(_cursorIndexOfDatesFormation);
            }
            final String _tmpDateEvaluation;
            if (_cursor.isNull(_cursorIndexOfDateEvaluation)) {
              _tmpDateEvaluation = null;
            } else {
              _tmpDateEvaluation = _cursor.getString(_cursorIndexOfDateEvaluation);
            }
            final List<String> _tmpMoyensAppreciation;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfMoyensAppreciation)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfMoyensAppreciation);
            }
            _tmpMoyensAppreciation = __converters.toStringList(_tmp);
            final List<String> _tmpRaisonsInsatisfaction;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRaisonsInsatisfaction)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRaisonsInsatisfaction);
            }
            _tmpRaisonsInsatisfaction = __converters.toStringList(_tmp_1);
            final List<String> _tmpCompetencesAcquises;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCompetencesAcquises)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfCompetencesAcquises);
            }
            _tmpCompetencesAcquises = __converters.toStringList(_tmp_2);
            final String _tmpSuggestions;
            if (_cursor.isNull(_cursorIndexOfSuggestions)) {
              _tmpSuggestions = null;
            } else {
              _tmpSuggestions = _cursor.getString(_cursorIndexOfSuggestions);
            }
            final CritieresEvaluation _tmpCritieres;
            final int _tmpSatisfactionBesoin;
            _tmpSatisfactionBesoin = _cursor.getInt(_cursorIndexOfSatisfactionBesoin);
            final int _tmpImpactPerformance;
            _tmpImpactPerformance = _cursor.getInt(_cursorIndexOfImpactPerformance);
            final int _tmpApplicationConnaissances;
            _tmpApplicationConnaissances = _cursor.getInt(_cursorIndexOfApplicationConnaissances);
            final int _tmpSatisfactionGlobale;
            _tmpSatisfactionGlobale = _cursor.getInt(_cursorIndexOfSatisfactionGlobale);
            _tmpCritieres = new CritieresEvaluation(_tmpSatisfactionBesoin,_tmpImpactPerformance,_tmpApplicationConnaissances,_tmpSatisfactionGlobale);
            _result = new EvaluationEntity(_tmpId,_tmpFormationId,_tmpIntituleAction,_tmpMaticuleCollaborateur,_tmpDatesFormation,_tmpDateEvaluation,_tmpMoyensAppreciation,_tmpCritieres,_tmpRaisonsInsatisfaction,_tmpCompetencesAcquises,_tmpSuggestions);
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
  public LiveData<List<EvaluationEntity>> getAllAsList() {
    final String _sql = "SELECT * FROM evaluations";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"evaluations"}, false, new Callable<List<EvaluationEntity>>() {
      @Override
      @Nullable
      public List<EvaluationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfIntituleAction = CursorUtil.getColumnIndexOrThrow(_cursor, "intituleAction");
          final int _cursorIndexOfMaticuleCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "maticuleCollaborateur");
          final int _cursorIndexOfDatesFormation = CursorUtil.getColumnIndexOrThrow(_cursor, "datesFormation");
          final int _cursorIndexOfDateEvaluation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEvaluation");
          final int _cursorIndexOfMoyensAppreciation = CursorUtil.getColumnIndexOrThrow(_cursor, "moyensAppreciation");
          final int _cursorIndexOfRaisonsInsatisfaction = CursorUtil.getColumnIndexOrThrow(_cursor, "raisonsInsatisfaction");
          final int _cursorIndexOfCompetencesAcquises = CursorUtil.getColumnIndexOrThrow(_cursor, "competencesAcquises");
          final int _cursorIndexOfSuggestions = CursorUtil.getColumnIndexOrThrow(_cursor, "Suggestions");
          final int _cursorIndexOfSatisfactionBesoin = CursorUtil.getColumnIndexOrThrow(_cursor, "satisfactionBesoin");
          final int _cursorIndexOfImpactPerformance = CursorUtil.getColumnIndexOrThrow(_cursor, "impactPerformance");
          final int _cursorIndexOfApplicationConnaissances = CursorUtil.getColumnIndexOrThrow(_cursor, "applicationConnaissances");
          final int _cursorIndexOfSatisfactionGlobale = CursorUtil.getColumnIndexOrThrow(_cursor, "satisfactionGlobale");
          final List<EvaluationEntity> _result = new ArrayList<EvaluationEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EvaluationEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpIntituleAction;
            if (_cursor.isNull(_cursorIndexOfIntituleAction)) {
              _tmpIntituleAction = null;
            } else {
              _tmpIntituleAction = _cursor.getString(_cursorIndexOfIntituleAction);
            }
            final String _tmpMaticuleCollaborateur;
            if (_cursor.isNull(_cursorIndexOfMaticuleCollaborateur)) {
              _tmpMaticuleCollaborateur = null;
            } else {
              _tmpMaticuleCollaborateur = _cursor.getString(_cursorIndexOfMaticuleCollaborateur);
            }
            final String _tmpDatesFormation;
            if (_cursor.isNull(_cursorIndexOfDatesFormation)) {
              _tmpDatesFormation = null;
            } else {
              _tmpDatesFormation = _cursor.getString(_cursorIndexOfDatesFormation);
            }
            final String _tmpDateEvaluation;
            if (_cursor.isNull(_cursorIndexOfDateEvaluation)) {
              _tmpDateEvaluation = null;
            } else {
              _tmpDateEvaluation = _cursor.getString(_cursorIndexOfDateEvaluation);
            }
            final List<String> _tmpMoyensAppreciation;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfMoyensAppreciation)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfMoyensAppreciation);
            }
            _tmpMoyensAppreciation = __converters.toStringList(_tmp);
            final List<String> _tmpRaisonsInsatisfaction;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRaisonsInsatisfaction)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRaisonsInsatisfaction);
            }
            _tmpRaisonsInsatisfaction = __converters.toStringList(_tmp_1);
            final List<String> _tmpCompetencesAcquises;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCompetencesAcquises)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfCompetencesAcquises);
            }
            _tmpCompetencesAcquises = __converters.toStringList(_tmp_2);
            final String _tmpSuggestions;
            if (_cursor.isNull(_cursorIndexOfSuggestions)) {
              _tmpSuggestions = null;
            } else {
              _tmpSuggestions = _cursor.getString(_cursorIndexOfSuggestions);
            }
            final CritieresEvaluation _tmpCritieres;
            final int _tmpSatisfactionBesoin;
            _tmpSatisfactionBesoin = _cursor.getInt(_cursorIndexOfSatisfactionBesoin);
            final int _tmpImpactPerformance;
            _tmpImpactPerformance = _cursor.getInt(_cursorIndexOfImpactPerformance);
            final int _tmpApplicationConnaissances;
            _tmpApplicationConnaissances = _cursor.getInt(_cursorIndexOfApplicationConnaissances);
            final int _tmpSatisfactionGlobale;
            _tmpSatisfactionGlobale = _cursor.getInt(_cursorIndexOfSatisfactionGlobale);
            _tmpCritieres = new CritieresEvaluation(_tmpSatisfactionBesoin,_tmpImpactPerformance,_tmpApplicationConnaissances,_tmpSatisfactionGlobale);
            _item = new EvaluationEntity(_tmpId,_tmpFormationId,_tmpIntituleAction,_tmpMaticuleCollaborateur,_tmpDatesFormation,_tmpDateEvaluation,_tmpMoyensAppreciation,_tmpCritieres,_tmpRaisonsInsatisfaction,_tmpCompetencesAcquises,_tmpSuggestions);
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
  public Object getById(final long id, final Continuation<? super EvaluationEntity> arg1) {
    final String _sql = "SELECT * FROM evaluations WHERE id = ?";
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
          final int _cursorIndexOfIntituleAction = CursorUtil.getColumnIndexOrThrow(_cursor, "intituleAction");
          final int _cursorIndexOfMaticuleCollaborateur = CursorUtil.getColumnIndexOrThrow(_cursor, "maticuleCollaborateur");
          final int _cursorIndexOfDatesFormation = CursorUtil.getColumnIndexOrThrow(_cursor, "datesFormation");
          final int _cursorIndexOfDateEvaluation = CursorUtil.getColumnIndexOrThrow(_cursor, "dateEvaluation");
          final int _cursorIndexOfMoyensAppreciation = CursorUtil.getColumnIndexOrThrow(_cursor, "moyensAppreciation");
          final int _cursorIndexOfRaisonsInsatisfaction = CursorUtil.getColumnIndexOrThrow(_cursor, "raisonsInsatisfaction");
          final int _cursorIndexOfCompetencesAcquises = CursorUtil.getColumnIndexOrThrow(_cursor, "competencesAcquises");
          final int _cursorIndexOfSuggestions = CursorUtil.getColumnIndexOrThrow(_cursor, "Suggestions");
          final int _cursorIndexOfSatisfactionBesoin = CursorUtil.getColumnIndexOrThrow(_cursor, "satisfactionBesoin");
          final int _cursorIndexOfImpactPerformance = CursorUtil.getColumnIndexOrThrow(_cursor, "impactPerformance");
          final int _cursorIndexOfApplicationConnaissances = CursorUtil.getColumnIndexOrThrow(_cursor, "applicationConnaissances");
          final int _cursorIndexOfSatisfactionGlobale = CursorUtil.getColumnIndexOrThrow(_cursor, "satisfactionGlobale");
          final EvaluationEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final String _tmpIntituleAction;
            if (_cursor.isNull(_cursorIndexOfIntituleAction)) {
              _tmpIntituleAction = null;
            } else {
              _tmpIntituleAction = _cursor.getString(_cursorIndexOfIntituleAction);
            }
            final String _tmpMaticuleCollaborateur;
            if (_cursor.isNull(_cursorIndexOfMaticuleCollaborateur)) {
              _tmpMaticuleCollaborateur = null;
            } else {
              _tmpMaticuleCollaborateur = _cursor.getString(_cursorIndexOfMaticuleCollaborateur);
            }
            final String _tmpDatesFormation;
            if (_cursor.isNull(_cursorIndexOfDatesFormation)) {
              _tmpDatesFormation = null;
            } else {
              _tmpDatesFormation = _cursor.getString(_cursorIndexOfDatesFormation);
            }
            final String _tmpDateEvaluation;
            if (_cursor.isNull(_cursorIndexOfDateEvaluation)) {
              _tmpDateEvaluation = null;
            } else {
              _tmpDateEvaluation = _cursor.getString(_cursorIndexOfDateEvaluation);
            }
            final List<String> _tmpMoyensAppreciation;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfMoyensAppreciation)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfMoyensAppreciation);
            }
            _tmpMoyensAppreciation = __converters.toStringList(_tmp);
            final List<String> _tmpRaisonsInsatisfaction;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRaisonsInsatisfaction)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRaisonsInsatisfaction);
            }
            _tmpRaisonsInsatisfaction = __converters.toStringList(_tmp_1);
            final List<String> _tmpCompetencesAcquises;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCompetencesAcquises)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfCompetencesAcquises);
            }
            _tmpCompetencesAcquises = __converters.toStringList(_tmp_2);
            final String _tmpSuggestions;
            if (_cursor.isNull(_cursorIndexOfSuggestions)) {
              _tmpSuggestions = null;
            } else {
              _tmpSuggestions = _cursor.getString(_cursorIndexOfSuggestions);
            }
            final CritieresEvaluation _tmpCritieres;
            final int _tmpSatisfactionBesoin;
            _tmpSatisfactionBesoin = _cursor.getInt(_cursorIndexOfSatisfactionBesoin);
            final int _tmpImpactPerformance;
            _tmpImpactPerformance = _cursor.getInt(_cursorIndexOfImpactPerformance);
            final int _tmpApplicationConnaissances;
            _tmpApplicationConnaissances = _cursor.getInt(_cursorIndexOfApplicationConnaissances);
            final int _tmpSatisfactionGlobale;
            _tmpSatisfactionGlobale = _cursor.getInt(_cursorIndexOfSatisfactionGlobale);
            _tmpCritieres = new CritieresEvaluation(_tmpSatisfactionBesoin,_tmpImpactPerformance,_tmpApplicationConnaissances,_tmpSatisfactionGlobale);
            _result = new EvaluationEntity(_tmpId,_tmpFormationId,_tmpIntituleAction,_tmpMaticuleCollaborateur,_tmpDatesFormation,_tmpDateEvaluation,_tmpMoyensAppreciation,_tmpCritieres,_tmpRaisonsInsatisfaction,_tmpCompetencesAcquises,_tmpSuggestions);
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
  public LiveData<Integer> countLive() {
    final String _sql = "SELECT COUNT(*) FROM evaluations";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"evaluations"}, false, new Callable<Integer>() {
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
