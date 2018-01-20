package com.slife.service.impl;

import com.slife.service.UserService;
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


    @Override
    public SessionKeyWX getSessionKeyWx(String code) {
        SessionKeyWX sessionKey = requestWechatApi.getSessionKey(code);

        return sessionKey;
    }
}
