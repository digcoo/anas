package com.slife.model;

import java.io.Serializable;

/**
 * @author 
 */
public class SysUserSysOffice extends SysUserSysOfficeKey implements Serializable {
    /**
     * 主负责人1，副负责人2，普通员工3
     */
    private String major;

    private static final long serialVersionUID = 1L;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}