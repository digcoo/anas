package com.slife.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value="BusinessVO",description = "行业信息")
public class BusinessVO {

    @ApiModelProperty(value = "行业Id")
    private long id;

    @ApiModelProperty(value = "行业名称")
    private String name;

    @ApiModelProperty(value = "父行业Id")
    private long parentId;

    @ApiModelProperty(value = "下级行业")
    private List<BusinessVO> children;


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

    public List<BusinessVO> getChildren() {
        return children;
    }

    public void setChildren(List<BusinessVO> children) {
        this.children = children;
    }

    public void addChild(BusinessVO businessVO) {
        if(children == null){
            this.children = new ArrayList<BusinessVO>();
        }
        this.children.add(businessVO);
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
}
