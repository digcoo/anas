package com.slife.mapper;

import com.slife.model.SysConfig;
import com.slife.model.SysConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysConfigMapper {
    long countByExample(SysConfigExample example);

    int deleteByExample(SysConfigExample example);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    List<SysConfig> selectByExample(SysConfigExample example);

    int updateByExampleSelective(@Param("record") SysConfig record, @Param("example") SysConfigExample example);

    int updateByExample(@Param("record") SysConfig record, @Param("example") SysConfigExample example);
}