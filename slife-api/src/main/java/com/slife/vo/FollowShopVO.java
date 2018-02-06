package com.slife.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="FollowShopVO",description = "关注商家列表")
public class FollowShopVO extends FollowShopBaseVO{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动id",required = true)
    private Long adId;

    @ApiModelProperty(value = "活动名称",required = true)
    private String adName;

    @ApiModelProperty(value = "时间format",required = true)
    private String timeDesc;

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public String getTimeDesc() {
        return timeDesc;
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }
}

