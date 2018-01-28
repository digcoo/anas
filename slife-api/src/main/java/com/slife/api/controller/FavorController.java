package com.slife.api.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.ShopAd;
import com.slife.service.FavorService;
import com.slife.util.ReturnDTOUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cq on 18-1-28.
 */
@RestController
@Api("收藏")
public class FavorController {

    @Resource
    private FavorService favorService;
    
    @ApiOperation(value = "收藏接口", notes = "用户收藏")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", paramType = "query", dataType = "long", required = true),
            @ApiImplicitParam(name = "adId", paramType = "query", dataType = "long", required = true)})
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    @GetMapping("link")
    public ReturnDTO<Boolean> link(@RequestParam("userId") long userId, @RequestParam("adId") long adId) {
        boolean follow = favorService.link(userId, adId);
        return follow ? ReturnDTOUtil.success(follow) : ReturnDTOUtil.fail();
    }

    @ApiOperation(value = "取消关注", notes = "用户取消收藏")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", paramType = "query", dataType = "long", required = true),
            @ApiImplicitParam(name = "adId", paramType = "query", dataType = "long", required = true)})
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    @GetMapping("unlink")
    public ReturnDTO<Boolean> unlink(@RequestParam("userId") long userId, @RequestParam("adId") long adId) {
        boolean follow = favorService.unlink(userId, adId);
        return follow ? ReturnDTOUtil.success(follow) : ReturnDTOUtil.fail();
    }

    @ApiOperation(value = "关注", notes = "用户关注商户接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", paramType = "query", dataType = "long", required = true)})
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    @GetMapping("list")
    public ReturnDTO<List<ShopAd>> shopAdList(@RequestParam("userId") long userId) {
        List<ShopAd> shops = favorService.getShopAdsByUserId(userId);
        return ReturnDTOUtil.success(shops);
    }
}
