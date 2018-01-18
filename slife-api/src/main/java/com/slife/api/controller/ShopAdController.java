package com.slife.api.controller;

import com.alibaba.fastjson.JSON;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.vo.DataTable;
import com.slife.entity.Shop;
import com.slife.entity.ShopAd;
import com.slife.service.IShopAdService;
import com.slife.service.IShopService;
import com.slife.util.ReturnDTOUtil;
import com.slife.vo.IndexVO;
import com.slife.vo.Item;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant商家controller
 */
@Controller
@RequestMapping("/api")
public class ShopAdController extends BaseController {

    @Autowired
    private IShopAdService shopAdService;
    @Autowired
    private IShopService shopService;

    @ApiOperation(value = "获取附近的活动列表", notes = "根据geohash编码获取活动列表数据")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条", defaultValue = "0",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "geohash", value = "geo编码",required = true) })
    @GetMapping(value = "/ads")
    @ResponseBody
    public ReturnDTO getAds(@RequestParam("index") Integer index,@RequestParam("geohash") String geohash, ServletRequest request) {
        List<ShopAd> shopAdList = shopAdService.selectAdsByGeohash(index==null?0:index,geohash);
        if(CollectionUtils.isEmpty(shopAdList)){
            return ReturnDTOUtil.success();
        }else{
            List<Long> shopIdList = shopAdList.stream().map(shopAd -> shopAd.getShopId()).collect(Collectors.toList());
            List<Shop> shopList = shopService.selectBatchIds(shopIdList);
            Map<Long, Shop> shopMap = shopList.stream().collect(Collectors.toMap(Shop::getId,Shop->Shop));
            List<IndexVO>  indexVOList = shopAdList.stream().map(
                    shopAd -> {
                        Shop shop = shopMap.get(shopAd.getShopId());
                        if (shop == null){
                            return null;
                        }
                        IndexVO indexVO = new IndexVO();
                        indexVO.setShopId(shopAd.getShopId());
                        indexVO.setAddr(shop.getAddr());
                        indexVO.setFollowNum(shop.getFollowNum());
                        indexVO.setName(shop.getName());
                        indexVO.setLogo(shop.getLogo());
                        indexVO.setItems(JSON.parseArray(shopAd.getItems(),Item.class));
                        return indexVO;
                    }
            ).collect(Collectors.toList());
            return ReturnDTOUtil.success(indexVOList);
        }
    }
}
