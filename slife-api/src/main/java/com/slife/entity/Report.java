package com.slife.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.ApiEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tod
 * @date 2018/2/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: report
 */
@TableName("digcoo_anas_report")
public class Report extends ApiEntity<Report> {

    private static final long serialVersionUID = 1L;
    /**
     *  举报类型
     */
    private Byte type;

    private Long shopId;

    private Long userId;

    private String content;

    private String proof;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }
}
