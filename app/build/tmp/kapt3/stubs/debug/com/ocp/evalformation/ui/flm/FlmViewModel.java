package com.ocp.evalformation.ui.flm;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0010B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0011"}, d2 = {"Lcom/ocp/evalformation/ui/flm/FlmViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/ocp/evalformation/data/repository/MainRepository;", "(Lcom/ocp/evalformation/data/repository/MainRepository;)V", "_searchState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState;", "searchState", "Lkotlinx/coroutines/flow/StateFlow;", "getSearchState", "()Lkotlinx/coroutines/flow/StateFlow;", "searchByMatricule", "", "matricule", "", "SearchState", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class FlmViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.repository.MainRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.ocp.evalformation.ui.flm.FlmViewModel.SearchState> _searchState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.flm.FlmViewModel.SearchState> searchState = null;
    
    @javax.inject.Inject()
    public FlmViewModel(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.repository.MainRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.flm.FlmViewModel.SearchState> getSearchState() {
        return null;
    }
    
    public final void searchByMatricule(@org.jetbrains.annotations.NotNull()
    java.lang.String matricule) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState;", "", "()V", "Error", "Found", "Idle", "Loading", "NotFound", "Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState$Error;", "Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState$Found;", "Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState$Idle;", "Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState$Loading;", "Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState$NotFound;", "app_debug"})
    public static abstract class SearchState {
        
        private SearchState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState$Error;", "Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.ocp.evalformation.ui.flm.FlmViewModel.SearchState {
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
            public final com.ocp.evalformation.ui.flm.FlmViewModel.SearchState.Error copy(@org.jetbrains.annotations.NotNull()
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState$Found;", "Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState;", "data", "Lcom/ocp/evalformation/ui/flm/CollabAvecFormation;", "(Lcom/ocp/evalformation/ui/flm/CollabAvecFormation;)V", "getData", "()Lcom/ocp/evalformation/ui/flm/CollabAvecFormation;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
        public static final class Found extends com.ocp.evalformation.ui.flm.FlmViewModel.SearchState {
            @org.jetbrains.annotations.NotNull()
            private final com.ocp.evalformation.ui.flm.CollabAvecFormation data = null;
            
            public Found(@org.jetbrains.annotations.NotNull()
            com.ocp.evalformation.ui.flm.CollabAvecFormation data) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.ui.flm.CollabAvecFormation getData() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.ui.flm.CollabAvecFormation component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.ui.flm.FlmViewModel.SearchState.Found copy(@org.jetbrains.annotations.NotNull()
            com.ocp.evalformation.ui.flm.CollabAvecFormation data) {
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState$Idle;", "Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState;", "()V", "app_debug"})
        public static final class Idle extends com.ocp.evalformation.ui.flm.FlmViewModel.SearchState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ocp.evalformation.ui.flm.FlmViewModel.SearchState.Idle INSTANCE = null;
            
            private Idle() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState$Loading;", "Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState;", "()V", "app_debug"})
        public static final class Loading extends com.ocp.evalformation.ui.flm.FlmViewModel.SearchState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ocp.evalformation.ui.flm.FlmViewModel.SearchState.Loading INSTANCE = null;
            
            private Loading() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState$NotFound;", "Lcom/ocp/evalformation/ui/flm/FlmViewModel$SearchState;", "matricule", "", "(Ljava/lang/String;)V", "getMatricule", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class NotFound extends com.ocp.evalformation.ui.flm.FlmViewModel.SearchState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String matricule = null;
            
            public NotFound(@org.jetbrains.annotations.NotNull()
            java.lang.String matricule) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMatricule() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.ui.flm.FlmViewModel.SearchState.NotFound copy(@org.jetbrains.annotations.NotNull()
            java.lang.String matricule) {
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