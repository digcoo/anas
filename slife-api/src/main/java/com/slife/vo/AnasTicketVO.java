package com.slife.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by cq on 18-2-4.
 */
public class AnasTicketVO {

    @ApiModelProperty("登录token")
    String token;

    @ApiModelProperty("用户ID,用户不存在值为0")
    long userId;

    @ApiModelProperty("微信返回的错误码")
    private String errCode;

    @ApiModelProperty("微信返回的错误信息")
    private String errMsg;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public AnasTicketVO buildError(String errCode ,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
        return this;
    }
}
