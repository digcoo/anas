package com.slife.entity;

/**
 * Created by cq on 18-1-10.
 */
public class Result<T> {

    private String code;

    private String desc;

    private boolean isSuccess;

    private T t;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
