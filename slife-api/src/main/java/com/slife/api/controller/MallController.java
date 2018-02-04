package com.slife.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.Mall;
import com.slife.service.impl.MallService;
import com.slife.util.ReturnDTOUtil;
import com.slife.util.StringUtils;
import com.slife.vo.MallVO;

@Api(description = "商业中心模块接口(模糊匹配查询)")
@RestController
@RequestMapping("/api/mall")
public class MallController {

    @Autowired
    private MallService mallService;

    @ApiOperation(value = "D-4 商业中心模糊查询接口", notes = "商业中心模糊查询接口")
    @GetMapping("/search")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = MallVO.class)})
    public ReturnDTO<List<MallVO>> index(@RequestParam("key") String key) {
        if (StringUtils.isEmpty(key)) {
            ReturnDTOUtil.paramError();
        }
        
    	List<MallVO> retList = mallService.findListBykey(key);
    	return ReturnDTOUtil.success(retList);
    }

    @ApiOperation(value = "D-12 商业中心列表查询接口", notes = "商业中心列表查询接口")
    @GetMapping("/list")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = MallVO.class)})
    public ReturnDTO<List<Mall>> list() {
    	List<Mall> retList = mallService.selectAll();
    	return ReturnDTOUtil.success(retList);
    }
    
}
