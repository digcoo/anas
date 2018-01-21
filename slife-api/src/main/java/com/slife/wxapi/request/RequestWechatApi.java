package com.slife.wxapi.request;

import com.slife.httpclient.HttpSendManager;
import com.slife.wxapi.ConstantsUrl;
import com.slife.wxapi.response.SessionKeyWX;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cq on 18-1-19.
 */
@Component
public class RequestWechatApi extends ConstantsUrl {

    @Value("${wechat.appId}")
    private String wechatAppId;

    @Value("${wechat.appSecret}")
    private String wechatAppSecret;

    @Value("${wechat.encodingAESKey}")
    private String encodingAESKey;

    @Resource
    private HttpSendManager httpSendManager;


    /**
     * 获取基础支持 sessionKey
     *
     * @return
     */
    public SessionKeyWX getSessionKey(String code) {
        String url = getRequestUrl(WX_SESSION_KEY);
        Map<String, String> param = new HashedMap();
        param.put("grant_type", "authorization_code");
        param.put("appid", wechatAppId);
        param.put("secret", wechatAppSecret);
        param.put("js_code", code);
        List<NameValuePair> params = filParam(param);
        return httpSendManager.doPost(url, params, SessionKeyWX.class);
    }

    public void getWXUserInfo(String openId,String sessionKey){
        String url = getRequestUrl(WX_USER_INFO);
        Map<String, String> param = new HashedMap();
        param.put("lang", "zh_CN");
        param.put("openid", openId);
        param.put("openid", sessionKey);


    }


    private List<NameValuePair> filParam(Map<String, String> param) {
        if (MapUtils.isEmpty(param)) {
            return null;
        }
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        param.forEach((k, v) -> {
            NameValuePair nameValuePair = new BasicNameValuePair(k, v);
            nameValuePairs.add(nameValuePair);
        });
        return nameValuePairs;
    }

}
