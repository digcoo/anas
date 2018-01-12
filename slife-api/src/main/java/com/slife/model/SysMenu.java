package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class SysMenu implements Serializable {
    /**
     * 资源id
     */
    private long id;

    /**
     * 创建者id
     */
    private long createId;

    /**
     * 修改者id
     */
    private long updateId;

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
    private long parentId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 菜单类型，按钮或者menu
     */
    private String type;

    /**
     * 链接地址
     */
    private String href;

    /**
     * 打开方式
     */
    private String target;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否展示
     */
    private String showFlag;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 路径
     */
    private String path;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 所在公司id
     */
    private long sysCompanyId;

    private static final long serialVersionUID = 1L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreateId() {
        return createId;
    }

    public void setCreateId(long createId) {
        this.createId = createId;
    }

    public long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(long updateId) {
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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public long getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }
}