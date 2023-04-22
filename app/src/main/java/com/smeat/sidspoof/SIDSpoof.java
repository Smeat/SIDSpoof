package com.smeat.sidspoof;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findMethodBestMatch;

import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class SIDSpoof implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("[SIDSpoof] Loading app");
        init_hooks(lpparam);
    }

    static String PACKAGE_NAME = "com.entersekt.authapp.sparkasse";

    /// old
    /*
    static String ROOT_CLASS_NAME = "com.netcetera.tpmw.core.k.w";
    static String ROOT_METHOD_NAME = "a";
      static String ROOT_CHECK_CLASS = "com.netcetera.tpmw.core.app.presentation.h.g.b.a";
    static String ROOT_CHECK_METHOD = "execute";
    static String OKAY_CLASS = "com.netcetera.tpmw.core.app.presentation.h.b.f";
    static String OKAY_METHOD = "c";

        static String SOURCE_CHECK_CLASS = "com.netcetera.tpmw.core.app.presentation.h.a.b.b.a";
    static String SOURCE_CHECK_METHOD = "execute";
    */

    /// new
    static String ROOT_CLASS_NAME = "ui.w";
    static String ROOT_METHOD_NAME = "a";

    static String ROOT_CHECK_CLASS = "yg.a";
    static String ROOT_CHECK_METHOD = "execute";

    static String OKAY_CLASS = "gg.f$b$b";
    static String OKAY_METHOD = "b";

    static String SOURCE_CHECK_CLASS = "eg.a";
    static String SOURCE_CHECK_METHOD = "execute";

    public void init_hooks(XC_LoadPackage.LoadPackageParam lpparam) {

        final XC_MethodReplacement okMethod = new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                Class<?> activityClass = findClass(OKAY_CLASS, lpparam.classLoader);
                Method activityMethod = findMethodBestMatch(activityClass, OKAY_METHOD);
                return activityMethod.invoke(null);
            }
        };

        if (lpparam.packageName.equals(PACKAGE_NAME)) {
            // Fix root screen
            findAndHookMethod(ROOT_CHECK_CLASS, lpparam.classLoader, ROOT_CHECK_METHOD, okMethod);
            // Fix source screen
            findAndHookMethod(SOURCE_CHECK_CLASS, lpparam.classLoader, SOURCE_CHECK_METHOD, okMethod);
        }
    }
}
