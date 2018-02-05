package com.slife.entity.enums;

/**
 * 关联状态(用于:关注or收藏状态 公用)
 * Created by cq on 18-1-28.
 */
public enum LinkStatus {
    LINK(1,"关联"),

    UNLINK(2,"取消关联");

    private int code;

    private String desc;

    LinkStatus(int code, String desc) {
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
