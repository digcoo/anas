package com.slife.mapper;

import com.slife.model.SysUserSysOffice;
import com.slife.model.SysUserSysOfficeKey;

public interface SysUserSysOfficeMapper {

    int deleteByPrimaryKey(SysUserSysOfficeKey key);

    int insert(SysUserSysOffice record);

    SysUserSysOffice selectByPrimaryKey(SysUserSysOfficeKey key);

    int updateByPrimaryKey(SysUserSysOffice record);
}