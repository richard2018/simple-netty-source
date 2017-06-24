package com.hw.demo8;

import java.io.Serializable;

/**
 * Created by Lee on 2017/6/24.
 */
public class Command implements Serializable {

    private static final long serialVersionUID = 7590999461767050471L;

    private String actionName;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}