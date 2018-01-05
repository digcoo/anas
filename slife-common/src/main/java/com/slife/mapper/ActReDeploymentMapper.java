package com.slife.mapper;

import com.slife.model.ActReDeployment;
import com.slife.model.ActReDeploymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActReDeploymentMapper {
    long countByExample(ActReDeploymentExample example);

    int deleteByExample(ActReDeploymentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActReDeployment record);

    int insertSelective(ActReDeployment record);

    List<ActReDeployment> selectByExample(ActReDeploymentExample example);

    ActReDeployment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActReDeployment record, @Param("example") ActReDeploymentExample example);

    int updateByExample(@Param("record") ActReDeployment record, @Param("example") ActReDeploymentExample example);

    int updateByPrimaryKeySelective(ActReDeployment record);

    int updateByPrimaryKey(ActReDeployment record);
}