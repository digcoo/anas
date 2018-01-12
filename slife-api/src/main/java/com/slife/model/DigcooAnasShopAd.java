package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class DigcooAnasShopAd implements Serializable {
    private long id;

    /**
     * new-新品,open-开业,discount-打折,presell-预售,other-其他
     */
    private String type;

    /**
     * 广告内容
     */
    private String title;

    /**
     * 活动的图片、价格等，json字符串
     */
    private String items;

    private Date startTime;

    private Date endTime;

    /**
     * 收藏数
     */
    private int favorNum;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 状态：normal-正常，init-初始，pre_publish-预发布，expired-过期
     */
    private String status;

    private Date gmtCreated;

    private Date gmtUpdated;

    private static final long serialVersionUID = 1L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getFavorNum() {
        return favorNum;
    }

    public void setFavorNum(int favorNum) {
        this.favorNum = favorNum;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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