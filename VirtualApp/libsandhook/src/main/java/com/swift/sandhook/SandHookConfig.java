package com.swift.sandhook;

import android.annotation.SuppressLint;
import android.os.Build;

import com.swift.sandhook.lib.BuildConfig;

public class SandHookConfig {
public static int getPreviewSDKInt() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                return Build.VERSION.PREVIEW_SDK_INT;
            } catch (Throwable e) {
                // ignore
            }
        }
        return 0;
    }
    public static int SDK_INT = 0;

    static {
        if(getPreviewSDKInt() > 0)
        {
            SDK_INT = Build.VERSION.SDK_INT + 1;
        }
        else
        {
            SDK_INT = Build.VERSION.SDK_INT;
        }
    }
    //Debug status of hook target process
    public volatile static boolean DEBUG = true;
    //Enable compile with jit
    public volatile static boolean compiler = true;
    public volatile static ClassLoader initClassLoader;
    public volatile static int curUser = 0;
    public volatile static boolean delayHook = true;

    public volatile static String libSandHookPath;
    public volatile static LibLoader libLoader = new LibLoader() {
        @SuppressLint("UnsafeDynamicallyLoadedCode")
        @Override
        public void loadLib() {
            if (SandHookConfig.libSandHookPath == null) {
                System.loadLibrary("sandhook");
            } else {
                System.load(SandHookConfig.libSandHookPath);
            }
        }
    };

    public interface LibLoader {
        void loadLib();
    }
}
