package com.slife.api.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.User;
import com.slife.service.UserService;
import com.slife.util.ReturnDTOUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by cq on 18-1-19.
 */
@RequestMapping("/user/")
@RestController
public class UserController {
    @Resource
    private UserService userService;


    @ApiOperation(value = "获取微信SessionKey", notes = "根据wx.login获取的code得到session key")
    @ApiImplicitParam(name = "code", paramType = "code", dataType = "String", required = true)
    @GetMapping("getSessionKey")
    public ReturnDTO getSessionKeyWx(@RequestParam("code") String code) {
        return userService.getSessionKeyWx(code);
    }


    @ApiOperation(value = "获取用户信息", notes = "根据微信openId获取用户信息")
    @ApiImplicitParam(name = "openId", paramType = "String", dataType = "String", required = true)
    @GetMapping("getByOpenId")
    public ReturnDTO getUserByOpenId(String openId){
        User user = userService.getUserByOpenId(openId);
        return new ReturnDTO(user);
    }

    @ApiOperation(value = "添加新用户", notes = "将微信获取的用户信息添加到系统")
    @ApiImplicitParam(name = "user", paramType = "Object", dataType = "User", required = true)
    @PostMapping("add")
    public ReturnDTO addUser(@RequestParam("user") User user) {
        boolean result = userService.addUser(user);
        if (result) {
            return ReturnDTOUtil.fail();
        }
        ReturnDTO returnDTO = new ReturnDTO();
        returnDTO.setMessage(true);
        return returnDTO;
    }
}
