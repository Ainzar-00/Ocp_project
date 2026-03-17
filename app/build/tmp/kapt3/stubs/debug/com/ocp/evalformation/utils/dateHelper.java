package com.ocp.evalformation.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u0006J\u001f\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/ocp/evalformation/utils/dateHelper;", "", "()V", "excelDateToString", "", "numericValue", "", "pattern", "getDateAppreciation", "excelDate", "getTodayExcelDate", "stringToExcelDate", "dateStr", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Double;", "app_debug"})
public final class dateHelper {
    @org.jetbrains.annotations.NotNull()
    public static final com.ocp.evalformation.utils.dateHelper INSTANCE = null;
    
    private dateHelper() {
        super();
    }
    
    public final double getTodayExcelDate() {
        return 0.0;
    }
    
    public final double getDateAppreciation(double excelDate) {
        return 0.0;
    }
    
    /**
     * Converts a date string to an Excel numeric date value.
     * Excel stores dates as number of days since 1900-01-01.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double stringToExcelDate(@org.jetbrains.annotations.NotNull()
    java.lang.String dateStr, @org.jetbrains.annotations.NotNull()
    java.lang.String pattern) {
        return null;
    }
    
    /**
     * Converts an Excel numeric date (e.g. 45730.0) to a formatted date string.
     * Excel stores dates as number of days since 1900-01-01.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String excelDateToString(double numericValue, @org.jetbrains.annotations.NotNull()
    java.lang.String pattern) {
        return null;
    }
}