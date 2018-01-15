package com.slife.dao;

import com.slife.mapper.DigcooAnasUserMapper;
import com.slife.model.DigcooAnasUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by cq on 18-1-14.
 */
@Component
public class DigcooAnasUserDao {

    @Resource
    private DigcooAnasUserMapper digcooAnasUserMapper;

    public DigcooAnasUser getUserByOpenId(String openId){

        return digcooAnasUserMapper.getUserByOpenId(openId);
    }
}
