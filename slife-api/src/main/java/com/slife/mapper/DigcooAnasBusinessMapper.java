package com.slife.mapper;

import com.slife.model.DigcooAnasBusiness;

public interface DigcooAnasBusinessMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DigcooAnasBusiness record);

    DigcooAnasBusiness selectByPrimaryKey(Long id);


    int updateByPrimaryKey(DigcooAnasBusiness record);
}