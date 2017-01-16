package com.greason.multievent.listener;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Greason on 17/1/15.
 */

public interface OnPreventTouchListener {

    void onTouch(View view, MotionEvent motionEvent);
}
