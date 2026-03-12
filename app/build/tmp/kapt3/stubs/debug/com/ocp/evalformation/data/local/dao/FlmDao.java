package com.ocp.evalformation.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u000bH\'J\u0018\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\u00020\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\u00020\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0017\u00a8\u0006\u001a"}, d2 = {"Lcom/ocp/evalformation/data/local/dao/FlmDao;", "", "count", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "", "getAll", "", "Lcom/ocp/evalformation/data/local/entity/FlmEntity;", "getAllLive", "Landroidx/lifecycle/LiveData;", "getByMatricule", "matricule", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnsynced", "insert", "", "flm", "(Lcom/ocp/evalformation/data/local/entity/FlmEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "flms", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markSynced", "matricules", "app_debug"})
@androidx.room.Dao()
public abstract interface FlmDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.FlmEntity flm, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.ocp.evalformation.data.local.entity.FlmEntity> flms, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM flms ORDER BY nom ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.FlmEntity>> getAllLive();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM flms")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object count(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM flms ORDER BY nom ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.FlmEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM flms WHERE matricule = :matricule LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByMatricule(@org.jetbrains.annotations.NotNull()
    java.lang.String matricule, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ocp.evalformation.data.local.entity.FlmEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM flms WHERE syncedToFirebase = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnsynced(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.FlmEntity>> $completion);
    
    @androidx.room.Query(value = "UPDATE flms SET syncedToFirebase = 1 WHERE matricule IN (:matricules)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markSynced(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> matricules, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM flms")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}