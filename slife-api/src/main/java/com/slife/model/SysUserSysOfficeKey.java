package com.slife.model;

import java.io.Serializable;

/**
 * @author 
 */
public class SysUserSysOfficeKey implements Serializable {
    /**
     * 用户id
     */
    private Long sysUserId;

    /**
     * 系统组织架构id
     */
    private Long sysOfficeId;

    private static final long serialVersionUID = 1L;

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }
}