package com.slife.vo;


import java.io.Serializable;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 *
 */
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片url
     */
    private String url;

    /**
     * 图片标签
     */
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
