package com.slife.mapper;

import com.slife.model.ActIdInfo;
import com.slife.model.ActIdInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActIdInfoMapper {
    long countByExample(ActIdInfoExample example);

    int deleteByExample(ActIdInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActIdInfo record);

    int insertSelective(ActIdInfo record);

    List<ActIdInfo> selectByExampleWithBLOBs(ActIdInfoExample example);

    List<ActIdInfo> selectByExample(ActIdInfoExample example);

    ActIdInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActIdInfo record, @Param("example") ActIdInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ActIdInfo record, @Param("example") ActIdInfoExample example);

    int updateByExample(@Param("record") ActIdInfo record, @Param("example") ActIdInfoExample example);

    int updateByPrimaryKeySelective(ActIdInfo record);

    int updateByPrimaryKeyWithBLOBs(ActIdInfo record);

    int updateByPrimaryKey(ActIdInfo record);
}