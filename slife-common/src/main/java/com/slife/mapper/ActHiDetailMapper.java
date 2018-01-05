package com.slife.mapper;

import com.slife.model.ActHiDetail;
import com.slife.model.ActHiDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiDetailMapper {
    long countByExample(ActHiDetailExample example);

    int deleteByExample(ActHiDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiDetail record);

    int insertSelective(ActHiDetail record);

    List<ActHiDetail> selectByExample(ActHiDetailExample example);

    ActHiDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiDetail record, @Param("example") ActHiDetailExample example);

    int updateByExample(@Param("record") ActHiDetail record, @Param("example") ActHiDetailExample example);

    int updateByPrimaryKeySelective(ActHiDetail record);

    int updateByPrimaryKey(ActHiDetail record);
}