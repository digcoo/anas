package com.slife.entity.enums;

public enum AdType {

    DISCOUNT("DISCOUNT", "打折促销"),
    NEW("NEW", "新品上市"),
    OPEN("OPEN", "新店开业"),
    ADVANCE("ADVANCE", "预告活动"),
    OTHER("OTHER", "其他活动"),
    ;

    AdType( String type, final String desc) {
        this.type = type;
        this.desc = desc;
    }

    private String type;

    private String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
