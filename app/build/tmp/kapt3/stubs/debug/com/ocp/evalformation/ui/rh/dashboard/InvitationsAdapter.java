package com.ocp.evalformation.ui.rh.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001a2\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u001a\u001bB-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\f0\f2\u0006\u0010\u000f\u001a\u00020\u000bH\u0002J\u001c\u0010\u0010\u001a\u00020\u00062\n\u0010\u0011\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001c\u0010\u0014\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0013H\u0016J\u001a\u0010\u0018\u001a\u00020\u00062\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/ocp/evalformation/ui/rh/dashboard/InvitationsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/ocp/evalformation/ui/rh/FormationWithInvitation;", "Lcom/ocp/evalformation/ui/rh/dashboard/InvitationsAdapter$VH;", "onEnvoyer", "Lkotlin/Function1;", "", "onRenvoyer", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "themesMap", "", "", "", "formatDate", "kotlin.jvm.PlatformType", "millis", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitThemes", "themes", "Companion", "VH", "app_debug"})
public final class InvitationsAdapter extends androidx.recyclerview.widget.ListAdapter<com.ocp.evalformation.ui.rh.FormationWithInvitation, com.ocp.evalformation.ui.rh.dashboard.InvitationsAdapter.VH> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.ocp.evalformation.ui.rh.FormationWithInvitation, kotlin.Unit> onEnvoyer = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.ocp.evalformation.ui.rh.FormationWithInvitation, kotlin.Unit> onRenvoyer = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.Map<java.lang.Long, java.lang.String> themesMap;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.ocp.evalformation.ui.rh.FormationWithInvitation> DIFF = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.ocp.evalformation.ui.rh.dashboard.InvitationsAdapter.Companion Companion = null;
    
    public InvitationsAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.ocp.evalformation.ui.rh.FormationWithInvitation, kotlin.Unit> onEnvoyer, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.ocp.evalformation.ui.rh.FormationWithInvitation, kotlin.Unit> onRenvoyer) {
        super(null);
    }
    
    public final void submitThemes(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.Long, java.lang.String> themes) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.ocp.evalformation.ui.rh.dashboard.InvitationsAdapter.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.ui.rh.dashboard.InvitationsAdapter.VH holder, int position) {
    }
    
    private final java.lang.String formatDate(long millis) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/ocp/evalformation/ui/rh/dashboard/InvitationsAdapter$Companion;", "", "()V", "DIFF", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/ocp/evalformation/ui/rh/FormationWithInvitation;", "getDIFF", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.ocp.evalformation.ui.rh.FormationWithInvitation> getDIFF() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/ocp/evalformation/ui/rh/dashboard/InvitationsAdapter$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "b", "Lcom/ocp/evalformation/databinding/ItemInvitationBinding;", "(Lcom/ocp/evalformation/ui/rh/dashboard/InvitationsAdapter;Lcom/ocp/evalformation/databinding/ItemInvitationBinding;)V", "bind", "", "item", "Lcom/ocp/evalformation/ui/rh/FormationWithInvitation;", "app_debug"})
    public final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.ocp.evalformation.databinding.ItemInvitationBinding b = null;
        
        public VH(@org.jetbrains.annotations.NotNull()
        com.ocp.evalformation.databinding.ItemInvitationBinding b) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.ocp.evalformation.ui.rh.FormationWithInvitation item) {
        }
    }
}