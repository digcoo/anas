package com.slife.entity.enums;

/**
 * Created by cq on 18-1-21.
 */
public enum  Gender {
    UNKNOWN(0,"未知"),
    MEN(1,"男"),
    WOMEN(2,"女");


    private int index;

    private String message;

    Gender(int index, String message) {
        this.index = index;
        this.message = message;
    }

    public int getIndex() {
        return index;
    }

    public String getMessage() {
        return message;
    }
}
