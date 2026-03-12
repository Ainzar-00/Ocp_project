package com.ocp.evalformation.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0003H\'J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0003H\'J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\fJ&\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\u0014"}, d2 = {"Lcom/ocp/evalformation/data/local/dao/InvitationFlmDao;", "", "countPendingLive", "Landroidx/lifecycle/LiveData;", "", "getAllLive", "", "Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "getPendingLive", "insert", "", "invitation", "(Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markReplied", "", "id", "date", "evalId", "(JJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface InvitationFlmDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.InvitationFlmEntity invitation, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.InvitationFlmEntity invitation, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM invitations_flm ORDER BY dateEnvoi DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.InvitationFlmEntity>> getAllLive();
    
    @androidx.room.Query(value = "SELECT * FROM invitations_flm WHERE statut = \'ENVOYE\'")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.InvitationFlmEntity>> getPendingLive();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM invitations_flm WHERE statut = \'ENVOYE\'")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> countPendingLive();
    
    @androidx.room.Query(value = "UPDATE invitations_flm SET statut = \'REPONDU\', dateReponse = :date, evaluationId = :evalId WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markReplied(long id, long date, long evalId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}