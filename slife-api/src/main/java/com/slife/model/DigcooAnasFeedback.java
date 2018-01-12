package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class DigcooAnasFeedback implements Serializable {
    /**
     * 用户举报
     */
    private long id;

    /**
     * 提交者用户id
     */
    private long userId;

    /**
     * 反馈建议
     */
    private String content;

    private Date gmtCreated;

    private Date gmtUpdated;

    private static final long serialVersionUID = 1L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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