package com.slife.dao;

import com.slife.base.dao.CrudDao;
import com.slife.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by cq on 18-1-20.
 */
public interface UserDao {
    Integer insert(User record);

    User selectByPrimaryKey(@Param("id") long id);

    User selectByOpenId(@Param("openId") String openId);

    int updateByPrimaryKey(User record);

    int updateNick(@Param("id") long id ,@Param("nick") String nick);

    int updateHeadImg(@Param("id") long id ,@Param("headImg") String headImg);



}
