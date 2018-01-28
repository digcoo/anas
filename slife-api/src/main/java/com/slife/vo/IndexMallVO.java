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
@ApiModel(value="IndexMallVO",description="用于呈现用户首页商业中心")
public class IndexMallVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * mall id
     */
    @ApiModelProperty(value="mall id",name="mallId",required = true)
    private Long mallId;

    /**
     * mall名称
     */
    @ApiModelProperty(value="mall名称",name="name",required = true)
    private String name;

    /**
     * mall图片
     */
    @ApiModelProperty(value="mall图片",name="logo",required = true)
    private String logo;

    @ApiModelProperty(value="纬度",name="latitudes")
    private Double lat;

    @ApiModelProperty(value="经度",name="longitude")
    private Double lng;

    @ApiModelProperty(value="商家数",name="nums",required = false)
    private Integer nums = 0;

    public Long getMallId() {
        return mallId;
    }

    public void setMallId(Long mallId) {
        this.mallId = mallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }
}
