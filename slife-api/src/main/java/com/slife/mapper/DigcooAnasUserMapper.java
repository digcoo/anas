package com.slife.mapper;

import com.slife.model.DigcooAnasUser;

public interface DigcooAnasUserMapper {

    DigcooAnasUser getUserByOpenId(String openId);

    int deleteByPrimaryKey(long id);

    int insert(DigcooAnasUser record);

    DigcooAnasUser selectByPrimaryKey(long id);

    int updateByPrimaryKey(DigcooAnasUser record);
}