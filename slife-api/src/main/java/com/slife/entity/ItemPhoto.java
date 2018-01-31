package com.slife.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.slife.base.entity.ApiEntity;

@TableName("digcoo_anas_item_photo")
public class ItemPhoto extends ApiEntity<ItemPhoto> {

	@TableId(value = "ID", type = IdType.AUTO)
	private Long id;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    private Long referId;

    private String title;
    
    private String tag;
    
    private byte category;

    private String bigPhoto;

    private String smallPhoto;

    private byte recommend;		//首页显示

	public Long getReferId() {
		return referId;
	}

	public void setReferId(Long referId) {
		this.referId = referId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public byte getCategory() {
		return category;
	}

	public void setCategory(byte category) {
		this.category = category;
	}

	public String getBigPhoto() {
		return bigPhoto;
	}

	public void setBigPhoto(String bigPhoto) {
		this.bigPhoto = bigPhoto;
	}

	public String getSmallPhoto() {
		return smallPhoto;
	}

	public void setSmallPhoto(String smallPhoto) {
		this.smallPhoto = smallPhoto;
	}

	public byte getRecommend() {
		return recommend;
	}

	public void setRecommend(byte recommend) {
		this.recommend = recommend;
	}
	
}