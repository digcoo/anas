package com.slife.api.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.User;
import com.slife.service.UserService;
import com.slife.util.ReturnDTOUtil;
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
    @ApiImplicitParam(name = "code", paramType = "query", dataType = "String", required = true)
    @GetMapping("getSessionKey")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = SessionKeyVO.class)})
    public ReturnDTO<SessionKeyVO> getSessionKeyWx(@RequestParam("code") String code) {
        return userService.getSessionKeyWx(code);
    }


    @ApiOperation(value = "获取用户信息", notes = "根据微信openId获取用户信息")
    @ApiImplicitParam(name = "openId", paramType = "query", dataType = "String", required = true)
    @GetMapping("getByOpenId")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = User.class)})
    public ReturnDTO<User> getUserByOpenId(String openId) {
        User user = userService.getUserByOpenId(openId);
        return new ReturnDTO(user);
    }

    @ApiOperation(value = "添加新用户", notes = "将微信获取的用户信息添加到系统")
    @ApiImplicitParam(name = "user", paramType = "form", dataType = "User", required = true)
    @PostMapping("add")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = Boolean.class)})
    public ReturnDTO<Boolean> addUser(@RequestParam("user") User user) {
        boolean result = userService.addUser(user);
        if (result) {
            return ReturnDTOUtil.fail();
        }
        ReturnDTO<Boolean> returnDTO = new ReturnDTO();
        returnDTO.setMessage(true);
        return returnDTO;
    }

    @ApiOperation(value = "更新用户信息", notes = "当用户进入个人资料页，根据updateTime判断，更新用户微信账号信息")
    @ApiImplicitParam(name = "user", paramType = "form", dataType = "User", required = true)
    @PostMapping("edit")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = Boolean.class)})
    public ReturnDTO<Boolean> editUser(@RequestParam("user") User user) {
        return userService.editUser(user);
    }

    @ApiOperation(value = "修改昵称", notes = "用户重新修改昵称，默认昵称使用微信昵称")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", paramType = "form", dataType = "long", required = true),
            @ApiImplicitParam(name = "nick", paramType = "form", dataType = "String", required = true)})
    @PostMapping("editNick")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = Boolean.class)})
    public ReturnDTO<Boolean> editNick(@RequestParam("id") long id, @RequestParam("nick") String nick) {
        return userService.editNick(id ,nick);
    }

    @ApiOperation(value = "上传头像", notes = "用户重新上传头像，默认使用微信头像")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", paramType = "form", dataType = "long", required = true),
            @ApiImplicitParam(name = "headImg", paramType = "form", dataType = "String", required = true)})
    @PostMapping("editHeadImg")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = Boolean.class)})
    public ReturnDTO<Boolean> editHeadImg(@RequestParam("id") long id, @RequestParam("headImg") String headImg) {
        return userService.editHeadImg(id ,headImg);
    }

}
