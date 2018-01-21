package com.slife.httpclient;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by cq on 17-1-12.
 */
@Component
public class HttpSendManager {
    private static final Logger _LOG = LoggerFactory.getLogger(HttpSendManager.class);
    @Resource
    private CloseableHttpClient closeableHttpClient;
    @Resource
    private HttpHandleResponse httpHandleResponse;

    public <T> T doPost(String path, List<NameValuePair> params, Class<T> clazz) {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(path);
        try {
            if (CollectionUtils.isNotEmpty(params)) {
                httpPost.setEntity(new UrlEncodedFormEntity(params)); //设置参数
            }
            String returnMsg = closeableHttpClient.execute(httpPost, httpHandleResponse);
            return JSON.parseObject(returnMsg, clazz);
        } catch (IOException e) {
            _LOG.error("doPost error ." + e);
        }
        return null;
    }



    public <T> T doGet(String path, List<NameValuePair> params, Class<T> clazz) {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        try {
            String paramStr = "";
            if (CollectionUtils.isNotEmpty(params)) {
                //设置get请求参数
                paramStr = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
            }
            HttpGet httpget = new HttpGet(path + "?" + paramStr);
            String returnMsg = closeableHttpClient.execute(httpget, httpHandleResponse);
            return JSON.parseObject(returnMsg, clazz);
        } catch (Exception e) {
            _LOG.error("doGet error ." + e);
        }
        return null;
    }
}
