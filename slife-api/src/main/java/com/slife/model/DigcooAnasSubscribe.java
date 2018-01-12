package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class DigcooAnasSubscribe implements Serializable {
    /**
     * 用户订阅
     */
    private long id;

    /**
     * 用户关注的所有行业id,json字符串表示
     */
    private String businessId;

    private long userId;

    private Date gmtCreated;

    private Date gmtUpdated;

    private static final long serialVersionUID = 1L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtUpdated() {
        return gmtUpdated;
    }

    public void setGmtUpdated(Date gmtUpdated) {
        this.gmtUpdated = gmtUpdated;
    }
}