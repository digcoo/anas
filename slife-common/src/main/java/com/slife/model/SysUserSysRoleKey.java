package com.slife.model;

import java.io.Serializable;

/**
 * @author 
 */
public class SysUserSysRoleKey implements Serializable {
    /**
     * 系统用户id
     */
    private Long sysUserId;

    /**
     * 系统角色id
     */
    private Long sysRoleId;

    private static final long serialVersionUID = 1L;

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
}