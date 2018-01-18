package com.slife.vo;


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
public class IndexVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家名称
     */
    private String name;

    /**
     * 商家ID
     */
    private Long shopId;

    /**
     * 商家logo
     */
    private String logo;

    /**
     * 地址
     */
    private String addr;


    /**
     * 地址
     */
    private Integer followNum;


    private List<Item> items;


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
}
