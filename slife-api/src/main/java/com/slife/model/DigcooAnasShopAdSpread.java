package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class DigcooAnasShopAdSpread implements Serializable {
    /**
     * 广告推广记录表
     */
    private long id;

    /**
     * 广告id
     */
    private long adId;

    /**
     * 推广活动类型：top-上头条，seed-麦子传播
     */
    private String type;

    private Date gmtCreated;

    private Date gmtUpdated;

    private static final long serialVersionUID = 1L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAdId() {
        return adId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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