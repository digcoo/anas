package com.slife.mapper;

import com.slife.model.SysUserSysRoleExample;
import com.slife.model.SysUserSysRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserSysRoleMapper {
    long countByExample(SysUserSysRoleExample example);

    int deleteByExample(SysUserSysRoleExample example);

    int deleteByPrimaryKey(SysUserSysRoleKey key);

    int insert(SysUserSysRoleKey record);

    int insertSelective(SysUserSysRoleKey record);

    List<SysUserSysRoleKey> selectByExample(SysUserSysRoleExample example);

    int updateByExampleSelective(@Param("record") SysUserSysRoleKey record, @Param("example") SysUserSysRoleExample example);

    int updateByExample(@Param("record") SysUserSysRoleKey record, @Param("example") SysUserSysRoleExample example);
}