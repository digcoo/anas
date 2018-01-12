package com.slife.mapper;

import com.slife.model.SysDict;

public interface SysDictMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysDict record);

    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SysDict record);
}