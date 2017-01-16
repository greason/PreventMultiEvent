package com.greason.multievent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Greason on 16/7/14.
 */
public class TimeUtils {


    private static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMdd");

    public static String getTime() {
        Date date = new Date();
        return dateFormater.format(date);
    }

    public static boolean isClickPrevent(ViewBean viewBean, int delayTime) {
        final long nowTime = System.currentTimeMillis();
        boolean flag = false;
        if (viewBean.clickTimeStamp == 0) {
            flag = false;
        } else if (nowTime - viewBean.clickTimeStamp <= delayTime) {
            flag = true;
        }
        if (!flag) {
            viewBean.clickTimeStamp = nowTime;
        }
        return flag;
    }

    public static boolean isTouchPrevent(ViewBean viewBean, int delayTime) {
        final long nowTime = System.currentTimeMillis();
        boolean flag = false;
        if (viewBean.touchTimeStamp == 0) {
            flag = false;
        } else if (nowTime - viewBean.touchTimeStamp <= delayTime) {
            flag = true;
        }
        if (!flag) {
            viewBean.touchTimeStamp = nowTime;
        }
        return flag;
    }
}
