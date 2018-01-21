package com.slife.wxapi;

/**
 * Created by cq on 18-1-19.
 */
public class ConstantsUrl {

    public static final String WX_URL = "https://api.weixin.qq.com/";
    /**
     * 获取微信openId 和 sessionKey
     */
    public static final String WX_SESSION_KEY = "sns/jscode2session?";

    public static final String WX_USER_INFO = "sns/userinfo?";


    public static final String getRequestUrl(String uri) {
        StringBuilder stringBuilder = new StringBuilder(WX_URL);
        return stringBuilder.append(uri).toString();
    }

}
