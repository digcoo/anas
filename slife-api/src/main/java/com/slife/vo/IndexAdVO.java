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
@ApiModel(value="IndexAdVO",description="用于呈现用户首页附近所有活动")
public class IndexAdVO implements Serializable {

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
     * 活动ID
     */
    @ApiModelProperty(value="活动ID",name="adId",required = true)
    private Long adId;

    /**
     * 商家logo
     */
    @ApiModelProperty(value="商家logo",name="logo")
    private String logo;

    /**
     * 地址
     */
    @ApiModelProperty(value="商家地址",name="addr",required = true)
    private String addr;

    @ApiModelProperty(value="时间说明",name="timeDesc",required = true)
    private String timeDesc;
    /**
     * 地址
     */
    @ApiModelProperty(value="商家粉丝",name="followNum")
    private Integer followNum;

    @ApiModelProperty(value="活动名称",name="adName",required = true)
    private String adName;

    @ApiModelProperty(value="活动类型",name="type",required = true,notes = "1-新品,2-开业,3-打折,4-预售,5-其他")
    private Byte type;

    @ApiModelProperty(value="商家活动详情",name="items")
    private List<Item> items;

    @ApiModelProperty(value="纬度",name="latitudes")
    private Double lat;

    @ApiModelProperty(value="经度",name="longitude")
    private Double lng;

    @ApiModelProperty(value="距离",name="distance")
    private double distance;

    @ApiModelProperty(value="距离描述",name="distanceDesc")
    private String distanceDesc;


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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getTimeDesc() {
        return timeDesc;
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDistanceDesc() {
        return distanceDesc;
    }

    public void setDistanceDesc(String distanceDesc) {
        this.distanceDesc = distanceDesc;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }
}
