package com.slife.dao;

import com.slife.base.dao.CrudDao;
import com.slife.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by cq on 18-1-20.
 */
public interface UserDao extends CrudDao<User> {

    User selectByPrimaryKey(@Param("id") long id);

    User selectByOpenId(@Param("openId") String openId);

    int updateByPrimaryKey(@Param("user") User record);

    int updateNick(@Param("id") long id ,@Param("nick") String nick);

    int updateHeadImg(@Param("id") long id ,@Param("headImg") String headImg);



}
