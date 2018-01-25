package com.slife.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.slife.base.entity.ReturnDTO;
import com.slife.service.IItemPhotoService;
import com.slife.util.ReturnDTOUtil;
import com.slife.util.StringUtils;
import com.slife.vo.ItemPhotoVO;

@Api(description = "图片库模块功能接口(主页、更多、搜索)")
@RestController
@RequestMapping("/api/photo")
public class ItemPhotoController {

    @Autowired
    private IItemPhotoService itemPhotoService;

    @ApiOperation(value = "D-1 图片库首页接口", notes = "图片库首页接口")
    @GetMapping("/index")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = ItemPhotoVO.class)})
    public ReturnDTO<Map<Integer, List<ItemPhotoVO>>> index() {
    	Map<Integer, List<ItemPhotoVO>> ret = itemPhotoService.findIndexs();
    	return ReturnDTOUtil.success(ret);
    }
    
    @ApiOperation(value = "D-2 图片库首页更多接口", notes = "图片库首页更多接口")
    @GetMapping("/more")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = ItemPhotoVO.class)})
    public ReturnDTO more(@RequestParam("index") Integer index, @RequestParam("category") Integer category) {
        if (index == null || category == null) {
            ReturnDTOUtil.paramError();
        }
        List<ItemPhotoVO> retList = itemPhotoService.findPageByCategory(index, category);
        return ReturnDTOUtil.success(retList);
    }
    
    @ApiOperation(value = "D-3 图片库搜索接口", notes = "图片库搜索接口")
    @GetMapping("/search")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = ItemPhotoVO.class)})
    public ReturnDTO search(@RequestParam("index") Integer index, @RequestParam("key") String key) {
        if (index == null || StringUtils.isEmpty(key)) {
            ReturnDTOUtil.paramError();
        }
        List<ItemPhotoVO> retList = itemPhotoService.search(index, key);
        return ReturnDTOUtil.success(retList);
    
    }
}
