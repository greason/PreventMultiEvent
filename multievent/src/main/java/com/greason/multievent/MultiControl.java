package com.greason.multievent;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.greason.multievent.listener.OnPreventClickListener;
import com.greason.multievent.listener.OnPreventListener;
import com.greason.multievent.listener.OnPreventTouchListener;

import java.util.Iterator;
import java.util.WeakHashMap;

/**
 * Created by Greason on 17/1/15.
 */

public class MultiControl {

    private int clickDelayTime = Constants.CLICK_DELAY_TIME;
    private int touchDelayTime = Constants.TOUCH_DELAY_TIME;

    private int forceCleanNum = Constants.FORCE_CLEAN_NUM;

    private WeakHashMap<View, ViewBean> viewMap;

    private static MultiControl mMultiControl;

    private MultiControl() {
        viewMap = new WeakHashMap<>();
    }

    public static MultiControl getInstance() {
        if (mMultiControl == null) {
            synchronized (MultiControl.class) {
                if (mMultiControl == null) {
                    mMultiControl = new MultiControl();
                }
                return mMultiControl;
            }
        }
        return mMultiControl;
    }

    public boolean isClickPrevent(View view) {
        ViewBean viewBean = viewMap.get(view);
        if (viewBean.isPrevent && TimeUtils.isClickPrevent(viewBean, clickDelayTime)) {
            return true;
        }
        return false;
    }

    public boolean isTouchPrevent(View view) {
        ViewBean viewBean = viewMap.get(view);
        if (viewBean.isPrevent && TimeUtils.isClickPrevent(viewBean, touchDelayTime)) {
            return true;
        }
        return false;
    }

    public void addView(@NonNull View view) {
        refreshMap(view);
    }

    private void refreshMap(View view) {
        if (view == null) {
            return;
        }
        viewMap.put(view, new ViewBean());
        if (viewMap.size() > forceCleanNum) {
            clean();
        }
    }

    public void addView(@NonNull View view, @NonNull OnPreventListener listener) {
        refreshMap(view, listener);
    }

    private void refreshMap(View view, final OnPreventListener listener) {
        refreshMap(view);
        if (listener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewBean viewBean = viewMap.get(view);
                    if (viewBean.isPrevent && TimeUtils.isClickPrevent(viewBean, clickDelayTime)) {
                        LogUtils.d("view click is prevented.");
                        return;
                    }
                    listener.onClick(view);

                    LogUtils.d("view click.");
                }
            });
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    ViewBean viewBean = viewMap.get(view);
                    if (viewBean.isPrevent && TimeUtils.isTouchPrevent(viewBean, touchDelayTime)) {
                        LogUtils.d("view touch is prevented.");
                        return false;
                    }
                    listener.onTouch(view, motionEvent);

                    LogUtils.d("view touch.");
                    return false;
                }
            });
        }
    }

    public void addView(@NonNull View view, @NonNull OnPreventClickListener listener) {
        refreshMap(view, listener);
    }

    private void refreshMap(View view, final OnPreventClickListener listener) {
        refreshMap(view);
        if (listener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewBean viewBean = viewMap.get(view);
                    if (viewBean.isPrevent && TimeUtils.isClickPrevent(viewBean, clickDelayTime)) {
                        LogUtils.d("view click is prevented.");
                        return;
                    }
                    listener.onClick(view);

                    LogUtils.d("view click.");
                }
            });
        }
    }

    public void addView(@NonNull View view, @NonNull OnPreventTouchListener listener) {
        refreshMap(view, listener);
    }

    private void refreshMap(View view, final OnPreventTouchListener listener) {
        refreshMap(view);
        if (listener != null) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    ViewBean viewBean = viewMap.get(view);
                    if (viewBean.isPrevent && TimeUtils.isTouchPrevent(viewBean, touchDelayTime)) {
                        LogUtils.d("view touch is prevented.");
                        return false;
                    }
                    if (listener != null) {
                        listener.onTouch(view, motionEvent);
                    }

                    LogUtils.d("view touch.");
                    return false;
                }
            });
        }
    }

    public void onDestroy() {
        clean();
    }

    private void clean() {
        if (viewMap.isEmpty()) {
            return;
        }
        Iterator<View> iterator = viewMap.keySet().iterator();
        while (iterator.hasNext()) {
            View view = iterator.next();
            if (view.getContext() instanceof Activity) {
                if (view == null) {
                    iterator.remove();
                }
                Activity activity = (Activity) view.getContext();
                if (activity.isFinishing()) {
                    iterator.remove();
                }
            }
        }
    }

    public void setClickDelayTime(int delay_time) {
        this.clickDelayTime = delay_time;
    }

    public void setTouchDelayTime(int touchDelayTime) {
        this.touchDelayTime = touchDelayTime;
    }

    public int getClickDelayTime() {
        return clickDelayTime;
    }

    public int getTouchDelayTime() {
        return touchDelayTime;
    }

    public int getForceCleanNum() {
        return forceCleanNum;
    }

    public void setForceCleanNum(int forceCleanNum) {
        if (forceCleanNum > Constants.FORCE_CLEAN_NUM) {
            this.forceCleanNum = forceCleanNum;
        }
    }
}
