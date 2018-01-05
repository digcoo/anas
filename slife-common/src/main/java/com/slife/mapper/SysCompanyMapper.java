package com.slife.mapper;

import com.slife.model.SysCompany;
import com.slife.model.SysCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysCompanyMapper {
    long countByExample(SysCompanyExample example);

    int deleteByExample(SysCompanyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysCompany record);

    int insertSelective(SysCompany record);

    List<SysCompany> selectByExample(SysCompanyExample example);

    SysCompany selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysCompany record, @Param("example") SysCompanyExample example);

    int updateByExample(@Param("record") SysCompany record, @Param("example") SysCompanyExample example);

    int updateByPrimaryKeySelective(SysCompany record);

    int updateByPrimaryKey(SysCompany record);
}