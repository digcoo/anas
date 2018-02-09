//package com.slife.service;
//
//import com.alibaba.fastjson.JSON;
//import com.slife.WebApplicationTests;
//import com.slife.api.controller.FavorController;
//import com.slife.api.controller.FollowController;
//import com.slife.base.entity.ReturnDTO;
//import com.slife.entity.ShopAd;
//import org.junit.Test;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created by cq on 18-1-28.
// */
//public class FavorTest extends WebApplicationTests {
//
//    @Resource
//    private FavorController favorController;
//
//
//    @Test
//    public void link() {
//        long userId = 1;
//        long shopAddId = 1;
//        ReturnDTO<Boolean> returnDTO =  favorController.link(userId ,shopAddId);
//        System.out.println("link result:"+ JSON.toJSONString(returnDTO));
//    }
//
//    @Test
//    public void unlink() {
//        long userId = 1;
//        long shopAddId = 1;
//        ReturnDTO<Boolean> returnDTO =  favorController.unlink(userId ,shopAddId);
//        System.out.println("link result:"+ JSON.toJSONString(returnDTO));
//    }
//
//    @Test
//    public void getShopListByUserId() {
//        long userId = 1;
//        ReturnDTO<List<ShopAd>> returnDTO =  favorController.shopAdList(userId);
//        System.out.println("link result:"+ JSON.toJSONString(returnDTO));
//    }
//
//}
