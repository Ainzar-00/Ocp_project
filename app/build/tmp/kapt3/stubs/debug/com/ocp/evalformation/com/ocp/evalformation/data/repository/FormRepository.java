package com.ocp.evalformation.com.ocp.evalformation.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\n \u0010*\u0004\u0018\u00010\n0\n2\u0006\u0010\u0011\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/ocp/evalformation/com/ocp/evalformation/data/repository/FormRepository;", "", "formDao", "Lcom/ocp/evalformation/data/local/dao/FormDao;", "api", "Lcom/ocp/evalformation/data/GoogleScriptApiService/GoogleScriptApiService;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/ocp/evalformation/data/local/dao/FormDao;Lcom/ocp/evalformation/data/GoogleScriptApiService/GoogleScriptApiService;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "buildPreFilledUrl", "", "form", "Lcom/ocp/evalformation/data/local/entity/Forms;", "invitation", "Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "encode", "kotlin.jvm.PlatformType", "value", "app_debug"})
public final class FormRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.local.dao.FormDao formDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.GoogleScriptApiService.GoogleScriptApiService api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    
    @javax.inject.Inject()
    public FormRepository(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.FormDao formDao, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.GoogleScriptApiService.GoogleScriptApiService api, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String buildPreFilledUrl(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.Forms form, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.InvitationFlmEntity invitation) {
        return null;
    }
    
    private final java.lang.String encode(java.lang.String value) {
        return null;
    }
}