package com.slife.mapper;

import com.slife.model.ActReProcdef;
import com.slife.model.ActReProcdefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActReProcdefMapper {
    long countByExample(ActReProcdefExample example);

    int deleteByExample(ActReProcdefExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActReProcdef record);

    int insertSelective(ActReProcdef record);

    List<ActReProcdef> selectByExample(ActReProcdefExample example);

    ActReProcdef selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActReProcdef record, @Param("example") ActReProcdefExample example);

    int updateByExample(@Param("record") ActReProcdef record, @Param("example") ActReProcdefExample example);

    int updateByPrimaryKeySelective(ActReProcdef record);

    int updateByPrimaryKey(ActReProcdef record);
}