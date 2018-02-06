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
@ApiModel(value="IndexVO",description="用于呈现用户首页")
public class FavorVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="活动列表",name="ads")
    private List<FavorAdVO> ads;

    public List<FavorAdVO> getAds() {
        return ads;
    }

    public void setAds(List<FavorAdVO> ads) {
        this.ads = ads;
    }
}
