package com.slife.model;

import java.io.Serializable;

/**
 * @author 
 */
public class SysUserSysRoleKey implements Serializable {
    /**
     * 系统用户id
     */
    private long sysUserId;

    /**
     * 系统角色id
     */
    private long sysRoleId;

    private static final long serialVersionUID = 1L;

    public long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
}