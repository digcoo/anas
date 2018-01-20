package com.slife.service;

import com.slife.wxapi.response.SessionKeyWX;

/**
 * Created by cq on 18-1-19.
 */
public interface UserService {

    public SessionKeyWX getSessionKeyWx(String code);

}
