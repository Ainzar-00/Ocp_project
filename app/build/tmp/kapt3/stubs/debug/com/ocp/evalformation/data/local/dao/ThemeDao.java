package com.ocp.evalformation.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u000bH\'J\u0018\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\"\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010\u0017\u001a\u00020\u00062\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u000e\u0010\u0019\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u001a"}, d2 = {"Lcom/ocp/evalformation/data/local/dao/ThemeDao;", "", "count", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "", "getAll", "", "Lcom/ocp/evalformation/data/local/entity/ThemeEntity;", "getAllLive", "Landroidx/lifecycle/LiveData;", "getById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnsynced", "insert", "theme", "(Lcom/ocp/evalformation/data/local/entity/ThemeEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "themes", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markSynced", "ids", "resetIds", "app_debug"})
@androidx.room.Dao()
public abstract interface ThemeDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.ThemeEntity theme, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 5)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.ocp.evalformation.data.local.entity.ThemeEntity> themes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM themes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object count(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM themes ORDER BY nom ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.ThemeEntity>> getAllLive();
    
    @androidx.room.Query(value = "SELECT * FROM themes ORDER BY nom ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.ThemeEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM themes WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ocp.evalformation.data.local.entity.ThemeEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM themes WHERE syncedToFirebase = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnsynced(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.ThemeEntity>> $completion);
    
    @androidx.room.Query(value = "UPDATE themes SET syncedToFirebase = 1 WHERE id IN (:ids)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markSynced(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> ids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM themes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM sqlite_sequence WHERE name=\'themes\'")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object resetIds(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}