# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\tool\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-injars yyjia1.jar
-outjars yyjia.jar

-libraryjars lib\android.jar
-libraryjars lib\alipaysdk.jar
-libraryjars lib\alipaysecsdk.jar
-libraryjars lib\alipayutdid.jar
-libraryjars lib\android-support-v4.jar
-libraryjars lib\smc_sdk_v1.4.jar
-libraryjars lib\HeepayPlugin_v1.7.jar
-libraryjars lib\TenpayServiceSDK.jar
-libraryjars lib\alipaySdk-20160825.jar
-libraryjars lib\volly-1.0.11.jar
-libraryjars lib\umeng-analytics-v6.1.0.jar

-dontoptimize
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose
-ignorewarnings


-keep public class * extends android.app.Activity

-keep public class * extends android.app.Application

-keep public class * extends android.app.Service

-keep public class * extends android.content.BroadcastReceiver

-keep public class * extends android.content.ContentProvider

-keep public class * extends android.app.backup.BackupAgentHelper

-keep public class * extends android.preference.Preference

-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

-keep class * extends android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-keep class com.yyjia.sdk.center.GMcenter {
    <fields>;
    <methods>;
}

-keep class com.yyjia.sdk.listener.** {
    <fields>;
    <methods>;
}

-keep class com.yyjia.sdk.data.Information {
    <fields>;
    <methods>;
}

-keep public final interface  * extends android.widget.LinearLayout

-keep public final interface  * extends android.widget.LinearLayout.LayoutParams

-keep class com.yyjia.sdk.util.Utils {
    <fields>;
    <methods>;
}

-keep class baiwen.mandaring.base64.** {
    <fields>;
    <methods>;
}

# Also keep - Enumerations. Keep the special static methods that are required in
# enumeration classes.
-keepclassmembers enum  * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep names - Native method names. Keep all native class/method names.
-keepclasseswithmembers,allowshrinking class * {
    native <methods>;
}