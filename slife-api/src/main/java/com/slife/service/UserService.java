package com.slife.service;

import com.slife.entity.User;
import com.slife.vo.SessionKeyVO;
import com.slife.wxapi.response.SessionKeyWX;

/**
 * Created by cq on 18-1-19.
 */
public interface UserService {

    SessionKeyVO getSessionKeyWx(String code);


    User getUserByOpenId(String openId);

    boolean addUser(User user);

    User editUser(User user);
}
