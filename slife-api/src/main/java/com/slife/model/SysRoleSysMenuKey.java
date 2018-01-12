package com.slife.model;

import java.io.Serializable;

/**
 * @author 
 */
public class SysRoleSysMenuKey implements Serializable {
    /**
     * 系统资源id
     */
    private long sysMenuId;

    /**
     * 系统角色id
     */
    private long sysRoleId;

    private static final long serialVersionUID = 1L;

    public long getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(long sysMenuId) {
        this.sysMenuId = sysMenuId;
    }

    public long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
}