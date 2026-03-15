package com.ocp.evalformation.data.local.dao;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EvaluationDao_Impl implements EvaluationDao {
  private final RoomDatabase __db;

  public EvaluationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> arg0) {
    return EvaluationDao.DefaultImpls.deleteAll(EvaluationDao_Impl.this, arg0);
  }

  @Override
  public Object getUnsynced(final Continuation<? super List<?>> arg0) {
    return EvaluationDao.DefaultImpls.getUnsynced(EvaluationDao_Impl.this, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
