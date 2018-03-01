package com.slife.api.controller;

import com.slife.wxapi.response.SessionKeyWX;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.User;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.UserService;
import com.slife.util.ReturnDTOUtil;
import com.slife.utils.RedisKey;
import com.slife.utils.SlifeRedisTemplate;
import com.slife.vo.SessionKeyVO;
import com.slife.vo.UserAddVO;
import com.slife.vo.UserVO;

/**
 * Created by cq on 18-1-19.
 */
@RequestMapping("/api/user")
@RestController
@Api(description = "用户操作相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SlifeRedisTemplate slifeRedisTemplate;

    @ApiOperation(value = "获取微信SessionKey", notes = "根据wx.login获取的code得到session key")
    @ApiImplicitParam(name = "code", paramType = "query", dataType = "String", required = true)
    @GetMapping("/session_key")
    public ReturnDTO getSessionKeyWx(@RequestParam("code") String code) {
        SessionKeyWX sessionKeyWX = userService.getSessionKeyWx(code);
        if (StringUtils.isEmpty(sessionKeyWX.getErrcode())) {
            String digcooSessionKey = slifeRedisTemplate.setDigcooSessionKey(sessionKeyWX.getSessionKey(),sessionKeyWX.getOpenId());
            SessionKeyVO sessionKeyVO = new SessionKeyVO();
            sessionKeyVO.setDigcooSessionKey(digcooSessionKey);
            User user = userService.getUserByOpenId(sessionKeyWX.getOpenId());
            UserVO userVO = new UserVO();
            userVO.setUserId(user.getId());
            userVO.setType(user.getType());
            userVO.setMobile(user.getMobile());
            userVO.setNick(user.getNick());
            userVO.setHeadImg(user.getHeadImg());
            sessionKeyVO.setUser(userVO);
			return ReturnDTOUtil.success(sessionKeyVO);
		}else{
			return ReturnDTOUtil.custom(Integer.parseInt(sessionKeyWX.getErrcode()), sessionKeyWX.getErrmsg());
		}
    }

    @ApiOperation(value = "添加新用户", notes = "将微信获取的用户信息添加到系统")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", value = "digcoo session key",required = true)
    })
    @PostMapping("/add")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public ReturnDTO addUser(
    		@RequestParam(value = "digcoo_session_key",required = true) String digcooSessionKey,
    		@RequestBody UserAddVO user) throws SlifeException{
    	String sessionKeyAndOpenId = slifeRedisTemplate.getDigcooSessionKey(digcooSessionKey);
        //session 过期
        if(StringUtils.isBlank(sessionKeyAndOpenId)){
            throw new SlifeException(HttpCodeEnum.USER_SESSION_EXPIRED);
        }else{
            String[] sessionKeyAndOpenIdArray =sessionKeyAndOpenId.split(RedisKey.DIGCOO_SESSION_KEY_DELIMITER);
            User localUser = userService.getUserByOpenId(sessionKeyAndOpenIdArray[0]);
            if(localUser != null){
                return ReturnDTOUtil.success(localUser);
            }else{
                User newUser = new User();
                newUser.setCity(user.getCity());
                newUser.setCountry(user.getCountry());
                newUser.setGender(user.getGender());
                newUser.setHeadImg(user.getHeadImg());
                newUser.setMobile(user.getMobile());
                newUser.setNick(user.getNick());
                newUser.setProvince(user.getProvince());
                newUser.setOpenId(sessionKeyAndOpenIdArray[0]);
                return userService.addUser(newUser) == 1?ReturnDTOUtil.success(newUser):ReturnDTOUtil.fail();
            }

        }
    }

    @ApiOperation(value = "获取用户信息", notes = "根据digcoo_session_key获取用户信息")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", value = "digcoo session key",required = true)
    })
    @GetMapping("/getUser")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = User.class)})
    public ReturnDTO<User> getUserByDigcooSessionKey(@RequestParam(value = "digcoo_session_key",required = true) String digcooSessionKey) {
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
            
            UserVO userVO = new UserVO();
            userVO.setUserId(user.getId());
            userVO.setType(user.getType());
            userVO.setMobile(user.getMobile());
            userVO.setNick(user.getNick());
            userVO.setHeadImg(user.getHeadImg());
            
            return ReturnDTOUtil.success(userVO);
        }
    }

    @ApiOperation(value = "修改昵称", notes = "用户重新修改昵称，默认昵称使用微信昵称")
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "nick", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", required = true)
            })
    @PostMapping("/nick/edit")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public ReturnDTO editNick(@RequestParam("nick") String nick,
    		@RequestParam(value="digcoo_session_key", required = true)String digcooSessionKey
    		) throws SlifeException{
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
            return userService.editNick(user.getId(), nick)==1?ReturnDTOUtil.success():ReturnDTOUtil.fail();
        }
    }

    @ApiOperation(value = "编辑头像", notes = "用户重新上传头像，默认使用微信头像")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "headImg", paramType = "form", dataType = "String", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", value = "digcoo session key",required = true)
    })
    @PostMapping("/head_img/edit")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public ReturnDTO editHeadImg(
    		@RequestParam("headImg") String headImg,
    		@RequestParam(value="digcoo_session_key", required = true)String digcooSessionKey
    		) throws SlifeException{
    	
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
            return userService.editHeadImg(user.getId(), headImg)==1?ReturnDTOUtil.success():ReturnDTOUtil.fail();
        }
    	
    }

//    @ApiOperation(value = "获取登录token", notes = "根据wx.login获取的code得到token")
//    @ApiImplicitParam(name = "code", paramType = "query", dataType = "String", required = true)
//    @GetMapping("/ticket")
//    public ReturnDTO ticket(@RequestParam("code") String code){
//        AnasTicketVO ticketVO = userService.ticket(code);
//        return ReturnDTOUtil.success(ticketVO);
//    }

}
