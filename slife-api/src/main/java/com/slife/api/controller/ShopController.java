package com.slife.api.controller;


import com.slife.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    
    @ApiOperation(value = "D-7 活动上架", notes = "活动上架",httpMethod = "POST")
    @PostMapping(value = "/ad/publish")
    @ResponseBody
    public ReturnDTO publishAd(@Param("adId") Long adId, HttpServletRequest request) {
    	logger.debug("[ShopController]-[publishAd] : adId = " + adId);
    	return shopAdService.publishShopAd(adId);
    }
    
    @ApiOperation(value = "D-8 活动下架", notes = "活动下架",httpMethod = "POST")
    @PostMapping(value = "/ad/off")
    @ResponseBody
    public ReturnDTO offAd(@Param("adId") Long adId, HttpServletRequest request) {
    	logger.debug("[ShopController]-[shieldAd] : adId = " + adId);
    	return shopAdService.offShopAd(adId);
    }
    
    @ApiOperation(value = "D-9 活动删除", notes = "活动删除",httpMethod = "POST")
    @PostMapping(value = "/ad/del")
    @ResponseBody
    public ReturnDTO delAd(@Param("adId") Long adId, HttpServletRequest request) {
    	logger.debug("[ShopController]-[delAd] : adId = " + adId);
    	return shopAdService.delShopAd(adId);
    }
    
    @ApiOperation(value = "D-10 商家活动列表接口（商家自查）", notes = "商家活动列表接口（商家自查）",httpMethod = "GET")
    @PostMapping(value = "/ad/list_for_shop")
    @ResponseBody
    public ReturnDTO listForShop(@Param("index") int index, @Param("shopId") Long shopId, HttpServletRequest request) {
    	return shopAdService.listForShop(shopId, index);
    }
    
    
    /**
     * todo 这里应该有惩罚恶意刷单现象：需要对价格有阶梯式涨价机制
     * @param adId
     * @param request
     * @return
     */
    @ApiOperation(value = "D-11 9元上头条", notes = "9元上头条",httpMethod = "POST")
    @PostMapping(value = "/ad/up9")
    @ResponseBody
    public ReturnDTO upAd(@Param("adId") Long adId, HttpServletRequest request) {
    	logger.debug("[ShopController]-[upAd] : adId = " + adId);
    	return shopAdService.upShopAd(adId);
    }


    @ApiOperation(value = "D-12 发起支付广告费用", notes = "支付广告费用",httpMethod = "POST")
    @PostMapping(value = "/ad/pay")
    @ResponseBody
    public ReturnDTO<PrepayVO>  payAd(long userId, HttpServletRequest request) {
        logger.debug("[ShopController]-[payAd] : userId = " + userId);
        return shopAdService.payAd(userId);
    }
}
