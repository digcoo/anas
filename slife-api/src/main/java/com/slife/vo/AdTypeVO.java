package com.slife.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="AdTypeVO",description = "广告类型对象")
public class AdTypeVO {

    @ApiModelProperty(value = "广告类型")
    private String type;
    @ApiModelProperty(value = "广告类型描述")
    private String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
