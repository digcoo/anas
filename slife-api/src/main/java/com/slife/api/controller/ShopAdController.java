package com.slife.api.controller;

import com.alibaba.fastjson.JSON;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.vo.DataTable;
import com.slife.entity.Shop;
import com.slife.entity.ShopAd;
import com.slife.service.IShopAdService;
import com.slife.service.IShopService;
import com.slife.util.DateUtils;
import com.slife.util.ReturnDTOUtil;
import com.slife.vo.IndexVO;
import com.slife.vo.Item;
import io.swagger.annotations.*;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant商家controller
 */
@Api(value = "用户非个人中心接口",tags = {"用户非个人中心接口"},description = "首页、收藏取消活动、搜索、商家主页、关注／取消关注商家、举报商家、关注的商家列表", protocols = "http")
@Controller
@RequestMapping("/api")
public class ShopAdController extends BaseController {

    @Autowired
    private IShopAdService shopAdService;
    @Autowired
    private IShopService shopService;

    @ApiOperation(value = "T1-首页-获取附近的活动列表", notes = "根据geohash编码获取活动列表数据")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条", defaultValue = "0",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "geohash", value = "geo编码",required = true) })
    @ApiResponses({@ApiResponse(code = 200,message = "成功",response = IndexVO.class)})
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
                        indexVO.setType(shopAd.getType());
                        indexVO.setAdName(shopAd.getTitle());
                        indexVO.setItems(JSON.parseArray(shopAd.getItems(),Item.class));
                        indexVO.setTimeDesc(formatTime(shopAd.getPublishTime()));
                        indexVO.setType(shopAd.getType());
                        indexVO.setLat(shop.getLat());
                        indexVO.setLng(shop.getLng());
                        return indexVO;
                    }
            ).collect(Collectors.toList());
            return ReturnDTOUtil.success(indexVOList);
        }
    }

    private String formatTime(Date date){
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.set(Calendar.DATE,-1);
        boolean yesterday = org.apache.commons.lang3.time.DateUtils.isSameDay(calendar.getTime(),date);
        boolean today = org.apache.commons.lang3.time.DateUtils.isSameDay(calendar2.getTime(),date);
        String timeStr = DateUtils.formatDate(date,"HH:mm");
        String dateStr = DateUtils.formatDate(date,"MM-DD");
        if(yesterday){
            return "昨天 "+timeStr;
        }else if(today){
            long difference=System.currentTimeMillis()-date.getTime();
            long minute=difference/(60*1000);
            if(minute<20){
                return minute+"前";
            }
            return timeStr;
        }else{
            return dateStr;
        }
    }
}
