package com.slife.mapper;

import com.slife.model.ActHiTaskinst;
import com.slife.model.ActHiTaskinstExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiTaskinstMapper {
    long countByExample(ActHiTaskinstExample example);

    int deleteByExample(ActHiTaskinstExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiTaskinst record);

    int insertSelective(ActHiTaskinst record);

    List<ActHiTaskinst> selectByExample(ActHiTaskinstExample example);

    ActHiTaskinst selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiTaskinst record, @Param("example") ActHiTaskinstExample example);

    int updateByExample(@Param("record") ActHiTaskinst record, @Param("example") ActHiTaskinstExample example);

    int updateByPrimaryKeySelective(ActHiTaskinst record);

    int updateByPrimaryKey(ActHiTaskinst record);
}