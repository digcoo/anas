package com.slife.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="AdTypeVO",description = "广告类型对象")
public class AdTypeVO {

    @ApiModelProperty(value = "广告类型")
    private int type;
    @ApiModelProperty(value = "广告类型描述")
    private String desc;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
