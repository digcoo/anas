package com.slife.mapper;

import com.slife.model.ActGeBytearray;
import com.slife.model.ActGeBytearrayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActGeBytearrayMapper {
    long countByExample(ActGeBytearrayExample example);

    int deleteByExample(ActGeBytearrayExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActGeBytearray record);

    int insertSelective(ActGeBytearray record);

    List<ActGeBytearray> selectByExampleWithBLOBs(ActGeBytearrayExample example);

    List<ActGeBytearray> selectByExample(ActGeBytearrayExample example);

    ActGeBytearray selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActGeBytearray record, @Param("example") ActGeBytearrayExample example);

    int updateByExampleWithBLOBs(@Param("record") ActGeBytearray record, @Param("example") ActGeBytearrayExample example);

    int updateByExample(@Param("record") ActGeBytearray record, @Param("example") ActGeBytearrayExample example);

    int updateByPrimaryKeySelective(ActGeBytearray record);

    int updateByPrimaryKeyWithBLOBs(ActGeBytearray record);

    int updateByPrimaryKey(ActGeBytearray record);
}