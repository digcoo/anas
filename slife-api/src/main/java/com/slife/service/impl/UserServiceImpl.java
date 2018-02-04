package com.slife.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import javax.annotation.Resource;

import ch.qos.logback.core.util.TimeUtil;
import com.slife.utils.RedisKeysImpl;
import com.slife.vo.AnasTicketVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.slife.dao.ShopDao;
import com.slife.dao.UserDao;
import com.slife.entity.User;
import com.slife.entity.enums.UserType;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.UserService;
import com.slife.util.StringUtils;
import com.slife.vo.SessionKeyVO;
import com.slife.wxapi.request.RequestWechatApi;
import com.slife.wxapi.response.SessionKeyWX;

/**
 * Created by cq on 18-1-19.
 */
@Component
public class UserServiceImpl implements UserService {

    private static final Predicate<User> checkParam = (o) -> {
        return o != null && StringUtils.isNotBlank(o.getOpenId());
    };

    @Resource
    private RequestWechatApi requestWechatApi;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserDao userDao;

    @Resource
    private ShopDao shopDao;

    private static final long TIMEOUT = 30*60*1000;


    @Override
    public SessionKeyVO getSessionKeyWx(String code) {
        SessionKeyWX sessionKey = requestWechatApi.getSessionKey(code);
        SessionKeyVO sessionKeyVO = new SessionKeyVO();
        if (sessionKey == null || StringUtils.isEmpty(sessionKey.getOpenId())) {
            sessionKeyVO.setErrCode(sessionKey.getErrcode());
            sessionKeyVO.setErrMsg(sessionKey.getErrmsg());
        } else {
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
        if (user == null) {
            throw new SlifeException(HttpCodeEnum.USER_NOT_FOUND_ERR);
        }
        return user;
    }

    @Override
    public Integer addUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public Integer editNick(long id, String nick) throws SlifeException {
        User original = userDao.selectByPrimaryKey(id);
        if (original == null) {
            throw new SlifeException(HttpCodeEnum.USER_NOT_FOUND_ERR);
        }
        User userOther = userDao.selectByIdAndNickname(id, nick);
        if (userOther != null) {
            throw new SlifeException(HttpCodeEnum.USER_NICK_DUPLICATE);
        }
        return userDao.updateNick(id, nick);
    }

    @Override
    public Integer editHeadImg(long id, String path) throws SlifeException {
        User user = userDao.selectByPrimaryKey(id);
        if (user == null) {
            throw new SlifeException(HttpCodeEnum.USER_NOT_FOUND_ERR);
        }

        if (UserType.SHOP_USER.getCode() == user.getType()) {
            shopDao.updateLogo(id, path);
        }

        return userDao.updateHeadImg(id, path);
    }

    @Override
    public AnasTicketVO ticket(String code) {
        SessionKeyWX sessionKey = requestWechatApi.getSessionKey(code);
        AnasTicketVO ticketVO = new AnasTicketVO();
        if (sessionKey != null && StringUtils.isEmpty(sessionKey.getOpenId())) {
            return ticketVO.buildError(sessionKey.getErrcode(), sessionKey.getErrmsg());
        }
        String token = cacheSessionTicket(sessionKey.getSessionKey(), sessionKey.getOpenId());
        User user = userDao.selectByOpenId(sessionKey.getOpenId());
        ticketVO.setToken(token);
        ticketVO.setUserId(user == null ? 0 : user.getId());
        return ticketVO;
    }


    String cacheSessionTicket(String sessionKey, String openId) {
        String uuid = UUID.randomUUID().toString();
        String value = openId.join("#", sessionKey);
        stringRedisTemplate.opsForValue().set(RedisKeysImpl.getUserTicket(uuid), value, TIMEOUT, TimeUnit.MICROSECONDS);
        return uuid;
    }
    public String getSessionTicket(String uuid){
        String value = stringRedisTemplate.opsForValue().get(RedisKeysImpl.getUserTicket(uuid));
        if(StringUtils.isNotEmpty(value)){
            stringRedisTemplate.opsForValue().set(RedisKeysImpl.getUserTicket(uuid), value, TIMEOUT, TimeUnit.MICROSECONDS);
        }
        return value;
    }
}
