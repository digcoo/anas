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
public class IndexVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="mall列表",name="malls")
    private List<IndexMallVO> malls;

    @ApiModelProperty(value="活动列表",name="ads")
    private List<IndexAdVO> ads;

    public List<IndexMallVO> getMalls() {
        return malls;
    }

    public void setMalls(List<IndexMallVO> malls) {
        this.malls = malls;
    }

    public List<IndexAdVO> getAds() {
        return ads;
    }

    public void setAds(List<IndexAdVO> ads) {
        this.ads = ads;
    }
}
