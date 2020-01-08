package com.example.fininuiapplication;

import android.app.Application;

import com.example.fininuiapplication.utils.TypefaceUtil;

public class FinInApplication extends Application {

    @Override
    public void onCreate() {
        // Override default_user font if required. Uncomment the following to revert to
        // default_user android behaviour
        TypefaceUtil.overrideFonts(getApplicationContext());

        super.onCreate();
    }
}
