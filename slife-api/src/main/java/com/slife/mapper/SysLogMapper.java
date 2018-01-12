package com.slife.mapper;

import com.slife.model.SysLog;

public interface SysLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SysLog record);


}