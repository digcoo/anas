package com.slife.entity.enums;

public enum UserType {

    COMMON(1,"个人用户"),
    
    SHOP_USER(2,"商家用户"),
    
    ;

    private int code;

    private String desc;

    UserType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
