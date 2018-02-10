package com.slife.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.alibaba.fastjson.JSONObject;
import com.slife.base.entity.ReturnDTO;
import com.slife.entity.User;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.UserService;
import com.slife.service.impl.ShopAdService;
import com.slife.service.impl.ShopService;
import com.slife.utils.RedisKey;
import com.slife.utils.SlifeRedisTemplate;
import com.slife.utils.XMLParser;
import com.slife.vo.AdAddVO;
import com.slife.vo.AdUpdateVO;
import com.slife.vo.PrepayVO;
import com.slife.vo.ShopBaseVO;
import com.slife.vo.ShopMallVO;
import com.slife.vo.ShopVO;

@Controller
@RequestMapping(value = "/api/shop")
@Api(description = "商家操作相关接口")
public class ShopController {

    protected Logger logger= LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ShopService shopService;
    
    @Autowired
    private ShopAdService shopAdService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SlifeRedisTemplate slifeRedisTemplate;


    @ApiOperation(value = "30-获取店铺信息", notes = "30-获取店铺信息",httpMethod = "GET")
    @GetMapping(value = "/getShopInfo")
    @ResponseBody
    @ApiImplicitParams({ 
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", required = true)
    })
    public ReturnDTO<ShopVO> getShopInfo(@RequestParam(value = "digcoo_session_key",required = true) String digcooSessionKey) {
    	String sessionKeyAndOpenId = slifeRedisTemplate.getDigcooSessionKey(digcooSessionKey);
        //session 过期
        if(StringUtils.isBlank(sessionKeyAndOpenId)){
            throw new SlifeException(HttpCodeEnum.USER_SESSION_EXPIRED);
        }else{
            String[] sessionKeyAndOpenIdArray =sessionKeyAndOpenId.split(RedisKey.DIGCOO_SESSION_KEY_DELIMITER);
            User user = userService.getUserByOpenId(sessionKeyAndOpenIdArray[1]);
            if(user == null){
            	throw new SlifeException(HttpCodeEnum.USER_NOT_FOUND_ERR);
            }
            return shopService.getShopInfo(user.getId());
        }
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
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条",required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Long", name = "shopId", value = "商铺id",required = true) })
    @GetMapping(value = "/ad/list_for_shop")
    @ResponseBody
    public ReturnDTO listForShop(@Param("index") int index, @Param("shopId") Long shopId, HttpServletRequest request) {
    	return shopAdService.listForShop(shopId, index);
    }
    
//    
//    /**
//     * todo 这里应该有惩罚恶意刷单现象：需要对价格有阶梯式涨价机制
//     * @param adId
//     * @param request
//     * @return
//     */
//    @ApiOperation(value = "D-11 9元上头条", notes = "9元上头条",httpMethod = "POST")
//    @PostMapping(value = "/ad/up9")
//    @ResponseBody
//    public ReturnDTO upAd(
//    		@Param("adId") Long adId, HttpServletRequest request) {
//    	logger.debug("[ShopController]-[upAd] : adId = " + adId);
//    	return shopAdService.upShopAd(adId);
//    }


    @ApiOperation(value = "D-12 发起支付广告费用", notes = "支付广告费用",httpMethod = "POST")   
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", required = true),
    })
    @PostMapping(value = "/ad/pay")
    @ResponseBody
    public ReturnDTO<PrepayVO>  payAd(
    		@RequestParam(value = "digcoo_session_key",required = true) String digcooSessionKey,
    		HttpServletRequest request) {
    	
        logger.debug("[ShopController]-[payAd] : sessionKey = " + digcooSessionKey);
        
        String sessionKeyAndOpenId = slifeRedisTemplate.getDigcooSessionKey(digcooSessionKey);
        //session 过期
        if(StringUtils.isBlank(sessionKeyAndOpenId)){
            throw new SlifeException(HttpCodeEnum.USER_SESSION_EXPIRED);
        }else{
            String[] sessionKeyAndOpenIdArray =sessionKeyAndOpenId.split(RedisKey.DIGCOO_SESSION_KEY_DELIMITER);
            User user = userService.getUserByOpenId(sessionKeyAndOpenIdArray[1]);
            if(user == null){
            	throw new SlifeException(HttpCodeEnum.USER_NOT_FOUND_ERR);
            }
            return shopAdService.payAd(user.getId());
        }
    }

    @ApiOperation(value = "Cj-13 支付回调接口", notes = "支付回调接口",httpMethod = "POST")
    @PostMapping(value = "/wxPayCallBack")
    @ResponseBody
    public String  wxPayCallBack( HttpServletRequest request) {
        try{
            InputStream in = request.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            String str = null;
            StringBuffer xmlBuffer = new StringBuffer();
            while ((str = bf.readLine()) != null) {
                xmlBuffer.append(str);
            }
            String callBackXml = xmlBuffer.toString();
            logger.debug("[ShopController]-[wxPayCallBack] : callBack : " + callBackXml);

            JSONObject callBackObject = XMLParser.getMapFromXML(callBackXml);
            callBackObject.put("callBackBody",callBackXml);
            if("SUCCESS".equals(callBackObject.getString("result_code"))){
                if( shopAdService.paySuccess(callBackObject)){
                    return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                }
            }else{
                return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[ERROR]]></return_msg></xml>";
            }
        }catch (Throwable th){
            logger.error("[ShopController]-[wxPayCallBack] error : ", th);

        }
        return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[ERROR]]></return_msg></xml>";

    }
}
