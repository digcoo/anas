package com.slife.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.ApiEntity;

/**
 * @author duyp
 * @date 2018/1/26
 * Describe: merchant实体映射
 */
@TableName("digcoo_anas_shop_ad_spread")
public class ShopAdSpread extends ApiEntity<ShopAdSpread> {

    @TableField(value = "ad_id")
    private Long adId;
    
    @TableField(value = "type")
    private Byte type = Byte.valueOf("0");
    
    public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }
}
