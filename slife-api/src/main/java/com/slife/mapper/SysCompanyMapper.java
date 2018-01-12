package com.slife.mapper;

import com.slife.model.SysCompany;

public interface SysCompanyMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysCompany record);

    SysCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SysCompany record);
}