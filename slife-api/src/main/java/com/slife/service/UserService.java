package com.slife.service;

import com.slife.entity.User;
import com.slife.exception.SlifeException;
import com.slife.vo.SessionKeyVO;

/**
 * Created by cq on 18-1-19.
 */
public interface UserService {

    SessionKeyVO getSessionKeyWx(String code);


    User getUserByOpenId(String openId);

    Integer addUser(User user);

    Integer editUser(User user);

    Integer editNick(long id, String nick) throws SlifeException;

    Integer editHeadImg(long id, String path) throws SlifeException;
}
