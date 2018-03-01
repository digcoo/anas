package com.slife.service;

import com.slife.entity.User;
import com.slife.exception.SlifeException;
import com.slife.vo.SessionKeyVO;
import com.slife.wxapi.response.SessionKeyWX;

/**
 * Created by cq on 18-1-19.
 */
public interface UserService {

    SessionKeyWX getSessionKeyWx(String code);

    User getUserByOpenId(String openId);

    Integer addUser(User user);

    Integer editNick(Long userId, String nick) throws SlifeException;

    Integer editHeadImg(Long userId, String path) throws SlifeException;

    //AnasTicketVO ticket(String code);
}
