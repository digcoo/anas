package com.slife.model;

import java.io.Serializable;

/**
 * @author 
 */
public class SysRoleSysMenuKey implements Serializable {
    /**
     * 系统资源id
     */
    private Long sysMenuId;

    /**
     * 系统角色id
     */
    private Long sysRoleId;

    private static final long serialVersionUID = 1L;

    public Long getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(Long sysMenuId) {
        this.sysMenuId = sysMenuId;
    }

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
}