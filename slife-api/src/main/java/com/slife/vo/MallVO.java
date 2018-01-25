package com.slife.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="MallVO",description = "商业中心信息")
public class MallVO {

    @ApiModelProperty(value = "mallId",required = true)
    private  long mallId;

    @ApiModelProperty(value = "商业中心名称",required = true)
    private  String name;
    
    public MallVO(){}
    
    public MallVO(long mallId, String name){
    	this.mallId = mallId;
    	this.name = name;
    }

	public long getMallId() {
		return mallId;
	}

	public void setMallId(long mallId) {
		this.mallId = mallId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

