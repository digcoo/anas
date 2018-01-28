package com.slife.service.impl;

import com.slife.dao.FavorDao;
import com.slife.service.FavorService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by cq on 18-1-28.
 */
@Component
public class FavorServiceImpl implements FavorService {

    @Resource
    private FavorDao favorDao;

    @Override
    public boolean link(long userId, long adId) {
        return false;
    }

    @Override
    public boolean unlink(long userId, long adId) {
        return false;
    }

    @Override
    public int total(long adId) {
        return 0;
    }
}
