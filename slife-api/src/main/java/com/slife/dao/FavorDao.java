package com.slife.dao;

import com.slife.entity.Favor;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FavorDao {

    List<Favor> selectByUserId(Map<String,Object> param);

    Favor selectByUserIdAndAdId(@Param("userId") long userId,@Param("adId") long adId);

    int deleteByPrimaryKey(long id);

    int insert(Favor record);

    int insertSelective(Favor record);

    Favor selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(Favor record);

    int updateByPrimaryKey(Favor record);
}