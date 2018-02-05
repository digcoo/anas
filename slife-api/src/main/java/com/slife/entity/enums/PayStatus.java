package com.slife.entity.enums;

public enum PayStatus {

    PRE_PAY(1,"未支付"),
    PAID(2,"支付成功");
    private int index;

    private String message;

    PayStatus(int index, String message) {
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
