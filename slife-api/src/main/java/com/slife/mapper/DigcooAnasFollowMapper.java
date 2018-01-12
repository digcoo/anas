package com.slife.mapper;

import com.slife.model.DigcooAnasFollow;

public interface DigcooAnasFollowMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DigcooAnasFollow record);

    DigcooAnasFollow selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DigcooAnasFollow record);
}