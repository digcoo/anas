package com.slife.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ShopBaseVO",description = "商家注册基本信息")
public class ShopBaseVO {

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
}
