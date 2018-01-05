package com.slife.mapper;

import com.slife.model.ActReModel;
import com.slife.model.ActReModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActReModelMapper {
    long countByExample(ActReModelExample example);

    int deleteByExample(ActReModelExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActReModel record);

    int insertSelective(ActReModel record);

    List<ActReModel> selectByExample(ActReModelExample example);

    ActReModel selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActReModel record, @Param("example") ActReModelExample example);

    int updateByExample(@Param("record") ActReModel record, @Param("example") ActReModelExample example);

    int updateByPrimaryKeySelective(ActReModel record);

    int updateByPrimaryKey(ActReModel record);
}