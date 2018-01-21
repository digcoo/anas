package com.slife.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ShopMallVO",description = "商家注册认证信息")
public class ShopMallVO {


    @ApiModelProperty(value = "用户Id",required = true)
    private  long userId;

    @ApiModelProperty(value = "店铺类型",required = true)
    private String shopType;

    @ApiModelProperty(value = "购物中心",required = true)
    private long mallId;

    @ApiModelProperty(value = "楼层",required = true)
    private String floor;

    @ApiModelProperty(value = "门牌号",required = true)
    private String room;

    @ApiModelProperty(value = "身份图片",required = true)
    private String agentIdentifyCard;

    @ApiModelProperty(value = "营业执照",required = true)
    private String businessLicense;
}
