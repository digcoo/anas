package com.slife.mapper;

import com.slife.model.ActEvtLog;
import com.slife.model.ActEvtLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActEvtLogMapper {
    long countByExample(ActEvtLogExample example);

    int deleteByExample(ActEvtLogExample example);

    int deleteByPrimaryKey(Long logNr);

    int insert(ActEvtLog record);

    int insertSelective(ActEvtLog record);

    List<ActEvtLog> selectByExampleWithBLOBs(ActEvtLogExample example);

    List<ActEvtLog> selectByExample(ActEvtLogExample example);

    ActEvtLog selectByPrimaryKey(Long logNr);

    int updateByExampleSelective(@Param("record") ActEvtLog record, @Param("example") ActEvtLogExample example);

    int updateByExampleWithBLOBs(@Param("record") ActEvtLog record, @Param("example") ActEvtLogExample example);

    int updateByExample(@Param("record") ActEvtLog record, @Param("example") ActEvtLogExample example);

    int updateByPrimaryKeySelective(ActEvtLog record);

    int updateByPrimaryKeyWithBLOBs(ActEvtLog record);

    int updateByPrimaryKey(ActEvtLog record);
}