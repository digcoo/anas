package com.slife.entity.enums;

public enum ShopStatus {

    RIGISTER_ONE(1,"第一步注册"),
    
    RIGISTER_TWO(2,"第二步注册"),
    
    AUDITED(3,"审核通过"),
    
    UNAUDIT(4,"审核未通过"),
    
    FROZEN(9,"冻结"),
    
    ;

    private int code;

    private String desc;

    ShopStatus(int code, String desc) {
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
