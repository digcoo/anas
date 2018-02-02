package com.slife.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value="活动新增对象",description="商家发布活动使用到的活动新增对象")
public class AdAddVO {

	@ApiModelProperty(value="商铺id",name="shopId",required = true)
	private long shopId;
	
	@ApiModelProperty(value="活动类型",name="type",required = true,notes = "1-新品,2-开业,3-打折,4-预售,5-其他")
    private int type;
   
	@ApiModelProperty(value="活动标题",name="title",required = true)
    private String title;

	@ApiModelProperty(value="活动内容",name="items",required = true,example="[{\"url\":\"***.jpg\", \"label\":\"32.9\"}, {\"url\":\"***.jpg\", \"label\":\"22.9\"}]")
    private String items;

	@ApiModelProperty(value="活动开始日期",name="startTime",required = false)
	private Date startTime;
	
	@ApiModelProperty(value="活动结束日期",name="endTime",required = false)
	private Date endTime;

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
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
	
}
