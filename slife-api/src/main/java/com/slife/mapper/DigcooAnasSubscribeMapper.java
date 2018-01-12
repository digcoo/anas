package com.slife.mapper;

import com.slife.model.DigcooAnasSubscribe;

public interface DigcooAnasSubscribeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DigcooAnasSubscribe record);

    DigcooAnasSubscribe selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DigcooAnasSubscribe record);
}