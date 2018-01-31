package com.slife.entity.enums;

public enum ShopType {

    STREET(1,"底层商铺"),
    
    MALL(2,"商业中心"),
    
    OTHER(3,"其他"),
    ;

    private int code;

    private String desc;

    ShopType(int code, String desc) {
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
