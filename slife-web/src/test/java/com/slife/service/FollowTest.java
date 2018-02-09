//package com.slife.service;
//
//import com.alibaba.fastjson.JSON;
//import com.slife.WebApplicationTests;
//import com.slife.api.controller.FollowController;
//import com.slife.base.entity.ReturnDTO;
//import com.slife.entity.Shop;
//import org.junit.Test;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created by cq on 18-1-28.
// */
//public class FollowTest extends WebApplicationTests {
//
//    @Resource
//    private FollowController followController;
//
//
//    @Test
//    public void link() {
//        long userId = 1;
//        long shopId = 25;
//        ReturnDTO<Boolean> returnDTO =  followController.link(userId ,shopId);
//        System.out.println("link result:"+ JSON.toJSONString(returnDTO));
//    }
//
//    @Test
//    public void unlink() {
//        long userId = 1;
//        long shopId = 25;
//        ReturnDTO<Boolean> returnDTO =  followController.unlink(userId ,shopId);
//        System.out.println("link result:"+ JSON.toJSONString(returnDTO));
//    }
//
//    @Test
//    public void getShopListByUserId() {
//        long userId  = 1;
//        ReturnDTO<List<Shop>> returnDTO =  followController.shopList(userId);
//        System.out.println("link result:"+ JSON.toJSONString(returnDTO));
//    }
//
//}
