package com.slife.api.controller;

import com.alibaba.fastjson.JSON;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.entity.Mall;
import com.slife.entity.Shop;
import com.slife.entity.ShopAd;
import com.slife.entity.ShopCountPerMallView;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.IMallService;
import com.slife.service.IShopAdService;
import com.slife.service.IShopService;
import com.slife.util.DateUtils;
import com.slife.util.ReturnDTOUtil;
import com.slife.util.StringUtils;
import com.slife.utils.RedisKey;
import com.slife.utils.SlifeRedisTemplate;
import com.slife.vo.*;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.io.GeohashUtils;
import com.spatial4j.core.shape.Point;
import io.swagger.annotations.*;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
@Api(description = "首页、收藏取消活动、搜索、商家主页、关注／取消关注商家、举报商家、关注的商家列表", protocols = "http")
@Controller
@RequestMapping("/api")
public class ShopAdController extends BaseController {

    @Autowired
    private IShopAdService shopAdService;
    @Autowired
    private IShopService shopService;
    @Autowired
    private IMallService mallService;
    @Autowired
    private SlifeRedisTemplate slifeRedisTemplate;

    private final static SpatialContext geo = SpatialContext.GEO;
    /**
     * NOTE：后台管理时删除或冻结店铺需要同步更新该商家的所有活动
     * @param index
     * @param geohash
     * @return
     */
    @ApiOperation(value = "T1-首页-获取附近的活动列表", notes = "根据geohash编码获取活动列表数据")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条", defaultValue = "0",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "geohash", value = "geo编码",required = true) })
    @ApiResponses({@ApiResponse(code = 200,message = "成功",response = IndexAdVO.class)})
    @GetMapping(value = "/ads")
    @ResponseBody
    public ReturnDTO<IndexVO> getAds(@RequestParam("index") Integer index, @RequestParam("geohash") String geohash) {
        if(StringUtils.isBlank(geohash) || geohash.length()<=5) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        IndexVO indexVO = new IndexVO();
        List<Mall> mallList = mallService.selectMallsByGeohash(geohash.substring(0,5));
        if(!CollectionUtils.isEmpty(mallList)) {
            //List<Long> mallIdList = mallList.stream().map(Mall::getId).collect(Collectors.toList());
            //Map<Long, Integer> shopNumMap = shopService.countShopByMallId(mallIdList).stream().collect(Collectors.toMap(ShopCountPerMallView::getMallId, ShopCountPerMallView::getNums));
            List<IndexMallVO> indexMallVOList = mallList.stream().map(mall -> {
                Point agentPoint = GeohashUtils.decode(geohash,geo);
                double distance1 = geo.calcDistance(geo.makePoint(mall.getLng(), mall.getLat()), agentPoint) * DistanceUtils.DEG_TO_KM;
                String distanceDesc;
                if(distance1<1){
                    int smallDistance = (int)(distance1*1000);
                    distanceDesc = String.valueOf(smallDistance<=100?"100m以内":smallDistance+"m");
                }else{
                    BigDecimal bigDecimal = new BigDecimal(distance1);
                    distanceDesc =  String.valueOf(bigDecimal.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue()+"km");
                }
                IndexMallVO indexMallVO = new IndexMallVO();
                indexMallVO.setMallId(mall.getId());
                indexMallVO.setTotal(mall.getTotal());
                indexMallVO.setHotShop(mall.getHotShop());
                indexMallVO.setName(mall.getName());
                indexMallVO.setLogo(mall.getLogo());
                indexMallVO.setLat(mall.getLat());
                indexMallVO.setLng(mall.getLng());
                indexMallVO.setDistance(distance1);
                indexMallVO.setDistanceDesc(distanceDesc);
                return indexMallVO;
            }).collect(Collectors.toList());
            indexVO.setMalls(indexMallVOList);
        }

        List<ShopAd> shopAdList = shopAdService.selectAdsByGeohash(index==null?0:index,geohash);
        if(!CollectionUtils.isEmpty(shopAdList)){
            List<Long> shopIdList = shopAdList.stream().map(ShopAd::getShopId).distinct().collect(Collectors.toList());
            List<Shop> shopList = shopService.selectBatchIds(shopIdList);
            Map<Long, Shop> shopMap = shopList.stream().filter(shop -> shop.getStatus()==3).collect(Collectors.toMap(Shop::getId,shop->shop));
            List<IndexAdVO> indexAdVOList = shopAdList.stream().map(shopAd -> shopBeanMapper(shopMap,shopAd,geohash)).filter(Objects::nonNull).collect(Collectors.toList());
            indexVO.setAds(indexAdVOList);
        }
        return ReturnDTOUtil.success(indexVO);
    }

