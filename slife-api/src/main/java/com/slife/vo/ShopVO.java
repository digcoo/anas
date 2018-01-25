package com.slife.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ShopVO",description = "商家详细信息")
public class ShopVO {


    @ApiModelProperty(value = "id",required = true)
    private  long id;

    @ApiModelProperty(value = "用户Id",required = true)
    private  long userId;


    @ApiModelProperty(value = "商家名称",required = true)
    private  String shopName;


    @ApiModelProperty(value = "职位",required = true)
    private  String position;


    @ApiModelProperty(value = "联系电话",required = true)
    private  String phone;


    @ApiModelProperty(value = "验证码",required = true)
    private  String phoneCode;


    @ApiModelProperty(value = "商家地址",required = true)
    private  String addr;

    @ApiModelProperty(value = "纬度",required = true)
    private double lat;

    @ApiModelProperty(value = "经度",required = true)
    private double lng;

    @ApiModelProperty(value = "geo编码",required = true)
    private String geohash;


    @ApiModelProperty(value = "行业Id",required = true)
    private  long  businessId;

    @ApiModelProperty(value = "接听电话",required = true)
    private  boolean  openMobile;


    @ApiModelProperty(value = "店铺类型",required = true)
    private String shopType;

    @ApiModelProperty(value = "购物中心",required = true)
    private long mallId;

    @ApiModelProperty(value = "楼层",required = true)
    private String floor;

    @ApiModelProperty(value = "门牌号",required = true)
    private String room;

    @ApiModelProperty(value = "身份图片正反面",required = true)
    private String agentIdentifyCard;

    @ApiModelProperty(value = "正面照片",required = true)
    private String agentPortrait;

    @ApiModelProperty(value = "营业执照",required = true)
    private String businessLicense;


    @ApiModelProperty(value = "审核状态 0-编辑状态 1:提交审核2:审核通过,3:审核未通过",required = true)
    private int auditState;

    @ApiModelProperty(value = "商家logo",required = true)
    private String logo;

    @ApiModelProperty(value = "店铺图片",required = true)
    private String picture;

    @ApiModelProperty(value = "用户关注数量",required = true)
    private int followum;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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



    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getFollowum() {
        return followum;
    }

    public void setFollowum(int followum) {
        this.followum = followum;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public boolean isOpenMobile() {
        return openMobile;
    }

    public void setOpenMobile(boolean openMobile) {
        this.openMobile = openMobile;
    }

    public int getAuditState() {
        return auditState;
    }

    public void setAuditState(int auditState) {
        this.auditState = auditState;
    }
}

