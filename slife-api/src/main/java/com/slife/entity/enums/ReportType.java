package com.slife.entity.enums;

public enum ReportType {

    ILLEGAL(1,"商家店铺不存在"),

    HARMFUL(2,"商家发布虚假活动"),

    CHEAT(3,"商家恶意刷屏");

    private int code;

    private String desc;

    ReportType(int code, String desc) {
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
