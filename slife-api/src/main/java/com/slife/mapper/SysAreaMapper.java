package com.slife.mapper;

import com.slife.model.SysArea;

public interface SysAreaMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysArea record);


    SysArea selectByPrimaryKey(String id);


    int updateByPrimaryKey(SysArea record);
}