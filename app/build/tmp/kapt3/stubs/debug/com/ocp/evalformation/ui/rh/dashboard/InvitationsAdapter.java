package com.ocp.evalformation.ui.rh.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00132\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0013\u0014B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u001c\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u000f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000eH\u0016\u00a8\u0006\u0015"}, d2 = {"Lcom/ocp/evalformation/ui/rh/dashboard/InvitationsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "Lcom/ocp/evalformation/ui/rh/dashboard/InvitationsAdapter$VH;", "()V", "formatDate", "", "kotlin.jvm.PlatformType", "millis", "", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "VH", "app_debug"})
public final class InvitationsAdapter extends androidx.recyclerview.widget.ListAdapter<com.ocp.evalformation.data.local.entity.InvitationFlmEntity, com.ocp.evalformation.ui.rh.dashboard.InvitationsAdapter.VH> {
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.ocp.evalformation.data.local.entity.InvitationFlmEntity> DIFF = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.ocp.evalformation.ui.rh.dashboard.InvitationsAdapter.Companion Companion = null;
    
    public InvitationsAdapter() {
        super(null);
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/ocp/evalformation/ui/rh/dashboard/InvitationsAdapter$Companion;", "", "()V", "DIFF", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "getDIFF", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.ocp.evalformation.data.local.entity.InvitationFlmEntity> getDIFF() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/ocp/evalformation/ui/rh/dashboard/InvitationsAdapter$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "b", "Lcom/ocp/evalformation/databinding/ItemInvitationBinding;", "(Lcom/ocp/evalformation/ui/rh/dashboard/InvitationsAdapter;Lcom/ocp/evalformation/databinding/ItemInvitationBinding;)V", "bind", "", "inv", "Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "app_debug"})
    public final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.ocp.evalformation.databinding.ItemInvitationBinding b = null;
        
        public VH(@org.jetbrains.annotations.NotNull()
        com.ocp.evalformation.databinding.ItemInvitationBinding b) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.ocp.evalformation.data.local.entity.InvitationFlmEntity inv) {
        }
    }
}