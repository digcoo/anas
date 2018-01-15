package com.slife.controller;

import com.slife.model.DigcooAnasUser;
import com.slife.model.entity.Result;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cq on 18-1-14.
 */
@RestController
public class UserController {

    /**
     * 根据微信openId获取用户信息
     * @param openId
     * @return
     */
    public Result<DigcooAnasUser> getUserInfo(String openId){

        return
    }
}
