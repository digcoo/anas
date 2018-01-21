package com.slife.api.controller;


import com.slife.base.entity.ReturnDTO;
import com.slife.entity.Business;
import com.slife.service.impl.ShopService;
import com.slife.util.ReturnDTOUtil;
import com.slife.vo.BusinessVO;
import com.slife.vo.ShopBaseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/api/shop")
@Api(description = "店铺注册相关接口")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "31-商家注册获取短信验证码", notes = "31-商家注册获取短信验证码",httpMethod = "GET")
    @GetMapping(value = "/requestRegSms")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "phone", value = "手机号码",required = true)
    })
    public ReturnDTO requestRegSms(String  phone, HttpServletRequest request) {

        return shopService.requestRegSms(phone);
    }


    @ApiOperation(value = "31-商家注册步骤1", notes = "32-商家注册步骤1",httpMethod = "POST")
    @PostMapping(value = "/regShopBaseInfo")
    @ResponseBody
    public ReturnDTO regShopBaseInfo(@RequestBody ShopBaseVO shopBaseVO   , HttpServletRequest request) {

        shopService.saveShopBase(shopBaseVO);
        return ReturnDTOUtil.success();
    }
}
