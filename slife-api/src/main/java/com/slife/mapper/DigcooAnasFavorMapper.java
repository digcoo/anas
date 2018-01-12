package com.slife.mapper;

import com.slife.model.DigcooAnasFavor;
import org.apache.ibatis.annotations.Param;

public interface DigcooAnasFavorMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DigcooAnasFavor record);

    DigcooAnasFavor selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DigcooAnasFavor record);
}