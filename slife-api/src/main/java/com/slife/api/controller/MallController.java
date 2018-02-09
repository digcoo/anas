package com.slife.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.Mall;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.impl.MallService;
import com.slife.util.ReturnDTOUtil;
import com.slife.util.StringUtils;
import com.slife.vo.MallVO;

@Api(description = "商业中心数据接口")
@RestController
@RequestMapping("/api/mall")
public class MallController {

    @Autowired
    private MallService mallService;
//
//    @ApiOperation(value = "D-4 商业中心模糊查询接口", notes = "商业中心模糊查询接口")
//    @GetMapping("/search")
//    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = MallVO.class)})
//    public ReturnDTO<List<MallVO>> index(@RequestParam("key") String key) {
//        if (StringUtils.isEmpty(key)) {
//            ReturnDTOUtil.paramError();
//        }
//        
//    	List<MallVO> retList = mallService.findListBykey(key);
//    	return ReturnDTOUtil.success(retList);
//    }

    @ApiOperation(value = "D-12 商业中心列表查询接口", notes = "商业中心列表查询接口")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "geohash", value = "geohash",required = false)}
        )
    @GetMapping("/list")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = MallVO.class)})
    public ReturnDTO<List<Mall>> list(@RequestParam(value="geohash", required = false) String geohash) {
    	if (StringUtils.isEmpty(geohash)) {
    		return ReturnDTOUtil.success(mallService.selectAll());
		}else{
			if(geohash.length() < 4) {
	            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
	        }
			geohash = geohash.substring(0, 4);
			List<Mall> malls = mallService.selectMallsByGeohash(geohash);
			if(malls != null && malls.size() > 0){
				List<MallVO> vos = new ArrayList<MallVO>(malls.size());
				for (Mall mall : malls) {
					vos.add(new MallVO(mall.getId(), mall.getName()));
				}
				return ReturnDTOUtil.success(vos);
			}
			
			return ReturnDTOUtil.custom(HttpCodeEnum.NO_DATA);
		}
    }
    
}
