package com.slife.mapper;

import com.slife.model.DigcooAnasUser;

public interface DigcooAnasUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DigcooAnasUser record);

    DigcooAnasUser selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DigcooAnasUser record);
}