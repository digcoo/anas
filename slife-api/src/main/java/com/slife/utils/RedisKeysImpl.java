package com.slife.utils;

/**
 * Created by cq on 18-2-4.
 */
public class RedisKeysImpl implements RedisKey {

    /**
     * 获取小程序登录临时票据
     * @return
     */
    public static String getUserTicket(String uuid){
        return RedisKey.USER_TICKET+uuid;
    }
}
