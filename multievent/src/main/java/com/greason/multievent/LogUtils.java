package com.greason.multievent;

import android.util.Log;

/**
 * Created by Greason on 17/1/15.
 */

public final class LogUtils {

    public static void d(String msg) {
        if (Constants.DEBUG) {
            Log.d(Constants.LOG_NAMESPACE, msg);
        }
    }

    public static void i(String msg) {
        if (Constants.DEBUG) {
            Log.d(Constants.LOG_NAMESPACE, msg);
        }
    }

}
