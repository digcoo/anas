package com.slife.api.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.Shop;
import com.slife.service.FollowService;
import com.slife.util.ReturnDTOUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cq on 18-1-28.
 */
@RestController
@Api("关注")
@RequestMapping("/api/follow")
public class FollowController {

    @Resource
    private FollowService followService;

    @ApiOperation(value = "关注", notes = "用户关注商户接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", paramType = "query", dataType = "long", required = true),
            @ApiImplicitParam(name = "shopId", paramType = "query", dataType = "long", required = true)})
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    @GetMapping("link")
    public ReturnDTO<Boolean> link(@RequestParam("userId") long userId, @RequestParam("shopId") long shopId) {
        boolean follow = followService.link(userId, shopId);
        return follow ? ReturnDTOUtil.success(follow) : ReturnDTOUtil.fail();
    }

    @ApiOperation(value = "取消关注", notes = "用户取消关注商户接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", paramType = "query", dataType = "long", required = true),
            @ApiImplicitParam(name = "shopId", paramType = "query", dataType = "long", required = true)})
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    @GetMapping("unlink")
    public ReturnDTO<Boolean> unlink(@RequestParam("userId") long userId, @RequestParam("shopId") long shopId) {
        boolean follow = followService.unlink(userId, shopId);
        return follow ? ReturnDTOUtil.success(follow) : ReturnDTOUtil.fail();
    }

    @ApiOperation(value = "关注", notes = "用户关注商户接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", paramType = "query", dataType = "long", required = true)})
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    @GetMapping("list")
    public ReturnDTO<List<Shop>> shopList(@RequestParam("userId") long userId) {
        List<Shop> shops = followService.getShopListByUserId(userId);
        return ReturnDTOUtil.success(shops);
    }


}
