package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class SysDict implements Serializable {
    /**
     * 字典id
     */
    private Long id;

    /**
     * 创建者id
     */
    private Long createId;

    /**
     * 修改者id
     */
    private Long updateId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 删除标记Y：正常；N：删除；A：审核
     */
    private String delFlag;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * jkey
     */
    private String jkey;

    /**
     * jvalue
     */
    private String jvalue;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 路径
     */
    private String path;

    /**
     * 所在公司id
     */
    private Long sysCompanyId;

    /**
     * 图标
     */
    private String icon;

    /**
     * C 表示类，I 表示小类
     */
    private String type;

    /**
     * 是否启用Y 表示是，N表示否
     */
    private String invalid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getJkey() {
        return jkey;
    }

    public void setJkey(String jkey) {
        this.jkey = jkey;
    }

    public String getJvalue() {
        return jvalue;
    }

    public void setJvalue(String jvalue) {
        this.jvalue = jvalue;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(Long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }
}