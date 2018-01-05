package com.slife.mapper;

import com.slife.model.ActRuJob;
import com.slife.model.ActRuJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActRuJobMapper {
    long countByExample(ActRuJobExample example);

    int deleteByExample(ActRuJobExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActRuJob record);

    int insertSelective(ActRuJob record);

    List<ActRuJob> selectByExample(ActRuJobExample example);

    ActRuJob selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActRuJob record, @Param("example") ActRuJobExample example);

    int updateByExample(@Param("record") ActRuJob record, @Param("example") ActRuJobExample example);

    int updateByPrimaryKeySelective(ActRuJob record);

    int updateByPrimaryKey(ActRuJob record);
}