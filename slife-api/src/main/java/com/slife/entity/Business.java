package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.slife.base.entity.ApiEntity;
import com.slife.base.entity.DataEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@TableName("digcoo_anas_business")
public class Business extends ApiEntity<Business> {


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    private String name;

    private Long parentId;

    private Integer layer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }
}