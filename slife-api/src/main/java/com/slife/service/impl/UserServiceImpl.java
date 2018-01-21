package com.slife.service.impl;

import com.slife.base.entity.ReturnDTO;
import com.slife.dao.UserDao;
import com.slife.entity.User;
import com.slife.enums.HttpCodeEnum;
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
        if (user == null) {
            return false;
        }
        if (StringUtils.isEmpty(user.getOpenId())) {
            return false;
        }
        int addCount = userDao.insert(user);
        return addCount == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public ReturnDTO<Boolean> editUser(User user) {
        if (checkParam.test(user)) {
            return ReturnDTOUtil.paramError();
        }
        if (user.getId() <= 0) {
            return ReturnDTOUtil.paramError();
        }
        User original = userDao.selectByPrimaryKey(user.getId());
        if (original == null) {
            return ReturnDTOUtil.custom(HttpCodeEnum.USER_NOT_FOUND_ERR);
        }
        int editCount = userDao.updateByPrimaryKey(user);
        boolean bl = editCount == 1 ? Boolean.TRUE : Boolean.FALSE;
        ReturnDTO<Boolean> returnDTO = new ReturnDTO<>(bl);
        return returnDTO;
    }

    @Override
    public ReturnDTO<Boolean> editNick(long id, String nick) {
        if (id <= 0 || StringUtils.isEmpty(nick)) {
            return ReturnDTOUtil.paramError();
        }
        User original = userDao.selectByPrimaryKey(id);
        if (original == null) {
            return ReturnDTOUtil.custom(HttpCodeEnum.USER_NOT_FOUND_ERR);
        }
        if (nick.equals(original.getNick())) {
            return ReturnDTOUtil.custom(HttpCodeEnum.USER_NICK_DUPLICATE);
        }
        int editCount = userDao.updateNick(id, nick);
        boolean bl = editCount == 1 ? Boolean.TRUE : Boolean.FALSE;
        ReturnDTO<Boolean> returnDTO = new ReturnDTO<>(bl);
        return returnDTO;
    }

    @Override
    public ReturnDTO<Boolean> editHeadImg(long id, String path) {
        if (id <= 0 || StringUtils.isEmpty(path)) {
            return ReturnDTOUtil.paramError();
        }
        User original = userDao.selectByPrimaryKey(id);
        if (original == null) {
            return ReturnDTOUtil.custom(HttpCodeEnum.USER_NOT_FOUND_ERR);
        }
        int editCount = userDao.updateHeadImg(id, path);
        boolean bl = editCount == 1 ? Boolean.TRUE : Boolean.FALSE;
        ReturnDTO<Boolean> returnDTO = new ReturnDTO<>(bl);
        return returnDTO;
    }
}
