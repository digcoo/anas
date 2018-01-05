package com.slife.mapper;

import com.slife.model.ActIdGroup;
import com.slife.model.ActIdGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActIdGroupMapper {
    long countByExample(ActIdGroupExample example);

    int deleteByExample(ActIdGroupExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActIdGroup record);

    int insertSelective(ActIdGroup record);

    List<ActIdGroup> selectByExample(ActIdGroupExample example);

    ActIdGroup selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActIdGroup record, @Param("example") ActIdGroupExample example);

    int updateByExample(@Param("record") ActIdGroup record, @Param("example") ActIdGroupExample example);

    int updateByPrimaryKeySelective(ActIdGroup record);

    int updateByPrimaryKey(ActIdGroup record);
}