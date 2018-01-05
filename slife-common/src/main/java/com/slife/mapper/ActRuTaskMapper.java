package com.slife.mapper;

import com.slife.model.ActRuTask;
import com.slife.model.ActRuTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActRuTaskMapper {
    long countByExample(ActRuTaskExample example);

    int deleteByExample(ActRuTaskExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActRuTask record);

    int insertSelective(ActRuTask record);

    List<ActRuTask> selectByExample(ActRuTaskExample example);

    ActRuTask selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActRuTask record, @Param("example") ActRuTaskExample example);

    int updateByExample(@Param("record") ActRuTask record, @Param("example") ActRuTaskExample example);

    int updateByPrimaryKeySelective(ActRuTask record);

    int updateByPrimaryKey(ActRuTask record);
}