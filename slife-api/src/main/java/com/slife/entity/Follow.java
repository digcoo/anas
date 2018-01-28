package com.slife.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Follow implements Serializable {
    /**
     * 用户收藏
     */
    private Long id;

    /**
     * 关注的商家id
     */
    private Long shopId;

    /**
     * 用户id或商家id
     */
    private Long userId;

    /**
     * 关注时间
     */
    private Date followTime;

    /**
     * 1.关注，2.取消关注
     */
    private Integer status;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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