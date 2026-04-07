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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00b6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001:\u0002qrB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\u000b2\u0006\u0010U\u001a\u00020\u000bJ\u0006\u0010V\u001a\u00020SJ\u0006\u0010W\u001a\u00020SJ\u0006\u0010X\u001a\u00020SJ$\u0010Y\u001a\u00020S2\u0006\u0010Z\u001a\u00020\u000b2\u0014\u0010[\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0012\u0004\u0012\u00020S0\\J\u000e\u0010]\u001a\u00020S2\u0006\u0010^\u001a\u00020_J\u0006\u0010`\u001a\u00020SJ\u0014\u0010a\u001a\u00020S2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u0006\u0010c\u001a\u00020SJ\u000e\u0010d\u001a\u00020S2\u0006\u0010e\u001a\u000204J\u0010\u0010f\u001a\u00020S2\b\u0010g\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010h\u001a\u00020S2\b\u0010i\u001a\u0004\u0018\u00010\rJ\u0010\u0010j\u001a\u00020S2\b\u0010k\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010l\u001a\u00020S2\u0006\u0010m\u001a\u00020\u000bJ\u0006\u0010n\u001a\u00020SJ\u0006\u0010o\u001a\u00020SJ$\u0010p\u001a\u00020S2\u0006\u0010Z\u001a\u00020\u000b2\u0014\u0010[\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020S0\\R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00130\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00130\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00130\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u00130\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\'0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\'0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001cR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\'0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001cR\u0019\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001cR\u0019\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001cR\u001d\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002040\u00130\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001cR\u001d\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002040\u00130\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001cR\u0017\u00108\u001a\b\u0012\u0004\u0012\u00020\u001109\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010<\u001a\u00020=8F\u00a2\u0006\u0006\u001a\u0004\b<\u0010>R\u0019\u0010?\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010\u001cR\u001d\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0017\u0010E\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010\u001cR\u0017\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001709\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010;R\u0017\u0010I\u001a\b\u0012\u0004\u0012\u00020\'0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u001cR\u0017\u0010K\u001a\b\u0012\u0004\u0012\u00020\'0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\bL\u0010\u001cR\u0019\u0010M\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010N0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\bO\u0010\u001cR\u001a\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\'\u0012\u0004\u0012\u00020\'0QX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006s"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "repo", "Lcom/ocp/evalformation/data/repository/MainRepository;", "evaluationRepo", "Lcom/ocp/evalformation/data/repository/EvaluationRepository;", "application", "Landroid/app/Application;", "(Lcom/ocp/evalformation/data/repository/MainRepository;Lcom/ocp/evalformation/data/repository/EvaluationRepository;Landroid/app/Application;)V", "_filterService", "Landroidx/lifecycle/MutableLiveData;", "", "_filterStatut", "Lcom/ocp/evalformation/data/local/entity/InvitationStatus;", "_filterTheme", "_invitationState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState;", "_pendingIds", "", "", "_searchQuery", "_themeState", "Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState;", "allCollaborateurs", "Landroidx/lifecycle/LiveData;", "Lcom/ocp/evalformation/data/local/entity/CollaborateurEntity;", "getAllCollaborateurs", "()Landroidx/lifecycle/LiveData;", "allFormations", "Lcom/ocp/evalformation/data/local/entity/FormationEntity;", "getAllFormations", "allInvitations", "Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "getAllInvitations", "allThemes", "Lcom/ocp/evalformation/data/local/entity/ThemeEntity;", "getAllThemes", "collaborateursWithFormation", "", "getCollaborateursWithFormation", "distinctThemesCount", "getDistinctThemesCount", "enAttenteCount", "getEnAttenteCount", "filterService", "getFilterService", "filterStatut", "getFilterStatut", "filterTheme", "getFilterTheme", "filteredFormations", "Lcom/ocp/evalformation/ui/rh/FormationWithInvitation;", "getFilteredFormations", "formationsWithStatus", "getFormationsWithStatus", "invitationState", "Lkotlinx/coroutines/flow/StateFlow;", "getInvitationState", "()Lkotlinx/coroutines/flow/StateFlow;", "isEndOfMonth", "", "()Z", "mostRecurrentTheme", "getMostRecurrentTheme", "pendingIds", "getPendingIds", "getRepo", "()Lcom/ocp/evalformation/data/repository/MainRepository;", "searchQuery", "getSearchQuery", "themeState", "getThemeState", "totalCollaborateurs", "getTotalCollaborateurs", "totalEvaluations", "getTotalEvaluations", "totalJsp", "", "getTotalJsp", "yearRange", "Lkotlin/Pair;", "addTheme", "", "nom", "objectifPedagogique", "checkAndUpdateInvitationStatuses", "clearFilters", "deleteAllData", "getFormationByMatricule", "matricule", "onResult", "Lkotlin/Function1;", "refreshPendingIds", "prefs", "Landroid/content/SharedPreferences;", "resetThemeState", "sendAllByFormationIds", "ids", "sendAllInvitations", "sendFormToFlm", "item", "setFilterService", "service", "setFilterStatut", "statut", "setFilterTheme", "theme", "setSearch", "query", "syncToFirebase", "testAppreciationWorker", "verifierMatricule", "InvitationState", "ThemeState", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RhViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.repository.MainRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.repository.EvaluationRepository evaluationRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final android.app.Application application = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.ThemeEntity>> allThemes = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.CollaborateurEntity>> allCollaborateurs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.FormationEntity>> allFormations = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.InvitationFlmEntity>> allInvitations = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Pair<java.lang.Integer, java.lang.Integer> yearRange = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> totalCollaborateurs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> collaborateursWithFormation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<java.lang.Long>> _pendingIds = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<java.lang.Long>> pendingIds = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> distinctThemesCount = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> totalEvaluations = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> enAttenteCount = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> totalJsp = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> mostRecurrentTheme = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.ui.rh.FormationWithInvitation>> formationsWithStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _filterService = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.ocp.evalformation.data.local.entity.InvitationStatus> _filterStatut = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _filterTheme = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> filterService = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.ocp.evalformation.data.local.entity.InvitationStatus> filterStatut = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> filterTheme = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.ui.rh.FormationWithInvitation>> filteredFormations = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.ocp.evalformation.ui.rh.RhViewModel.ThemeState> _themeState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.RhViewModel.ThemeState> themeState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.ocp.evalformation.ui.rh.RhViewModel.InvitationState> _invitationState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.RhViewModel.InvitationState> invitationState = null;
    
    @javax.inject.Inject()
    public RhViewModel(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.repository.MainRepository repo, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.repository.EvaluationRepository evaluationRepo, @org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.repository.MainRepository getRepo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.ThemeEntity>> getAllThemes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.CollaborateurEntity>> getAllCollaborateurs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.FormationEntity>> getAllFormations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.InvitationFlmEntity>> getAllInvitations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getTotalCollaborateurs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getCollaborateursWithFormation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<java.lang.Long>> getPendingIds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getDistinctThemesCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getTotalEvaluations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getEnAttenteCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalJsp() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getMostRecurrentTheme() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.ui.rh.FormationWithInvitation>> getFormationsWithStatus() {
        return null;
    }
    
    public final boolean isEndOfMonth() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFilterService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.ocp.evalformation.data.local.entity.InvitationStatus> getFilterStatut() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getFilterTheme() {
        return null;
    }
    
    public final void setSearch(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void setFilterService(@org.jetbrains.annotations.Nullable()
    java.lang.String service) {
    }
    
    public final void setFilterStatut(@org.jetbrains.annotations.Nullable()
    com.ocp.evalformation.data.local.entity.InvitationStatus statut) {
    }
    
    public final void setFilterTheme(@org.jetbrains.annotations.Nullable()
    java.lang.String theme) {
    }
    
    public final void clearFilters() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.ui.rh.FormationWithInvitation>> getFilteredFormations() {
        return null;
    }
    
    public final void refreshPendingIds(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences prefs) {
    }
    
    public final void checkAndUpdateInvitationStatuses() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.RhViewModel.ThemeState> getThemeState() {
        return null;
    }
    
    public final void addTheme(@org.jetbrains.annotations.NotNull()
    java.lang.String nom, @org.jetbrains.annotations.NotNull()
    java.lang.String objectifPedagogique) {
    }
    
    public final void resetThemeState() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.RhViewModel.InvitationState> getInvitationState() {
        return null;
    }
    
    public final void sendFormToFlm(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.ui.rh.FormationWithInvitation item) {
    }
    
    public final void sendAllByFormationIds(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> ids) {
    }
    
    public final void sendAllInvitations() {
    }
    
    public final void testAppreciationWorker() {
    }
    
    public final void verifierMatricule(@org.jetbrains.annotations.NotNull()
    java.lang.String matricule, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.ocp.evalformation.data.local.entity.CollaborateurEntity, kotlin.Unit> onResult) {
    }
    
    public final void getFormationByMatricule(@org.jetbrains.annotations.NotNull()
    java.lang.String matricule, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.ocp.evalformation.data.local.entity.FormationEntity, kotlin.Unit> onResult) {
    }
    
    public final void deleteAllData() {
    }
    
    public final void syncToFirebase() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState;", "", "()V", "Error", "Idle", "Sending", "Sent", "SentAll", "Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState$Error;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState$Idle;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState$Sending;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState$Sent;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState$SentAll;", "app_debug"})
    public static abstract class InvitationState {
        
        private InvitationState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState$Error;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.ocp.evalformation.ui.rh.RhViewModel.InvitationState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.ui.rh.RhViewModel.InvitationState.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState$Idle;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState;", "()V", "app_debug"})
        public static final class Idle extends com.ocp.evalformation.ui.rh.RhViewModel.InvitationState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ocp.evalformation.ui.rh.RhViewModel.InvitationState.Idle INSTANCE = null;
            
            private Idle() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState$Sending;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState;", "()V", "app_debug"})
        public static final class Sending extends com.ocp.evalformation.ui.rh.RhViewModel.InvitationState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ocp.evalformation.ui.rh.RhViewModel.InvitationState.Sending INSTANCE = null;
            
            private Sending() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState$Sent;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState;", "invitation", "Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "(Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;)V", "getInvitation", "()Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
        public static final class Sent extends com.ocp.evalformation.ui.rh.RhViewModel.InvitationState {
            @org.jetbrains.annotations.NotNull()
            private final com.ocp.evalformation.data.local.entity.InvitationFlmEntity invitation = null;
            
            public Sent(@org.jetbrains.annotations.NotNull()
            com.ocp.evalformation.data.local.entity.InvitationFlmEntity invitation) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.data.local.entity.InvitationFlmEntity getInvitation() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.data.local.entity.InvitationFlmEntity component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.ui.rh.RhViewModel.InvitationState.Sent copy(@org.jetbrains.annotations.NotNull()
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState$SentAll;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$InvitationState;", "count", "", "(I)V", "getCount", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"})
        public static final class SentAll extends com.ocp.evalformation.ui.rh.RhViewModel.InvitationState {
            private final int count = 0;
            
            public SentAll(int count) {
            }
            
            public final int getCount() {
                return 0;
            }
            
            public final int component1() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.ui.rh.RhViewModel.InvitationState.SentAll copy(int count) {
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
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState;", "", "()V", "Error", "Idle", "Loading", "Success", "Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState$Error;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState$Idle;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState$Loading;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState$Success;", "app_debug"})
    public static abstract class ThemeState {
        
        private ThemeState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState$Error;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.ocp.evalformation.ui.rh.RhViewModel.ThemeState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.ui.rh.RhViewModel.ThemeState.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState$Idle;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState;", "()V", "app_debug"})
        public static final class Idle extends com.ocp.evalformation.ui.rh.RhViewModel.ThemeState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ocp.evalformation.ui.rh.RhViewModel.ThemeState.Idle INSTANCE = null;
            
            private Idle() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState$Loading;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState;", "()V", "app_debug"})
        public static final class Loading extends com.ocp.evalformation.ui.rh.RhViewModel.ThemeState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ocp.evalformation.ui.rh.RhViewModel.ThemeState.Loading INSTANCE = null;
            
            private Loading() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState$Success;", "Lcom/ocp/evalformation/ui/rh/RhViewModel$ThemeState;", "theme", "Lcom/ocp/evalformation/data/local/entity/ThemeEntity;", "(Lcom/ocp/evalformation/data/local/entity/ThemeEntity;)V", "getTheme", "()Lcom/ocp/evalformation/data/local/entity/ThemeEntity;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
        public static final class Success extends com.ocp.evalformation.ui.rh.RhViewModel.ThemeState {
            @org.jetbrains.annotations.NotNull()
            private final com.ocp.evalformation.data.local.entity.ThemeEntity theme = null;
            
            public Success(@org.jetbrains.annotations.NotNull()
            com.ocp.evalformation.data.local.entity.ThemeEntity theme) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.data.local.entity.ThemeEntity getTheme() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.data.local.entity.ThemeEntity component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.ui.rh.RhViewModel.ThemeState.Success copy(@org.jetbrains.annotations.NotNull()
            com.ocp.evalformation.data.local.entity.ThemeEntity theme) {
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
    }
}