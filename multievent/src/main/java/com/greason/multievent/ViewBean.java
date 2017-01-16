package com.greason.multievent;

/**
 * Created by Greason on 17/1/15.
 */

public class ViewBean {

    private int id;
    public long clickTimeStamp = 0;
    public long touchTimeStamp = 0;
    public boolean isPrevent = true;

    public ViewBean() {

    }

    public ViewBean(int id) {
        this.id = id;

    }

}
