package com.slife.mapper;

import com.slife.model.ActHiVarinst;
import com.slife.model.ActHiVarinstExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiVarinstMapper {
    long countByExample(ActHiVarinstExample example);

    int deleteByExample(ActHiVarinstExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiVarinst record);

    int insertSelective(ActHiVarinst record);

    List<ActHiVarinst> selectByExample(ActHiVarinstExample example);

    ActHiVarinst selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiVarinst record, @Param("example") ActHiVarinstExample example);

    int updateByExample(@Param("record") ActHiVarinst record, @Param("example") ActHiVarinstExample example);

    int updateByPrimaryKeySelective(ActHiVarinst record);

    int updateByPrimaryKey(ActHiVarinst record);
}