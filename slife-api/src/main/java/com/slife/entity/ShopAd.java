package com.slife.entity;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import org.springframework.data.annotation.Transient;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.ApiEntity;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant实体映射
 */
@TableName("digcoo_anas_shop_ad")
public class ShopAd extends ApiEntity<ShopAd> {
    /**
     * varchar(20) 活动类型
     */
    private Byte type;
    /**
     * varchar(500) 活动标题
     */
    private String title;

    /**
     * varchar(500) 内容
     */
    private String items;

    /**
     * varchar(500) 活动收藏数
     */
    private Integer favorNum = 0;
    /**
     * varchar(15) 活动状态
     */
    private Byte status = Byte.valueOf("0");

    /**
     * varchar(50) geo编码
     */
    private String geohash;

    private Long shopId;
    /**
     * 创建日期
     */
    private Date startTime;


    /**
     * 更新日期
     */
    private Date endTime;

    /**
     * 更新日期
     */
    private Date publishTime;

    /**
     * 商铺名称：冗余字段
     */
    private String shopName;

    /**
     * 行业id：冗余字段
     */
    private Long businessId;

    @TableField(exist=false)
    private Byte flag;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFavorNum() {
        return favorNum;
    }

    public void setFavorNum(Integer favorNum) {
        this.favorNum = favorNum;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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


    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

}
