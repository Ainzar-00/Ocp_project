package com.ocp.evalformation.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000f\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u000bH\'J\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0015\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0019J\"\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u001c\u0010\u001d\u001a\u00020\u00062\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00120\bH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u000e\u0010\u001f\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010 \u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0019\u00a8\u0006!"}, d2 = {"Lcom/ocp/evalformation/data/local/dao/FormationDao;", "", "count", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "", "getAll", "", "Lcom/ocp/evalformation/data/local/entity/FormationEntity;", "getAllLive", "Landroidx/lifecycle/LiveData;", "getByCollaborateur", "matricule", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByTheme", "themeId", "getUnsynced", "insert", "formation", "(Lcom/ocp/evalformation/data/local/entity/FormationEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "formations", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markSynced", "ids", "resetIds", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface FormationDao {
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.ocp.evalformation.data.local.entity.FormationEntity> formations, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.FormationEntity formation, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.FormationEntity formation, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM formations ORDER BY debut DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.FormationEntity>> getAllLive();
    
    @androidx.room.Query(value = "SELECT * FROM formations ORDER BY debut DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.FormationEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM formations WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ocp.evalformation.data.local.entity.FormationEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM formations WHERE collaborateurMatricule = :matricule")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByCollaborateur(@org.jetbrains.annotations.NotNull()
    java.lang.String matricule, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.FormationEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM formations WHERE themeId = :themeId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByTheme(long themeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.FormationEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM formations")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object count(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM formations WHERE syncedToFirebase = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnsynced(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.FormationEntity>> $completion);
    
    @androidx.room.Query(value = "UPDATE formations SET syncedToFirebase = 1 WHERE id IN (:ids)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markSynced(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> ids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM formations")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM sqlite_sequence WHERE name=\'formations\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object resetIds(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}