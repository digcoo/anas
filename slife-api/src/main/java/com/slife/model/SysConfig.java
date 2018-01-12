package com.slife.model;

import java.io.Serializable;

/**
 * @author 
 */
public class SysConfig implements Serializable {
    /**
     * 备注信息
     */
    private String remark;

    /**
     * 删除标记Y：正常；N：删除；A：审核
     */
    private String delFlag;

    /**
     * key
     */
    private String jkey;

    /**
     * value
     */
    private String jvalue;

    /**
     * 排序
     */
    private int sort;

    private static final long serialVersionUID = 1L;

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}