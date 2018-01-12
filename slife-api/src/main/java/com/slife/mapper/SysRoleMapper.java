package com.slife.mapper;

import com.slife.model.SysRole;

public interface SysRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(Long id);


    int updateByPrimaryKey(SysRole record);
}