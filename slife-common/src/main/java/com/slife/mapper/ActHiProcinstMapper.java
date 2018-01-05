package com.slife.mapper;

import com.slife.model.ActHiProcinst;
import com.slife.model.ActHiProcinstExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiProcinstMapper {
    long countByExample(ActHiProcinstExample example);

    int deleteByExample(ActHiProcinstExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiProcinst record);

    int insertSelective(ActHiProcinst record);

    List<ActHiProcinst> selectByExample(ActHiProcinstExample example);

    ActHiProcinst selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiProcinst record, @Param("example") ActHiProcinstExample example);

    int updateByExample(@Param("record") ActHiProcinst record, @Param("example") ActHiProcinstExample example);

    int updateByPrimaryKeySelective(ActHiProcinst record);

    int updateByPrimaryKey(ActHiProcinst record);
}