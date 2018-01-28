package com.slife.service.impl;

import com.slife.dao.FollowDao;
import com.slife.service.FollowService;

import javax.annotation.Resource;

/**
 * Created by cq on 18-1-28.
 */
public class FollowServiceImpl implements FollowService {

    @Resource
    private FollowDao followDao;

    @Override
    public boolean link(long userId, long shopId) {
        return false;
    }

    @Override
    public boolean unlink(long userId, long shopId) {
        return false;
    }
}
