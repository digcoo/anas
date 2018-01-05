package com.slife.mapper;

import com.slife.model.SysUserSysOffice;
import com.slife.model.SysUserSysOfficeExample;
import com.slife.model.SysUserSysOfficeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserSysOfficeMapper {
    long countByExample(SysUserSysOfficeExample example);

    int deleteByExample(SysUserSysOfficeExample example);

    int deleteByPrimaryKey(SysUserSysOfficeKey key);

    int insert(SysUserSysOffice record);

    int insertSelective(SysUserSysOffice record);

    List<SysUserSysOffice> selectByExample(SysUserSysOfficeExample example);

    SysUserSysOffice selectByPrimaryKey(SysUserSysOfficeKey key);

    int updateByExampleSelective(@Param("record") SysUserSysOffice record, @Param("example") SysUserSysOfficeExample example);

    int updateByExample(@Param("record") SysUserSysOffice record, @Param("example") SysUserSysOfficeExample example);

    int updateByPrimaryKeySelective(SysUserSysOffice record);

    int updateByPrimaryKey(SysUserSysOffice record);
}