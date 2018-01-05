package com.slife.model;

import java.io.Serializable;

/**
 * @author 
 */
public class SysArea implements Serializable {
    /**
     * 区域id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * dkey
     */
    private String jkey;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getJkey() {
        return jkey;
    }

    public void setJkey(String jkey) {
        this.jkey = jkey;
    }
}