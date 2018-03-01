package com.slife.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by cq on 18-1-20.
 */
public class SessionKeyVO implements Serializable{

    /**
     * 应用sessionKey
     */
    private String digcooSessionKey;

    /**
     * user对象
     */
    private UserVO userVO;
    
    private String errCode;
    
    private String errMsg;

    public String getDigcooSessionKey() {
        return digcooSessionKey;
    }

    public void setDigcooSessionKey(String digcooSessionKey) {
        this.digcooSessionKey = digcooSessionKey;
    }

    public UserVO getUser() {
        return userVO;
    }

    public void setUser(UserVO user) {
        this.userVO = user;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
