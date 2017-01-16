package com.greason.preventmultievent;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Greason on 17/1/15.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CrashReport.initCrashReport(getApplicationContext(), "e9ffc34f12", false);
    }
}
