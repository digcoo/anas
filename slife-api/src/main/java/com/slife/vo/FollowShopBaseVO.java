package com.slife.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="FollowShopBaseVO",description = "关注商家列表")
public class FollowShopBaseVO implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商家名称",required = true)
    private  String shopName;


    @ApiModelProperty(value = "商家地址",required = true)
    private  String addr;

    @ApiModelProperty(value = "纬度",required = true)
    private double lat;

    @ApiModelProperty(value = "经度",required = true)
    private double lng;

    @ApiModelProperty(value = "geo编码",required = true)
    private String geohash;

    @ApiModelProperty(value = "商家logo",required = true)
    private String logo;

    @ApiModelProperty(value = "店铺图片",required = true)
    private String picture;

    @ApiModelProperty(value = "商家id",required = true)
    private Long shopId;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

}

