package com.slife.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import com.alibaba.fastjson.JSON;
import com.slife.WebApplicationTests;
import com.slife.base.entity.ReturnDTO;
import com.slife.service.impl.ShopService;

/**
 * Created by tod.tao on 2018/1/23.
 */
public class SmsServiceTest extends WebApplicationTests {
	
    @Autowired
    private ShopService shopService;
    @Autowired
    private CacheManager cacheManager;

    @Test
    public void requestRegSms(){
    	ReturnDTO requestRegSms = shopService.requestRegSms("13764611339");
    	System.out.println(JSON.toJSONString(requestRegSms));
    	assertThat(requestRegSms).isNotNull();
    }
    
}