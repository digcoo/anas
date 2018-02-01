package com.slife.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import com.alibaba.fastjson.JSON;
import com.slife.WebApplicationTests;
import com.slife.vo.FeedBackVO;

/**
 * Created by tod.tao on 2018/1/23.
 */
public class FeedBackServiceTest extends WebApplicationTests {
	
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private CacheManager cacheManager;

    @Test
    public void addFeedBack(){
    	FeedBackVO feedback = new FeedBackVO();
    	feedback.setContent("我想说你这个产品做的垃圾，你承认吗?????");
    	feedback.setUserId(1l);
    	boolean addFeedBack = feedbackService.addFeedBack(feedback);
    	System.out.println(JSON.toJSONString(addFeedBack));
    }
    
}