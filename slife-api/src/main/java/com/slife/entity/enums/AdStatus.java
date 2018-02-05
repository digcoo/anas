package com.slife.entity.enums;

public enum AdStatus {

    INIT(0, "初始"),
    ON(1, "已上架"),
    OFF(2, "已下架"),
    EXPIRED(3, "已过期"),
    DEL(-1, "已删除"),
    ;

    public static AdStatus getByCode(int code){
    	for (AdStatus status : AdStatus.values()) {
			if(status.getStatus() == code){
				return status;
			}
		}
    	return null;
    }
    
    AdStatus(int status, final String desc) {
        this.status = status;
        this.desc = desc;
    }

    private int status;

    private String desc;

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
