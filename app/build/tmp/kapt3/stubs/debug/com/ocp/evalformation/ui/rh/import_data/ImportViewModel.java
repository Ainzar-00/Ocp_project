package com.ocp.evalformation.ui.rh.import_data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J.\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020)J.\u0010.\u001a\u00020\'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)2\u0006\u0010/\u001a\u00020)2\u0006\u0010,\u001a\u00020)J\u0016\u00100\u001a\u00020\'2\u0006\u0010*\u001a\u00020)2\u0006\u00101\u001a\u00020)J\u0018\u00102\u001a\u00020)2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0002J7\u00107\u001a\u0004\u0018\u0001062\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u000206092\u0012\u0010:\u001a\n\u0012\u0006\b\u0001\u0012\u00020)0;\"\u00020)H\u0002\u00a2\u0006\u0002\u0010<J\u000e\u0010=\u001a\u00020\'2\u0006\u0010>\u001a\u00020?J\u000e\u0010@\u001a\u00020\'2\u0006\u0010>\u001a\u00020?J\u000e\u0010A\u001a\u00020\'2\u0006\u0010>\u001a\u00020?J\u000e\u0010B\u001a\u00020\'2\u0006\u0010>\u001a\u00020?J\u0006\u0010C\u001a\u00020\'J\u0006\u0010D\u001a\u00020\'J\u0006\u0010E\u001a\u00020\'J\u0006\u0010F\u001a\u00020\'R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\t0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001f\u00a8\u0006G"}, d2 = {"Lcom/ocp/evalformation/ui/rh/import_data/ImportViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/ocp/evalformation/data/repository/MainRepository;", "context", "Landroid/content/Context;", "(Lcom/ocp/evalformation/data/repository/MainRepository;Landroid/content/Context;)V", "_bilanState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/ocp/evalformation/ui/rh/import_data/ImportState;", "_collabState", "_flmState", "_themeState", "allCollaborateurs", "Landroidx/lifecycle/LiveData;", "", "Lcom/ocp/evalformation/data/local/entity/CollaborateurEntity;", "getAllCollaborateurs", "()Landroidx/lifecycle/LiveData;", "allFlms", "Lcom/ocp/evalformation/data/local/entity/FlmEntity;", "getAllFlms", "allFormations", "Lcom/ocp/evalformation/data/local/entity/FormationEntity;", "getAllFormations", "allThemes", "Lcom/ocp/evalformation/data/local/entity/ThemeEntity;", "getAllThemes", "bilanState", "Lkotlinx/coroutines/flow/StateFlow;", "getBilanState", "()Lkotlinx/coroutines/flow/StateFlow;", "collabState", "getCollabState", "flmState", "getFlmState", "themeState", "getThemeState", "addCollaborateur", "", "matricule", "", "nom", "prenom", "service", "flmMatricule", "addFlm", "email", "addTheme", "objectifPedagogique", "cellStr", "row", "Lorg/apache/poi/ss/usermodel/Row;", "colIndex", "", "col", "headers", "", "keys", "", "(Ljava/util/Map;[Ljava/lang/String;)Ljava/lang/Integer;", "importBilanFC", "inputStream", "Ljava/io/InputStream;", "importCollaborateursFromExcel", "importFlmsFromExcel", "importThemesFromExcel", "resetBilanState", "resetCollabState", "resetFlmState", "resetThemeState", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ImportViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.repository.MainRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.CollaborateurEntity>> allCollaborateurs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.FlmEntity>> allFlms = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.ThemeEntity>> allThemes = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.FormationEntity>> allFormations = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> _collabState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> collabState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> _flmState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> flmState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> _themeState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> themeState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> _bilanState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> bilanState = null;
    
    @javax.inject.Inject()
    public ImportViewModel(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.repository.MainRepository repo, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.CollaborateurEntity>> getAllCollaborateurs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.FlmEntity>> getAllFlms() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.ThemeEntity>> getAllThemes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.ocp.evalformation.data.local.entity.FormationEntity>> getAllFormations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> getCollabState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> getFlmState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> getThemeState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.ocp.evalformation.ui.rh.import_data.ImportState> getBilanState() {
        return null;
    }
    
    public final void resetCollabState() {
    }
    
    public final void resetFlmState() {
    }
    
    public final void resetThemeState() {
    }
    
    public final void resetBilanState() {
    }
    
    private final java.lang.Integer col(java.util.Map<java.lang.String, java.lang.Integer> headers, java.lang.String... keys) {
        return null;
    }
    
    private final java.lang.String cellStr(org.apache.poi.ss.usermodel.Row row, int colIndex) {
        return null;
    }
    
    public final void addCollaborateur(@org.jetbrains.annotations.NotNull()
    java.lang.String matricule, @org.jetbrains.annotations.NotNull()
    java.lang.String nom, @org.jetbrains.annotations.NotNull()
    java.lang.String prenom, @org.jetbrains.annotations.NotNull()
    java.lang.String service, @org.jetbrains.annotations.NotNull()
    java.lang.String flmMatricule) {
    }
    
    public final void addFlm(@org.jetbrains.annotations.NotNull()
    java.lang.String matricule, @org.jetbrains.annotations.NotNull()
    java.lang.String nom, @org.jetbrains.annotations.NotNull()
    java.lang.String prenom, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String service) {
    }
    
    public final void addTheme(@org.jetbrains.annotations.NotNull()
    java.lang.String nom, @org.jetbrains.annotations.NotNull()
    java.lang.String objectifPedagogique) {
    }
    
    public final void importThemesFromExcel(@org.jetbrains.annotations.NotNull()
    java.io.InputStream inputStream) {
    }
    
    public final void importCollaborateursFromExcel(@org.jetbrains.annotations.NotNull()
    java.io.InputStream inputStream) {
    }
    
    public final void importFlmsFromExcel(@org.jetbrains.annotations.NotNull()
    java.io.InputStream inputStream) {
    }
    
    public final void importBilanFC(@org.jetbrains.annotations.NotNull()
    java.io.InputStream inputStream) {
    }
}