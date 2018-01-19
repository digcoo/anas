package com.slife.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 *
 */
@ApiModel(value="商家主页",description="用于呈现用户看到的商家主页")
public class ShopHomeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家名称
     */
    @ApiModelProperty(value="商家名称",name="name",required = true)
    private String name;

    /**
     * 商家ID
     */
    @ApiModelProperty(value="商家ID",name="shopId",required = true)
    private Long shopId;

    /**
     * 商家logo
     */
    @ApiModelProperty(value="商家logo",name="logo")
    private String logo;

    /**
     * 商家logo
     */
    @ApiModelProperty(value="商家背景图",name="picture")
    private String picture;

    /**
     * 地址
     */
    @ApiModelProperty(value="商家地址",name="addr",required = true)
    private String addr;


    /**
     * 地址
     */
    @ApiModelProperty(value="商家粉丝",name="followNum")
    private Integer followNum;

    @ApiModelProperty(value="活动对象",name="adName",required = true)
    private List<Ad> ads;

    @ApiModelProperty(value="纬度",name="latitudes")
    private Double lat;

    @ApiModelProperty(value="经度",name="longitude")
    private Double lng;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }


    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
