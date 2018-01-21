package com.slife.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Feedback implements Serializable {
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

    private Date createDate;

    private Date updateDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}