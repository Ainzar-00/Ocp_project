# Keep Room entities
-keep class com.ocp.evalformation.data.local.entity.** { *; }

# Keep Firebase models
-keepattributes Signature
-keepattributes *Annotation*

# POI
-dontwarn org.apache.poi.**
-dontwarn org.openxmlformats.**
-dontwarn com.microsoft.schemas.**
-dontwarn org.w3c.dom.**
-dontwarn javax.xml.**
-dontwarn org.xml.sax.**

# MPAndroidChart
-keep class com.github.mikephil.charting.** { *; }

# Hilt
-keep class dagger.hilt.** { *; }
-keep @dagger.hilt.android.HiltAndroidApp class * { *; }
