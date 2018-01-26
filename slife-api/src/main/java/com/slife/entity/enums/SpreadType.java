package com.slife.entity.enums;

public enum SpreadType {

    TOP(1, "9元上头条"),
    WHEAT(2, "麦子传播"),
    ;

    public static SpreadType getByCode(int code){
    	for (SpreadType type : SpreadType.values()) {
			if(type.getType() == code){
				return type;
			}
		}
    	return null;
    }
    
    SpreadType(int type, final String desc) {
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
