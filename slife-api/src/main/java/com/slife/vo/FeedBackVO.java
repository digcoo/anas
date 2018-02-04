package com.slife.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="意见反馈对象",description="意见反馈对象")
public class FeedBackVO {

	@ApiModelProperty(value="用户id",name="userId",required = true)
	private long userId;
	
	@ApiModelProperty(value="反馈内容",name="content",required = true)
    private String content;

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

}
