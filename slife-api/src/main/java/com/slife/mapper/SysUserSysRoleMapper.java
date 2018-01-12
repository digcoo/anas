package com.slife.mapper;

import com.slife.model.SysUserSysRoleKey;

public interface SysUserSysRoleMapper {

    int deleteByPrimaryKey(SysUserSysRoleKey key);

    int insert(SysUserSysRoleKey record);
}