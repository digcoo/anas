package com.slife.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 *
 */
@ApiModel(value="Item",description="用于呈现商家活动内容")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片url
     */
    @ApiModelProperty(value="图片url地址",name="url",required = true)
    private String url;

    /**
     * 图片标签
     */
    @ApiModelProperty(value="图片上的标签",name="label")
    private String label;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
