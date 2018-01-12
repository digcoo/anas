package com.slife.mapper;

import com.slife.model.SysOffice;

public interface SysOfficeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysOffice record);

    SysOffice selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SysOffice record);
}