package com.slife.dao;

import com.slife.entity.Favor;
import com.slife.entity.Follow;

import java.util.List;
import java.util.Map;

public interface FollowDao {

    List<Favor> selectByPrimaryKey(Map<String,Object> param);

    int deleteByPrimaryKey(long id);

    int insert(Follow record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);
}