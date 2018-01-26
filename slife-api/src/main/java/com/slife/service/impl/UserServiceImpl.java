package com.slife.service.impl;

import com.slife.base.entity.ReturnDTO;
import com.slife.dao.UserDao;
import com.slife.entity.User;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.UserService;
import com.slife.util.ReturnDTOUtil;
import com.slife.util.StringUtils;
import com.slife.vo.SessionKeyVO;
import com.slife.wxapi.request.RequestWechatApi;
import com.slife.wxapi.response.SessionKeyWX;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Predicate;

/**
 * Created by cq on 18-1-19.
 */
@Component
public class UserServiceImpl implements UserService {

    private static final Predicate<User> checkParam = (o) -> {
        if (o == null) {
            return false;
        }
        if (StringUtils.isEmpty(o.getOpenId())) {
            return false;
        }
        return true;
    };

    @Resource
    private RequestWechatApi requestWechatApi;

    @Resource
    private UserDao userDao;


    @Override
    public SessionKeyVO getSessionKeyWx(String code) {
        SessionKeyWX sessionKey = requestWechatApi.getSessionKey(code);
        SessionKeyVO sessionKeyVO = new SessionKeyVO();
        if (sessionKey == null || StringUtils.isEmpty(sessionKey.getOpenId())) {
            return null;
        }else{
            sessionKeyVO.setOpenId(sessionKey.getOpenId());
            sessionKeyVO.setSessionKey(sessionKey.getSessionKey());
            sessionKeyVO.setUnionId(sessionKey.getUnionid());
            User user = userDao.selectByOpenId(sessionKey.getOpenId());
            sessionKeyVO.setNewUser(user != null);
        }
        return sessionKeyVO;
    }

    @Override
    public User getUserByOpenId(String openId) {
        User user = userDao.selectByOpenId(openId);
        if (user ==null){
            throw new SlifeException(HttpCodeEnum.USER_NOT_FOUND_ERR);
        }
        return user;
    }

    @Override
    public Integer addUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public Integer editUser(User user) {
        return userDao.updateByPrimaryKey(user);
    }

    @Override
    public Integer editNick(long id, String nick) throws SlifeException{
        User original = userDao.selectByPrimaryKey(id);
        if (original == null) {
            throw new SlifeException(HttpCodeEnum.USER_NOT_FOUND_ERR);
        }
        User userOther = userDao.selectByIdAndNickname(id,nick);
        if (userOther != null) {
            throw new SlifeException(HttpCodeEnum.USER_NICK_DUPLICATE);
        }
        return userDao.updateNick(id, nick);
    }

    @Override
    public Integer editHeadImg(long id, String path) throws SlifeException{
        User original = userDao.selectByPrimaryKey(id);
        if (original == null) {
            throw new SlifeException(HttpCodeEnum.USER_NOT_FOUND_ERR);
        }
        return userDao.updateHeadImg(id, path);
    }
}
