package com.slife.service.impl;

import com.slife.dao.DigcooAnasUserDao;
import com.slife.service.UserService;

import javax.annotation.Resource;

/**
 * Created by cq on 18-1-14.
 */
public class UserServiceImpl implements UserService {
    @Resource
    private DigcooAnasUserDao digcooAnasUserDao;

}
