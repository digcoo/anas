package com.slife.service.impl;

import com.slife.base.entity.ReturnDTO;
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
    public ReturnDTO getSessionKeyWx(String code) {
        SessionKeyWX sessionKey = requestWechatApi.getSessionKey(code);
        if (sessionKey == null) {
            return null;
        }
        ReturnDTO returnDTO = new ReturnDTO();
        if (StringUtils.isEmpty(sessionKey.getOpenId())) {
            returnDTO.setCode(Integer.parseInt(sessionKey.getErrcode()));
            returnDTO.setError(sessionKey.getErrmsg());
            return returnDTO;
        }
        SessionKeyVO sessionKeyVO = new SessionKeyVO();
        sessionKeyVO.setOpenId(sessionKey.getOpenId());
        sessionKeyVO.setSessionKey(sessionKey.getSessionKey());
        sessionKeyVO.setUnionId(sessionKey.getUnionid());
        User user = userDao.selectByOpenId(sessionKey.getOpenId());
        if (user != null) {
            sessionKeyVO.setNewUser(true);
        }
        returnDTO.setMessage(sessionKeyVO);
        return returnDTO;
    }

    @Override
    public User getUserByOpenId(String openId) {
        return userDao.selectByOpenId(openId);
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
