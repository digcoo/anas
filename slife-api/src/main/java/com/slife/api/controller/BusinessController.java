package com.slife.api.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.Business;
import com.slife.entity.enums.AdType;
import com.slife.service.IBusinessService;
import com.slife.util.ReturnDTOUtil;
import com.slife.vo.AdTypeVO;
import com.slife.vo.BusinessVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/business")
@Api(description = "行业信息和广告类型")
public class BusinessController {

    @Autowired
    private IBusinessService businessService;

    @ApiOperation(value = "30-行业信息", notes = "30-获取全行业信息",httpMethod = "GET")
    @GetMapping(value = "/businessTree")
    @ResponseBody
    public ReturnDTO<List<BusinessVO>> getBusinessTree(HttpServletRequest request) {


        List<Business> businessList = businessService.selectAll();

        List<BusinessVO> result = genBusinessTree(businessList);

        return ReturnDTOUtil.success(result);
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


    private List<BusinessVO>  genBusinessTree(List<Business> businessList){

        Map<Long,BusinessVO>  parentMap = new HashMap<Long,BusinessVO>();
        List<BusinessVO> children =  new ArrayList<BusinessVO>();
        for(Business business : businessList){
            if(business.getLayer() == 1){
                parentMap.put(business.getId(),bulidBusinessVO(business));
            }else{
                children.add(bulidBusinessVO(business));
            }
        }
        for(BusinessVO child :children){
            BusinessVO  parent =   parentMap.get(child.getParentId());
            if(parent != null ){
                parent.addChild(child);
            }
        }
        List<BusinessVO> list = new ArrayList<BusinessVO>();
        list.addAll(parentMap.values());
        return list;

    }

    private  BusinessVO bulidBusinessVO(Business business){
        BusinessVO businessVO = new BusinessVO();
        businessVO.setId(business.getId());
        businessVO.setName(business.getName());
        businessVO.setParentId(business.getParentId());
        return businessVO;
    }


}
