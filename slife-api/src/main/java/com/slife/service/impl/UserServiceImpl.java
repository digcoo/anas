package com.slife.service.impl;

import com.slife.dao.UserDao;
import com.slife.entity.User;
import com.slife.service.UserService;
import com.slife.util.StringUtils;
import com.slife.vo.SessionKeyVO;
import com.slife.wxapi.request.RequestWechatApi;
import com.slife.wxapi.response.SessionKeyWX;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by cq on 18-1-19.
 */
@Component
public class UserServiceImpl implements UserService {

    @Resource
    private RequestWechatApi requestWechatApi;

    @Resource
    private UserDao userDao;


    @Override
    public SessionKeyVO getSessionKeyWx(String code) {
        SessionKeyWX sessionKey = requestWechatApi.getSessionKey(code);
        if (sessionKey == null) {
            return null;
        }
        if (StringUtils.isEmpty(sessionKey.getOpenId())) {
            return null;
        }
        SessionKeyVO sessionKeyVO = new SessionKeyVO();
        sessionKeyVO.setOpenId(sessionKey.getOpenId());
        sessionKeyVO.setSessionKey(sessionKey.getSessionKey());
        sessionKeyVO.setUnionId(sessionKey.getUnionid());
        User user = userDao.selectUserByOpendId(sessionKey.getOpenId());
        if (user != null) {
            sessionKeyVO.setNewUser(true);
        }
        return sessionKeyVO;
    }

    @Override
    public User getUserByOpenId(String openId) {
        return userDao.selectUserByOpendId(openId);
    }

    @Override
    public boolean addUser(User user) {
        int addCount = userDao.insert(user);
        return addCount == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public User editUser(User user) {
        return null;
    }
}
