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

	@ApiModelProperty(value = "标签", name = "tag", required = false)
	private String tag;

	@ApiModelProperty(value = "单品名称", name = "title", required = true)
	private String title;

	@ApiModelProperty(value = "小图", name = "smallPhoto", required = true)
	private String smallPhoto;

	@ApiModelProperty(value = "大图", name = "bigPhoto", required = true)
	private String bigPhoto;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
