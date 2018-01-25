package com.slife.entity.enums;

public enum AdType {

    DISCOUNT(1, "打折促销"),
    NEW(2, "新品上市"),
    OPEN(3, "新店开业"),
    ADVANCE(4, "预告活动"),
    OTHER(5, "其他活动"),
    ;

    public static AdType getByCode(int code){
    	for (AdType type : AdType.values()) {
			if(type.getType() == code){
				return type;
			}
		}
    	return null;
    }
    
    AdType(int type, final String desc) {
        this.type = type;
        this.desc = desc;
    }

    private int type;

    private String desc;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
