package com.slife.mapper;

import com.slife.model.SysRoleSysMenuExample;
import com.slife.model.SysRoleSysMenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleSysMenuMapper {
    long countByExample(SysRoleSysMenuExample example);

    int deleteByExample(SysRoleSysMenuExample example);

    int deleteByPrimaryKey(SysRoleSysMenuKey key);

    int insert(SysRoleSysMenuKey record);

    int insertSelective(SysRoleSysMenuKey record);

    List<SysRoleSysMenuKey> selectByExample(SysRoleSysMenuExample example);

    int updateByExampleSelective(@Param("record") SysRoleSysMenuKey record, @Param("example") SysRoleSysMenuExample example);

    int updateByExample(@Param("record") SysRoleSysMenuKey record, @Param("example") SysRoleSysMenuExample example);
}