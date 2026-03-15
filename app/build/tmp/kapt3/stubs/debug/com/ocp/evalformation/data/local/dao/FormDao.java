package com.ocp.evalformation.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/ocp/evalformation/data/local/dao/FormDao;", "", "deleteByThemeId", "", "themeId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllLive", "Landroidx/lifecycle/LiveData;", "", "Lcom/ocp/evalformation/data/local/entity/Forms;", "getByThemeId", "insert", "form", "(Lcom/ocp/evalformation/data/local/entity/Forms;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface FormDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.Forms form, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM forms")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.Forms>> getAllLive();
    
    @androidx.room.Query(value = "SELECT * FROM forms WHERE themeId = :themeId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByThemeId(long themeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ocp.evalformation.data.local.entity.Forms> $completion);
    
    @androidx.room.Query(value = "DELETE FROM forms WHERE themeId = :themeId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteByThemeId(long themeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}