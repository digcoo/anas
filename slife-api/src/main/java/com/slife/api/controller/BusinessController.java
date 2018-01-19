package com.slife.api.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.enums.AdType;
import com.slife.util.ReturnDTOUtil;
import com.slife.vo.AdTypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/business")
@Api(description = "行业信息和广告类型")
public class BusinessController {

    @ApiOperation(value = "行业信息", notes = "获取全行业信息",httpMethod = "GET")
    @GetMapping(value = "/businessTree")
    @ResponseBody
    public String genBusinessTree(HttpServletRequest request) {
        return "";
    }

    @ApiOperation(value = "广告类型", notes = "获取广告类型",httpMethod = "GET",produces = "application/json" )
    @GetMapping(value = "/adTypes")
    @ResponseBody
    public ReturnDTO<List<AdTypeVO>> getAdTypes(HttpServletRequest request) {
        List<AdTypeVO> result = new ArrayList<AdTypeVO>();

        for(AdType adType :AdType.values()){
            AdTypeVO adTypeVO = new AdTypeVO();
            adTypeVO.setType(adType.getType());
            adTypeVO.setDesc(adType.getDesc());
            result.add(adTypeVO);
        }
        return ReturnDTOUtil.success(result);
    }
}
