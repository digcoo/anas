package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class DigcooAnasBusiness implements Serializable {
    /**
     * 行业表
     */
    private long id;

    private String name;

    private long parentId;

    /**
     * 层级
     */
    private int layer;

    private Date gmtCreated;

    private Date gmtUpdated;

    private static final long serialVersionUID = 1L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtUpdated() {
        return gmtUpdated;
    }

    public void setGmtUpdated(Date gmtUpdated) {
        this.gmtUpdated = gmtUpdated;
    }
}