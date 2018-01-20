package com.slife.api.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.User;
import com.slife.service.UserService;
import com.slife.util.ReturnDTOUtil;
import com.slife.vo.IndexVO;
import com.slife.vo.SessionKeyVO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by cq on 18-1-19.
 */
@RequestMapping("/user/")
@RestController
@Api(description = "获取用户信息相关接口")
public class UserController {
    @Resource
    private UserService userService;


    @ApiOperation(value = "获取微信SessionKey", notes = "根据wx.login获取的code得到session key")
    @ApiImplicitParam(name = "code", paramType = "code", dataType = "String", required = true)
    @GetMapping("getSessionKey")
    @ApiResponses({@ApiResponse(code = 200,message = "成功",response = SessionKeyVO.class)})
    public ReturnDTO<SessionKeyVO> getSessionKeyWx(@RequestParam("code") String code) {
        return userService.getSessionKeyWx(code);
    }


    @ApiOperation(value = "获取用户信息", notes = "根据微信openId获取用户信息")
    @ApiImplicitParam(name = "openId", paramType = "String", dataType = "String", required = true)
    @GetMapping("getByOpenId")
    @ApiResponses({@ApiResponse(code = 200,message = "成功",response = User.class)})
    public ReturnDTO<User> getUserByOpenId(String openId){
        User user = userService.getUserByOpenId(openId);
        return new ReturnDTO(user);
    }

    @ApiOperation(value = "添加新用户", notes = "将微信获取的用户信息添加到系统")
    @ApiImplicitParam(name = "user", paramType = "Object", dataType = "User", required = true)
    @PostMapping("add")
    @ApiResponses({@ApiResponse(code = 200,message = "成功",response = Boolean.class)})
    public ReturnDTO<Boolean> addUser(@RequestParam("user") User user) {
        boolean result = userService.addUser(user);
        if (result) {
            return ReturnDTOUtil.fail();
        }
        ReturnDTO<Boolean> returnDTO = new ReturnDTO();
        returnDTO.setMessage(true);
        return returnDTO;
    }
}
