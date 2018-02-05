package com.slife.dao;

import com.slife.entity.Follow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FollowDao {

    Follow selectByUserIdAndShopId(@Param("userId") long userId, @Param("shopId") long shopId);

    List<Follow> selectByUserId(Map<String, Object> param);

    int deleteByPrimaryKey(long id);

    int insert(Follow record);

    int insertSelective(Follow record);

    Follow selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);
}