package com.ocp.evalformation.ui.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001fB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0082@\u00a2\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0013J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0011H\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0006\u0010\u001c\u001a\u00020\u0015J\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u0011R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/ocp/evalformation/ui/auth/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "syncRepository", "Lcom/ocp/evalformation/data/repository/syncRepository;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/ocp/evalformation/data/repository/syncRepository;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "fetchOrCreateUserRole", "Lcom/ocp/evalformation/model/UserRole;", "uid", "", "isNewUser", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firebaseAuthWithGoogle", "", "idToken", "getCurrentUserRole", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isAlreadyLoggedIn", "syncAndNavigate", "role", "LoginState", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.repository.syncRepository syncRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.ocp.evalformation.ui.auth.LoginViewModel.LoginState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.auth.LoginViewModel.LoginState> state = null;
    
    @javax.inject.Inject()
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.repository.syncRepository syncRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.auth.LoginViewModel.LoginState> getState() {
        return null;
    }
    
    public final boolean isAlreadyLoggedIn() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCurrentUserRole(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ocp.evalformation.model.UserRole> $completion) {
        return null;
    }
    
    public final void syncAndNavigate(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.model.UserRole role) {
    }
    
    public final void firebaseAuthWithGoogle(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken) {
    }
    
    private final java.lang.Object fetchOrCreateUserRole(java.lang.String uid, boolean isNewUser, kotlin.coroutines.Continuation<? super com.ocp.evalformation.model.UserRole> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState;", "", "()V", "Error", "Idle", "Loading", "Success", "Syncing", "Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState$Error;", "Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState$Idle;", "Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState$Loading;", "Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState$Success;", "Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState$Syncing;", "app_debug"})
    public static abstract class LoginState {
        
        private LoginState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState$Error;", "Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.ocp.evalformation.ui.auth.LoginViewModel.LoginState {
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
            public final com.ocp.evalformation.ui.auth.LoginViewModel.LoginState.Error copy(@org.jetbrains.annotations.NotNull()
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState$Idle;", "Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState;", "()V", "app_debug"})
        public static final class Idle extends com.ocp.evalformation.ui.auth.LoginViewModel.LoginState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ocp.evalformation.ui.auth.LoginViewModel.LoginState.Idle INSTANCE = null;
            
            private Idle() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState$Loading;", "Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState;", "()V", "app_debug"})
        public static final class Loading extends com.ocp.evalformation.ui.auth.LoginViewModel.LoginState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ocp.evalformation.ui.auth.LoginViewModel.LoginState.Loading INSTANCE = null;
            
            private Loading() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState$Success;", "Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState;", "role", "Lcom/ocp/evalformation/model/UserRole;", "(Lcom/ocp/evalformation/model/UserRole;)V", "getRole", "()Lcom/ocp/evalformation/model/UserRole;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
        public static final class Success extends com.ocp.evalformation.ui.auth.LoginViewModel.LoginState {
            @org.jetbrains.annotations.NotNull()
            private final com.ocp.evalformation.model.UserRole role = null;
            
            public Success(@org.jetbrains.annotations.NotNull()
            com.ocp.evalformation.model.UserRole role) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.model.UserRole getRole() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.model.UserRole component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.ocp.evalformation.ui.auth.LoginViewModel.LoginState.Success copy(@org.jetbrains.annotations.NotNull()
            com.ocp.evalformation.model.UserRole role) {
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState$Syncing;", "Lcom/ocp/evalformation/ui/auth/LoginViewModel$LoginState;", "()V", "app_debug"})
        public static final class Syncing extends com.ocp.evalformation.ui.auth.LoginViewModel.LoginState {
            @org.jetbrains.annotations.NotNull()
            public static final com.ocp.evalformation.ui.auth.LoginViewModel.LoginState.Syncing INSTANCE = null;
            
            private Syncing() {
            }
        }
    }
}