    @ApiOperation(value = "T2/T3-收藏／取消收藏活动", notes = "根据userId和adId保存或删除相应redis")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "用户id",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "adId", value = "活动id",required = true) })
    @GetMapping(value = "/ads/favor")
    @ResponseBody
    public ReturnDTO favor(@RequestParam("userId") Long userId,@RequestParam("adId") Long adId) {
        if(userId == null || adId==null) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        slifeRedisTemplate.collectOrNot(userId,adId);
        return ReturnDTOUtil.success();
    }


    @ApiOperation(value = "T5-活动搜索", notes = "根据geohash编码以及活动名称模糊搜索活动列表数据")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条", defaultValue = "0",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "geohash", value = "geo编码",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "name", value = "活动名称模糊搜索",required = false) })
    @ApiResponses({@ApiResponse(code = 200,message = "成功",response = IndexAdVO.class)})
    @GetMapping(value = "/ads/q")
    @ResponseBody
    public ReturnDTO searchAds(@RequestParam("index") Integer index,@RequestParam("geohash") String geohash,@RequestParam(value = "name",required=false) String name ) {
        if(StringUtils.isBlank(geohash) || geohash.length()<=5) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        List<ShopAd> shopAdList = shopAdService.selectAdsByGeohashAndName(geohash.substring(0,5),name);
        if(CollectionUtils.isEmpty(shopAdList)){
            return ReturnDTOUtil.success(shopAdList);
        }else{
            List<Long> shopIdList = shopAdList.stream().map(ShopAd::getShopId).distinct().collect(Collectors.toList());
            List<Shop> shopList = shopService.selectBatchIds(shopIdList);
            Map<Long, Shop> shopMap = shopList.stream().filter(shop -> shop.getStatus()==3).collect(Collectors.toMap(Shop::getId,Shop->Shop));
            List<IndexAdVO> indexAdVOList = shopAdList.stream().map(shopAd -> shopBeanMapper(shopMap,shopAd,geohash)).filter(Objects::nonNull).sorted(Comparator.comparing(IndexAdVO::getDistance)).skip(index).limit(10).collect(Collectors.toList());
            return ReturnDTOUtil.success(indexAdVOList);
        }
    }


    @ApiOperation(value = "T6/T7-用户端看到的商家主页", notes = "根据shopId获取商家以及有效的活动信息")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条", defaultValue = "0",required = true),
            @ApiImplicitParam(paramType = "path", dataType = "Long", name = "shopId", value = "商家id",required = true) })
    @ApiResponses({@ApiResponse(code = 200,message = "成功",response = ShopHomeVO.class)})
    @GetMapping(value = "/shop/{shopId}")
    @ResponseBody
    public ReturnDTO shopHome(@RequestParam("index") Integer index,@PathVariable Long shopId) {
        Shop shop = shopService.selectById(shopId);
        if(shop == null){
            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_NOT_EXISTS);
        }else{
            ShopHomeVO shopHomeVO = new ShopHomeVO();
            shopHomeVO.setShopId(shopId);
            shopHomeVO.setAddr(shop.getAddr());
            shopHomeVO.setFollowNum(shop.getFollowNum());
            shopHomeVO.setName(shop.getName());
            shopHomeVO.setLogo(shop.getLogo());
            List<ShopAd> shopAdList = shopAdService.selectAdsByShopId(index==null?0:index,shopId);
            List<Ad>  adList = shopAdList.stream().map(
                    shopAd -> {
                        Ad ad = new Ad();
                        ad.setType(shopAd.getType());
                        ad.setAdName(shopAd.getTitle());
                        ad.setItems(JSON.parseArray(shopAd.getItems(),Item.class));
                        ad.setTimeDesc(formatTime(shopAd.getPublishTime()));
                        ad.setLat(shop.getLat());
                        ad.setLng(shop.getLng());
                        return ad;
                    }
            ).collect(Collectors.toList());
            shopHomeVO.setAds(adList);
            return ReturnDTOUtil.success(shopHomeVO);
        }
    }

    @ApiOperation(value = "T8-关注／取消收关注商家", notes = "根据userId和shopId保存或删除相应redis")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "用户id",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "shopId", value = "店铺id",required = true) })
    @GetMapping(value = "/shop/follow")
    @ResponseBody
    public ReturnDTO follow(@RequestParam("userId") Long userId,@RequestParam("shopId") Long shopId) {
        if(userId == null || shopId==null) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        slifeRedisTemplate.followOrNot(userId,shopId);
        return ReturnDTOUtil.success();
    }

    @ApiOperation(value = "T10-关注商家列表", notes = "根据userId获取相应关注商家列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条", defaultValue = "0",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "用户id",required = true)
            })
    @GetMapping(value = "/shop/follow/shops")
    @ResponseBody
    public ReturnDTO followShops(@RequestParam("index") Integer index,@RequestParam("userId") Long userId) {
        if(userId == null) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        Set<String> shopIdList = slifeRedisTemplate.getFollowShopIdsWithPage(userId,index);
        List<Shop> shopList = shopService.selectBatchIds(shopIdList.stream().map(s -> Long.parseLong(s)).collect(Collectors.toList()));
        return ReturnDTOUtil.success(shopList.stream().filter(shop -> shop.getStatus()==1).collect(Collectors.toList()));
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

    private IndexAdVO shopBeanMapper(Map<Long, Shop> shopMap,ShopAd shopAd,String geohash){
        Shop shop = shopMap.get(shopAd.getShopId());
        if (shop == null){
            return null;
        }
        Point agentPoint = GeohashUtils.decode(geohash,geo);
        double distance1 = geo.calcDistance(geo.makePoint(shop.getLng(), shop.getLat()), agentPoint) * DistanceUtils.DEG_TO_KM;
        String distanceDesc;
        if(distance1<1){
            int smallDistance = (int)(distance1*1000);
            distanceDesc = String.valueOf(smallDistance<=100?"100m以内":smallDistance+"m");
        }else{
            BigDecimal bigDecimal = new BigDecimal(distance1);
            distanceDesc =  String.valueOf(bigDecimal.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue()+"km");
        }
        IndexAdVO indexAdVO = new IndexAdVO();
        indexAdVO.setShopId(shopAd.getShopId());
        indexAdVO.setAddr(shop.getAddr());
        indexAdVO.setFollowNum(shop.getFollowNum());
        indexAdVO.setName(shop.getName());
        indexAdVO.setLogo(shop.getLogo());
        indexAdVO.setType(shopAd.getType());
        indexAdVO.setAdName(shopAd.getTitle());
        indexAdVO.setItems(JSON.parseArray(shopAd.getItems(),Item.class));
        indexAdVO.setTimeDesc(formatTime(shopAd.getPublishTime()));
        indexAdVO.setType(shopAd.getType());
        indexAdVO.setLat(shop.getLat());
        indexAdVO.setLng(shop.getLng());
        indexAdVO.setDistance(distance1);
        indexAdVO.setDistanceDesc(distanceDesc);
        indexAdVO.setAdId(shopAd.getId());
        return indexAdVO;
    }
}
