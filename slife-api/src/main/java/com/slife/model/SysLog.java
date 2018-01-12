package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class SysLog implements Serializable {
    /**
     * 日志id
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
     * 日志来源类型
     */
    private String type;

    /**
     * 模块名称
     */
    private String tag;

    /**
     * 全类名或者操作url
     */
    private String src;

    /**
     * ip
     */
    private String ip;

    /**
     * 信息
     */
    private String msg;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 用户登录名
     */
    private String loginName;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 使用时长 毫米单位
     */
    private long useTime;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }
}