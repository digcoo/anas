package com.slife.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author duyp
 * @date 2018/1/23
 */
@ApiModel(value = "图片库对象", description = "商家上传图片时，选择在线图片库－主页使用到的图片对象")
public class ItemPhotoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "大分类", name = "category", required = true, notes="1:进口食品，2:食品饮料，3-粮油副食，4-美容洗护，5-家具家电，6-家庭清洁，7-母婴用品，8-生鲜水果")
	private byte category;
	
	@ApiModelProperty(value = "小图", name = "smallPhoto", required = true)
	private String smallPhoto;

	@ApiModelProperty(value = "大图", name = "bigPhoto", required = true)
	private String bigPhoto;
	
	public ItemPhotoVO(){}
	
	public ItemPhotoVO(byte category, String smallPhoto, String bigPhoto){
		this.category = category;
		this.smallPhoto = smallPhoto;
		this.bigPhoto = bigPhoto;
	}

	public byte getCategory() {
		return category;
	}

	public void setCategory(byte category) {
		this.category = category;
	}

	public String getSmallPhoto() {
		return smallPhoto;
	}

	public void setSmallPhoto(String smallPhoto) {
		this.smallPhoto = smallPhoto;
	}

	public String getBigPhoto() {
		return bigPhoto;
	}

	public void setBigPhoto(String bigPhoto) {
		this.bigPhoto = bigPhoto;
	}
	
}
