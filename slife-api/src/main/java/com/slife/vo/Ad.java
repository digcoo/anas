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
@ApiModel(value="活动对象",description="商家主页使用到的活动对象")
public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;


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

    @ApiModelProperty(value="时间说明",name="timeDesc",required = true)
    private String timeDesc;

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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

    public String getTimeDesc() {
        return timeDesc;
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
    }
}
