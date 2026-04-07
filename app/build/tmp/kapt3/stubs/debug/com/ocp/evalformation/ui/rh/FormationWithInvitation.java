package com.ocp.evalformation.ui.rh;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.lifecycle.*;
import androidx.work.WorkManager;
import com.ocp.evalformation.AppreciationDateWorker;
import com.ocp.evalformation.data.local.entity.*;
import com.ocp.evalformation.data.repository.EvaluationRepository;
import com.ocp.evalformation.data.repository.MainRepository;
import com.ocp.evalformation.utils.dateHelper;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import java.util.Calendar;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lcom/ocp/evalformation/ui/rh/FormationWithInvitation;", "", "formation", "Lcom/ocp/evalformation/data/local/entity/FormationEntity;", "invitation", "Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "(Lcom/ocp/evalformation/data/local/entity/FormationEntity;Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;)V", "getFormation", "()Lcom/ocp/evalformation/data/local/entity/FormationEntity;", "getInvitation", "()Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "status", "Lcom/ocp/evalformation/data/local/entity/InvitationStatus;", "getStatus", "()Lcom/ocp/evalformation/data/local/entity/InvitationStatus;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class FormationWithInvitation {
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.local.entity.FormationEntity formation = null;
    @org.jetbrains.annotations.Nullable()
    private final com.ocp.evalformation.data.local.entity.InvitationFlmEntity invitation = null;
    
    public FormationWithInvitation(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.FormationEntity formation, @org.jetbrains.annotations.Nullable()
    com.ocp.evalformation.data.local.entity.InvitationFlmEntity invitation) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.entity.FormationEntity getFormation() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.ocp.evalformation.data.local.entity.InvitationFlmEntity getInvitation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.entity.InvitationStatus getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.entity.FormationEntity component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.ocp.evalformation.data.local.entity.InvitationFlmEntity component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.ui.rh.FormationWithInvitation copy(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.FormationEntity formation, @org.jetbrains.annotations.Nullable()
    com.ocp.evalformation.data.local.entity.InvitationFlmEntity invitation) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}