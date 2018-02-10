package com.slife.dao;

import org.apache.ibatis.annotations.Param;

import com.slife.entity.User;

/**
 * Created by cq on 18-1-20.
 */
public interface UserDao {
    Integer insert(User record);

    User selectByPrimaryKey(@Param("id") long id);

    User selectByIdAndNickname(@Param("id") long id,@Param("nickname") String nickname);

    User selectByOpenId(@Param("openId") String openId);

    int updateByPrimaryKey(User record);

    int updateNick(@Param("userId") Long userId, @Param("nick") String nick);

    int updateHeadImg(@Param("userId") Long userId, @Param("headImg") String headImg);

    int upgradeToShop(@Param("userId") long userId, @Param("type") int type);

}
