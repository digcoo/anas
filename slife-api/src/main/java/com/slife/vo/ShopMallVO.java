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

    @ApiModelProperty(value = "身份图片正反面",required = true, example="{\"ID_A\":\"123.png\", \"ID_B\":\"234.png\"}")
    private String agentIdentifyCard;

    @ApiModelProperty(value = "正面照片",required = true)
    private String agentPortrait;

    @ApiModelProperty(value = "营业执照",required = false)
    private String businessLicense;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public long getMallId() {
        return mallId;
    }

    public void setMallId(long mallId) {
        this.mallId = mallId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getAgentIdentifyCard() {
        return agentIdentifyCard;
    }

    public void setAgentIdentifyCard(String agentIdentifyCard) {
        this.agentIdentifyCard = agentIdentifyCard;
    }

    public String getAgentPortrait() {
        return agentPortrait;
    }

    public void setAgentPortrait(String agentPortrait) {
        this.agentPortrait = agentPortrait;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }
}
