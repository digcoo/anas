package com.slife.mapper;

import com.slife.model.ActHiActinst;
import com.slife.model.ActHiActinstExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActHiActinstMapper {
    long countByExample(ActHiActinstExample example);

    int deleteByExample(ActHiActinstExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActHiActinst record);

    int insertSelective(ActHiActinst record);

    List<ActHiActinst> selectByExample(ActHiActinstExample example);

    ActHiActinst selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActHiActinst record, @Param("example") ActHiActinstExample example);

    int updateByExample(@Param("record") ActHiActinst record, @Param("example") ActHiActinstExample example);

    int updateByPrimaryKeySelective(ActHiActinst record);

    int updateByPrimaryKey(ActHiActinst record);
}