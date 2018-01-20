package com.slife.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by cq on 18-1-20.
 */
public class SessionKeyVO implements Serializable{

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 微信sessionKey
     */
    private String sessionKey;

    /**
     * 微信unionid
     */
    private String unionId;

    /**
     * 判断使用是否已经再系统中
     */
    private boolean newUser;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }
}
