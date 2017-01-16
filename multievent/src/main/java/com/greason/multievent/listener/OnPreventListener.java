package com.greason.multievent.listener;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Greason on 17/1/15.
 */

public interface OnPreventListener {

    void onClick(View view);

    void onTouch(View view, MotionEvent motionEvent);
}
