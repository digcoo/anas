package com.slife.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slife.base.entity.ReturnDTO;
import com.slife.service.impl.ShopAdService;
import com.slife.service.impl.ShopService;
import com.slife.vo.AdAddVO;
import com.slife.vo.AdUpdateVO;
import com.slife.vo.ShopBaseVO;
import com.slife.vo.ShopMallVO;
import com.slife.vo.ShopVO;

@Controller
@RequestMapping(value = "/api/shop")
@Api(description = "店铺注册相关接口")
public class ShopController {

    protected Logger logger= LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ShopService shopService;
    
    @Autowired
    private ShopAdService shopAdService;


    @ApiOperation(value = "30-获取店铺信息", notes = "30-获取店铺信息",httpMethod = "GET")
    @GetMapping(value = "/getShopInfo")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "long", name = "userId", value = "用户Id",required = true)
    })
    public ReturnDTO<ShopVO> getShopInfo(@RequestParam("userId") long userId) {
        return shopService.getShopInfo(userId);

    }

    @ApiOperation(value = "31-商家注册获取短信验证码", notes = "31-商家注册获取短信验证码",httpMethod = "GET")
    @GetMapping(value = "/requestRegSms")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "phone", value = "手机号码",required = true)
    })
    public ReturnDTO requestRegSms(String  phone, HttpServletRequest request) {
        return shopService.requestRegSms(phone);
    }




    @ApiOperation(value = "32-商家注册步骤1", notes = "32-商家注册步骤1",httpMethod = "POST")
    @PostMapping(value = "/submitShopBaseInfo")
    @ResponseBody
    public ReturnDTO<ShopBaseVO> submitShopBaseInfo(@RequestBody ShopBaseVO shopBaseVO   , HttpServletRequest request) {
        return shopService.saveShopBase(shopBaseVO);
    }


    @ApiOperation(value = "33-商家注册步骤2", notes = "32-商家注册步骤1",httpMethod = "POST")
    @PostMapping(value = "/submitShopMallInfo")
    @ResponseBody
    public ReturnDTO submitShopMallInfo(@RequestBody ShopMallVO shopMallVO   , HttpServletRequest request) {
        return shopService.saveShop(shopMallVO);
    }
    
    @ApiOperation(value = "D-5 新建活动", notes = "新建活动",httpMethod = "POST")
    @PostMapping(value = "/ad/add")
    @ResponseBody
    public ReturnDTO addAd(@RequestBody AdAddVO shopAd, HttpServletRequest request) {
    	logger.debug("[ShopController]-[addAd] : params = " + JSON.toJSONString(shopAd));
        return shopAdService.addShopAd(shopAd);
    }
    
    
    @ApiOperation(value = "D-6 编辑活动", notes = "编辑活动",httpMethod = "POST")
    @PostMapping(value = "/ad/update")
    @ResponseBody
    public ReturnDTO updateAd(@RequestBody AdUpdateVO shopAd, HttpServletRequest request) {
    	logger.debug("[ShopController]-[updateAd] : params = " + JSON.toJSONString(shopAd));
    	return shopAdService.updateShopAd(shopAd);
    }
}
