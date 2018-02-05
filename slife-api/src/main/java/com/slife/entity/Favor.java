package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
@TableName("digcoo_anas_favor")
public class Favor implements Serializable {
    /**
     * 用户关注
     */
    private Long id;

    /**
     * 关注的商家id
     */
    private Long adId;

    /**
     * 关注时间
     */
    private Date favor;

    /**
     * 1:收藏，2.取消收藏
     */
    private Integer status;

    /**
     * 用户id或商家id
     */
    private Long userId;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Date getFavor() {
        return favor;
    }

    public void setFavor(Date favor) {
        this.favor = favor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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