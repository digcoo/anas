package com.slife.dao;

import com.slife.base.dao.CrudDao;
import com.slife.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by cq on 18-1-20.
 */
public interface UserDao extends CrudDao<User> {

    public User selectUserByOpendId(@Param("openId") String openId);
}
