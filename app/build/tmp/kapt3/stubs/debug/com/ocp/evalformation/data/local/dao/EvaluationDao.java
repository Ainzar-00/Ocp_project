package com.ocp.evalformation.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u0096@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0096@\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0007"}, d2 = {"Lcom/ocp/evalformation/data/local/dao/EvaluationDao;", "", "deleteAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnsynced", "", "app_debug"})
@androidx.room.Dao()
public abstract interface EvaluationDao {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnsynced(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.lang.Object>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
        
        @org.jetbrains.annotations.Nullable()
        public static java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
        com.ocp.evalformation.data.local.dao.EvaluationDao $this, @org.jetbrains.annotations.NotNull()
        kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public static java.lang.Object getUnsynced(@org.jetbrains.annotations.NotNull()
        com.ocp.evalformation.data.local.dao.EvaluationDao $this, @org.jetbrains.annotations.NotNull()
        kotlin.coroutines.Continuation<? super java.util.List<? extends java.lang.Object>> $completion) {
            return null;
        }
    }
}