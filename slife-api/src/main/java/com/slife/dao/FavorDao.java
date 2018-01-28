package com.slife.dao;

import com.slife.entity.Favor;

import java.util.List;
import java.util.Map;

public interface FavorDao {

    List<Favor> selectByPrimaryKey(Map<String,Object> param);

    int deleteByPrimaryKey(Long id);

    int insert(Favor record);

    int insertSelective(Favor record);

    Favor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Favor record);

    int updateByPrimaryKey(Favor record);
}