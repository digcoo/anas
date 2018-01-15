package com.slife.service;

import com.slife.model.DigcooAnasUser;
import com.slife.model.entity.Result;
import org.springframework.stereotype.Service;


/**
 * Created by cq on 18-1-14.
 */
@Service
public interface UserService {
    /**
     * 根绝openId查询用户信息
     * @param openId
     * @return
     */
    Result<DigcooAnasUser> getUserByOpenId(String openId);

    /**
     * 编辑用户昵称
     * @param userId
     * @param nick
     * @return
     */
    Result<Boolean> editNick(long userId,String nick);

    /**
     * 修改用户头像
     * @param userId
     * @param headImg
     * @return
     */
    Result<Boolean> editHeadImg(long userId ,String headImg);

    public
}
