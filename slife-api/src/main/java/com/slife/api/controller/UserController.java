package com.slife.api.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.User;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.UserService;
import com.slife.util.ReturnDTOUtil;
import com.slife.vo.SessionKeyVO;

import io.swagger.annotations.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by cq on 18-1-19.
 */
@RequestMapping("/api/user")
@RestController
@Api(description = "获取用户信息相关接口")
public class UserController {
    @Autowired
    private UserService userService;


    @ApiOperation(value = "获取微信SessionKey", notes = "根据wx.login获取的code得到session key")
    @ApiImplicitParam(name = "code", paramType = "query", dataType = "String", required = true)
    @GetMapping("/session_key")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = SessionKeyVO.class)})
    public ReturnDTO<SessionKeyVO> getSessionKeyWx(@RequestParam("code") String code) {
        SessionKeyVO sessionKeyVO = userService.getSessionKeyWx(code);
        if (StringUtils.isEmpty(sessionKeyVO.getErrCode())) {
			return ReturnDTOUtil.success(sessionKeyVO);
		}else{
			return ReturnDTOUtil.custom(Integer.parseInt(sessionKeyVO.getErrCode()), sessionKeyVO.getErrMsg());
		}
    }


    @ApiOperation(value = "获取用户信息", notes = "根据微信openId获取用户信息")
    @ApiImplicitParam(name = "openId", paramType = "query", dataType = "String", required = true)
    @GetMapping("/open_id")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = User.class)})
    public ReturnDTO<User> getUserByOpenId(String openId) {
        User user = userService.getUserByOpenId(openId);
        return ReturnDTOUtil.success(user);
    }

    @ApiOperation(value = "添加新用户", notes = "将微信获取的用户信息添加到系统")
    @PostMapping("/add")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public ReturnDTO addUser(@RequestBody User user) throws SlifeException{
        return userService.addUser(user)==1?ReturnDTOUtil.success():ReturnDTOUtil.fail();
    }

//    @ApiOperation(value = "更新用户信息", notes = "当用户进入个人资料页，根据updateTime判断，更新用户微信账号信息")
//    @PostMapping("/edit")
//    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
//    public ReturnDTO editUser(@RequestBody User user)  throws SlifeException{
//        return userService.editUser(user)==1?ReturnDTOUtil.success():ReturnDTOUtil.fail();
//    }

    @ApiOperation(value = "修改昵称", notes = "用户重新修改昵称，默认昵称使用微信昵称")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", paramType = "query", dataType = "long", required = true),
            @ApiImplicitParam(name = "nick", paramType = "query", dataType = "String", required = true)})
    @PostMapping("/nick/edit")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public ReturnDTO editNick(@RequestParam("id") String id, @RequestParam("nick") String nick) throws SlifeException{
        return userService.editNick(Long.parseLong(id), nick)==1?ReturnDTOUtil.success():ReturnDTOUtil.fail();
    }

    @ApiOperation(value = "编辑头像", notes = "用户重新上传头像，默认使用微信头像")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", paramType = "query", dataType = "long", required = true),
            @ApiImplicitParam(name = "headImg", paramType = "form", dataType = "String", required = true)})
    @PostMapping("/head_img/edit")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public ReturnDTO editHeadImg(@RequestParam("id") String id, @RequestParam("headImg") String headImg) throws SlifeException{
        return userService.editHeadImg(Long.parseLong(id), headImg)==1?ReturnDTOUtil.success():ReturnDTOUtil.fail();
    }

}
