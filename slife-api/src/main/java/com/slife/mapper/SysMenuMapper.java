package com.slife.mapper;

import com.slife.model.SysMenu;


public interface SysMenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SysMenu record);
}