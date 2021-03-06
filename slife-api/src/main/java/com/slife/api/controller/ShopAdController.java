package com.slife.api.controller;

import com.slife.entity.*;
import com.slife.service.*;
import com.slife.utils.RedisKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.util.DateUtils;
import com.slife.util.ReturnDTOUtil;
import com.slife.util.StringUtils;
import com.slife.utils.SlifeRedisTemplate;
import com.slife.vo.Ad;
import com.slife.vo.FavorAdVO;
import com.slife.vo.FavorVO;
import com.slife.vo.FollowShopBaseVO;
import com.slife.vo.FollowShopVO;
import com.slife.vo.IndexAdVO;
import com.slife.vo.IndexMallVO;
import com.slife.vo.IndexVO;
import com.slife.vo.Item;
import com.slife.vo.ShopHomeVO;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.io.GeohashUtils;
import com.spatial4j.core.shape.Point;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant商家controller
 */
@Api(description = "活动相关接口", protocols = "http")
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
    private IReportService reportService;
    @Autowired
    private UserService userService;
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
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "geohash", value = "geo编码",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "business_ids", value = "逗号分隔的行业id",required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", value = "digcoo session key",required = false)
    })
    @ApiResponses({@ApiResponse(code = 200,message = "成功",response = IndexAdVO.class)})
    @GetMapping(value = "/ads")
    @ResponseBody
    public ReturnDTO<IndexVO> getAds(@RequestParam("index") Integer index, @RequestParam("geohash") String geohash,
                                     @RequestParam(value = "business_ids",required = false) String businessIds,
                                     @RequestParam(value = "digcoo_session_key",required = false) String digcooSessionKey
                                     ) {
        User user = null;
        if(StringUtils.isNotBlank(digcooSessionKey)) {
            String sessionKeyAndOpenId = slifeRedisTemplate.getDigcooSessionKey(digcooSessionKey);
            //session 过期
            if(StringUtils.isBlank(sessionKeyAndOpenId)){
                throw new SlifeException(HttpCodeEnum.USER_SESSION_EXPIRED);
            }else{
                String[] sessionKeyAndOpenIdArray =sessionKeyAndOpenId.split(RedisKey.DIGCOO_SESSION_KEY_DELIMITER);
                user = userService.getUserByOpenId(sessionKeyAndOpenIdArray[1]);
            }
        }
        final User user2 = user;
        if(StringUtils.isBlank(geohash) || geohash.length()<=5) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }

        IndexVO indexVO = new IndexVO();
        List<Mall> mallList = mallService.selectMallsByGeohash(geohash.substring(0,3));
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
        List<ShopAd> shopAdList;
        if(StringUtils.isBlank(businessIds)){
            shopAdList = shopAdService.selectAdsByGeohash(index==null?0:index,geohash.substring(0,3),null);
        }else{
            List<Long> businessIdList = Arrays.asList(businessIds.split(",")).stream().map(s -> Long.valueOf(s.trim())).distinct().collect(Collectors.toList());
            shopAdList = shopAdService.selectAdsByGeohash(index==null?0:index,geohash.substring(0,3),businessIdList);
        }
        if(!CollectionUtils.isEmpty(shopAdList)){
            List<Long> shopIdList = shopAdList.stream().map(ShopAd::getShopId).distinct().collect(Collectors.toList());
            List<Shop> shopList = shopService.selectBatchIds(shopIdList);
            Map<Long, Shop> shopMap = shopList.stream().filter(shop -> shop.getStatus()==3).collect(Collectors.toMap(Shop::getId,shop->shop));
            List<IndexAdVO> indexAdVOList = shopAdList.stream().map(shopAd -> shopBeanMapper(shopMap,shopAd,geohash,user2)).filter(Objects::nonNull).collect(Collectors.toList());
            indexVO.setAds(indexAdVOList);
        }
        return ReturnDTOUtil.success(indexVO);
    }

    @ApiOperation(value = "T2/T3-收藏／取消收藏活动", notes = "根据userId和adId保存或删除相应redis")
    @ApiImplicitParams({ 
    	@ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", value = "digcoo session key", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Long", name = "adId", value = "活动id",required = true) })
    @PostMapping(value = "/ads/favor")
    @ResponseBody
    public ReturnDTO favor(
    		@RequestParam("digcoo_session_key") String digcooSessionKey,
    		@RequestParam("adId") Long adId) {
    	
        if(StringUtils.isBlank(digcooSessionKey) || adId==null) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        
        String sessionKeyAndOpenId = slifeRedisTemplate.getDigcooSessionKey(digcooSessionKey);
        //session 过期
        if(StringUtils.isBlank(sessionKeyAndOpenId)){
            throw new SlifeException(HttpCodeEnum.USER_SESSION_EXPIRED);
        }else{
            String[] sessionKeyAndOpenIdArray =sessionKeyAndOpenId.split(RedisKey.DIGCOO_SESSION_KEY_DELIMITER);
            User localUser = userService.getUserByOpenId(sessionKeyAndOpenIdArray[1]);
            slifeRedisTemplate.collectOrNot(localUser.getId(),adId);
            return ReturnDTOUtil.success();
        }
    }


    @ApiOperation(value = "T5-活动搜索", notes = "根据geohash编码以及活动名称模糊搜索活动列表数据")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条", defaultValue = "0",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "geohash", value = "geo编码",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "name", value = "活动名称模糊搜索",required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", value = "digcoo session key",required = false) })
    @ApiResponses({@ApiResponse(code = 200,message = "成功",response = IndexAdVO.class)})
    @GetMapping(value = "/ads/q")
    @ResponseBody
    public ReturnDTO searchAds(@RequestParam("index") Integer index,@RequestParam("geohash") String geohash,
                               @RequestParam(value = "name",required=false) String name,
                               @RequestParam(value = "digcoo_session_key",required = false) String digcooSessionKey) {
        User user = null;
        if(StringUtils.isNotBlank(digcooSessionKey)) {
            String sessionKeyAndOpenId = slifeRedisTemplate.getDigcooSessionKey(digcooSessionKey);
            //session 过期
            if(StringUtils.isBlank(sessionKeyAndOpenId)){
                throw new SlifeException(HttpCodeEnum.USER_SESSION_EXPIRED);
            }else{
                String[] sessionKeyAndOpenIdArray =sessionKeyAndOpenId.split(RedisKey.DIGCOO_SESSION_KEY_DELIMITER);
                user = userService.getUserByOpenId(sessionKeyAndOpenIdArray[1]);
            }
        }
        final User user2 = user;
        if(StringUtils.isBlank(geohash) || geohash.length()<=5) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        List<ShopAd> shopAdList = shopAdService.selectAdsByGeohashAndName(geohash.substring(0,3),name);
        if(CollectionUtils.isEmpty(shopAdList)){
            return ReturnDTOUtil.success(shopAdList);
        }else{
            List<Long> shopIdList = shopAdList.stream().map(ShopAd::getShopId).distinct().collect(Collectors.toList());
            List<Shop> shopList = shopService.selectBatchIds(shopIdList);
            Map<Long, Shop> shopMap = shopList.stream().filter(shop -> shop.getStatus()==3).collect(Collectors.toMap(Shop::getId,Shop->Shop));
            List<IndexAdVO> indexAdVOList = shopAdList.stream().map(shopAd -> shopBeanMapper(shopMap,shopAd,geohash,user2)).filter(Objects::nonNull).sorted(Comparator.comparing(IndexAdVO::getDistance)).skip(index).limit(10).collect(Collectors.toList());
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
            shopHomeVO.setTel(shop.getTel());
            shopHomeVO.setAddr(shop.getAddr());
            shopHomeVO.setFollowNum(shop.getFollowNum());
            shopHomeVO.setName(shop.getName());
            shopHomeVO.setPicture(JSON.parseArray(shop.getPicture(),String.class));
            shopHomeVO.setLogo(shop.getLogo());
            List<ShopAd> shopAdList = shopAdService.selectAdsByShopId(index==null?0:index,shopId);
            List<Ad>  adList = shopAdList.stream().map(
                    shopAd -> {
                        Ad ad = new Ad();
                        ad.setType(shopAd.getType());
                        ad.setAdId(shopAd.getId());
                        ad.setAdName(shopAd.getTitle());
                        ad.setItems(JSON.parseArray(shopAd.getItems(),Item.class));
                        ad.setFlag(shopAd.getFlag());
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
    @ApiImplicitParams({ 
    	@ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", value = "digcoo session key",required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Long", name = "shopId", value = "店铺id",required = true) })
    @PostMapping(value = "/shop/follow")
    @ResponseBody
    public ReturnDTO follow(
    		@RequestParam("digcoo_session_key") String digcooSessionKey, 
    		@RequestParam("shopId") Long shopId) {
        
    	if(StringUtils.isBlank(digcooSessionKey) || shopId==null) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
    	
        String sessionKeyAndOpenId = slifeRedisTemplate.getDigcooSessionKey(digcooSessionKey);
        //session 过期
        if(StringUtils.isBlank(sessionKeyAndOpenId)){
            throw new SlifeException(HttpCodeEnum.USER_SESSION_EXPIRED);
        }else{
            String[] sessionKeyAndOpenIdArray =sessionKeyAndOpenId.split(RedisKey.DIGCOO_SESSION_KEY_DELIMITER);
            User localUser = userService.getUserByOpenId(sessionKeyAndOpenIdArray[1]);
            slifeRedisTemplate.followOrNot(localUser.getId(), shopId);
            return ReturnDTOUtil.success();
        }
    }

    @ApiOperation(value = "T9-举报商家", notes = "举报商家")
    @ApiImplicitParams({ 
    	@ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", value = "digcoo session key",required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Long", name = "shopId", value = "店铺id",required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Byte", name = "type", value = "类型",required = true),
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "content", value = "举报内容",required = true)})
    @PostMapping(value = "/shop/report")
    @ResponseBody
    public ReturnDTO report(
    		@RequestParam("digcoo_session_key") String digcooSessionKey, 
    		@RequestParam("shopId") Long shopId,
    		@RequestParam("type") Byte type,
    		@RequestParam("content") String content) {
        
    	if(StringUtils.isBlank(digcooSessionKey) || shopId==null) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        
        String sessionKeyAndOpenId = slifeRedisTemplate.getDigcooSessionKey(digcooSessionKey);
        //session 过期
        if(StringUtils.isBlank(sessionKeyAndOpenId)){
            throw new SlifeException(HttpCodeEnum.USER_SESSION_EXPIRED);
        }else{
            String[] sessionKeyAndOpenIdArray =sessionKeyAndOpenId.split(RedisKey.DIGCOO_SESSION_KEY_DELIMITER);
            User localUser = userService.getUserByOpenId(sessionKeyAndOpenIdArray[1]);
	        Report report = new Report();
	        report.setShopId(shopId);
	        report.setUserId(localUser.getId());
	        report.setType(type);
	        report.setContent(content);
	        return reportService.insert(report)?ReturnDTOUtil.success():ReturnDTOUtil.fail();
        }
    }


    @ApiOperation(value = "T10-关注商家列表", notes = "根据userId获取相应关注商家列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条", defaultValue = "0",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", value = "digcoo session key",required = true)
            })
    @GetMapping(value = "/shop/follow/shops")
    @ResponseBody
    public ReturnDTO followShops(
    		@RequestParam("index") Integer index, 
    		@RequestParam("digcoo_session_key") String digcooSessionKey) {
        
    	if(StringUtils.isBlank(digcooSessionKey)) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        
        String sessionKeyAndOpenId = slifeRedisTemplate.getDigcooSessionKey(digcooSessionKey);
        //session 过期
        if(StringUtils.isBlank(sessionKeyAndOpenId)){
            throw new SlifeException(HttpCodeEnum.USER_SESSION_EXPIRED);
        }else{
            String[] sessionKeyAndOpenIdArray =sessionKeyAndOpenId.split(RedisKey.DIGCOO_SESSION_KEY_DELIMITER);
            User localUser = userService.getUserByOpenId(sessionKeyAndOpenIdArray[1]);
        
	        Set<String> shopIdList = slifeRedisTemplate.getAllFollowShopIds(localUser.getId());
	        if(org.apache.commons.collections.CollectionUtils.isNotEmpty(shopIdList)) {
	            List<Long> shopIdList2 = shopIdList.stream().map(s -> Long.parseLong(s)).collect(Collectors.toList());
	            List<Shop> shopList = shopService.selectBatchIds(shopIdList2);
	            List<ShopAd> shopAdList = shopAdService.selectList(Condition.create().in("shop_id", shopIdList2).orderBy("publish_time",false));
	            Map<Long, List<ShopAd>> shopMap = shopAdList.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(ShopAd::getShopId));
	            return ReturnDTOUtil.success(shopList.stream().filter(shop -> shop.getStatus() == 3).map(shop -> {
	                ShopAd shopAd = shopMap.get(shop.getId()).get(0);
	                if(shopAd == null){
	                    return null;
	                }else{
	                    FollowShopVO followShopVO = new FollowShopVO();
	                    followShopVO.setShopId(shop.getId());
	                    followShopVO.setAddr(shop.getAddr());
	                    followShopVO.setLat(shop.getLat());
	                    followShopVO.setLng(shop.getLng());
	                    followShopVO.setShopName(shop.getName());
	                    followShopVO.setLogo(shop.getLogo());
	                    followShopVO.setAdId(shopAd.getId());
	                    followShopVO.setTimeDesc(formatTime(shopAd.getPublishTime()));
	                    followShopVO.setAdName(shopAd.getTitle());
	                    return followShopVO;
	                }
	            }).filter(Objects::nonNull).skip(index).limit(10).collect(Collectors.toList()));
	        }else{
	            return ReturnDTOUtil.custom(HttpCodeEnum.NO_DATA);
	        }
        }
    }

    @ApiOperation(value = "T11-商业中心活动列表", notes = "根据商业中心id获取该中心里所有的活动")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条", defaultValue = "0",required = true),
            @ApiImplicitParam(paramType = "path", dataType = "Long", name = "mallId", value = "商业中心id",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "floor", value = "楼层信息",required = false)
    })
    @GetMapping(value = "/mall/{mallId}/shops")
    @ResponseBody
    public ReturnDTO getAdsByMallId(@RequestParam("index") Integer index,@PathVariable("mallId") Long mallId,@RequestParam(value = "floor",required = false) String floor) {
        if(mallId == null) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
        List<ShopAd> shopAdList = shopAdService.selectAdsByMallIdAndFloor(index==null?0:index,mallId,StringUtils.isBlank(floor)?null:StringUtils.upperCase(floor));
        if(!CollectionUtils.isEmpty(shopAdList)){
            FavorVO favorVO = new FavorVO();
            List<Long> shopIdList = shopAdList.stream().map(ShopAd::getShopId).distinct().collect(Collectors.toList());
            List<Shop> shopList = shopService.selectBatchIds(shopIdList);
            Map<Long, Shop> shopMap = shopList.stream().filter(shop -> shop.getStatus()==3).collect(Collectors.toMap(Shop::getId,shop->shop));
            List<FavorAdVO> favorAdVOList = shopAdList.stream().map(shopAd -> adBeanMapper(shopMap,shopAd)).filter(Objects::nonNull).collect(Collectors.toList());
            favorVO.setAds(favorAdVOList);
            return  ReturnDTOUtil.success(favorVO);
        }else{
            return ReturnDTOUtil.custom(HttpCodeEnum.NO_DATA);
        }
    }

    @ApiOperation(value = "T12-我的关注", notes = "根据userId获取相应关注商家列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条", defaultValue = "0",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", value = "digcoo session key",required = true)
    })
    @GetMapping(value = "/user/follow/shops")
    @ResponseBody
    public ReturnDTO userFollowShops(
    		@RequestParam("index") Integer index,
    		@RequestParam("digcoo_session_key") String digcooSessionKey) {
        
    	if(StringUtils.isBlank(digcooSessionKey)) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
    	
        String sessionKeyAndOpenId = slifeRedisTemplate.getDigcooSessionKey(digcooSessionKey);
        //session 过期
        if(StringUtils.isBlank(sessionKeyAndOpenId)){
            throw new SlifeException(HttpCodeEnum.USER_SESSION_EXPIRED);
        }else{
            String[] sessionKeyAndOpenIdArray =sessionKeyAndOpenId.split(RedisKey.DIGCOO_SESSION_KEY_DELIMITER);
            User localUser = userService.getUserByOpenId(sessionKeyAndOpenIdArray[1]);
        
	        Set<String> shopIdList = slifeRedisTemplate.getAllFollowShopIds(localUser.getId());
	        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(shopIdList)) {
	            List<Long> shopIdList2 = shopIdList.stream().map(s -> Long.parseLong(s)).collect(Collectors.toList());
	            List<Shop> shopList = shopService.selectBatchIds(shopIdList2);
	            return ReturnDTOUtil.success(shopList.stream().filter(shop -> shop.getStatus() == 3).map(shop -> {
	                    FollowShopBaseVO followShopVO = new FollowShopBaseVO();
	                    followShopVO.setShopId(shop.getId());
	                    followShopVO.setAddr(shop.getAddr());
	                    followShopVO.setLat(shop.getLat());
	                    followShopVO.setLng(shop.getLng());
	                    followShopVO.setShopName(shop.getName());
	                    followShopVO.setLogo(shop.getLogo());
	                    return followShopVO;
	            }).skip(index).limit(10).collect(Collectors.toList()));
	        } else {
	            return ReturnDTOUtil.custom(HttpCodeEnum.NO_DATA);
	        }
        }
    }

    @ApiOperation(value = "T13-我的收藏", notes = "根据userId获取相应收藏活动列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "index", value = "查询初始记录，每次查询十条", defaultValue = "0",required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "digcoo_session_key", value = "digcoo session key",required = true)
    })
    @GetMapping(value = "/user/favor/ads")
    @ResponseBody
    public ReturnDTO userFavorAds(
    		@RequestParam("index") Integer index,
    		@RequestParam("digcoo_session_key") String digcooSessionKey) {
        
    	if(StringUtils.isBlank(digcooSessionKey)) {
            throw new SlifeException(HttpCodeEnum.INVALID_REQUEST);
        }
    	
        String sessionKeyAndOpenId = slifeRedisTemplate.getDigcooSessionKey(digcooSessionKey);
        //session 过期
        if(StringUtils.isBlank(sessionKeyAndOpenId)){
            throw new SlifeException(HttpCodeEnum.USER_SESSION_EXPIRED);
        }else{
            String[] sessionKeyAndOpenIdArray =sessionKeyAndOpenId.split(RedisKey.DIGCOO_SESSION_KEY_DELIMITER);
            User localUser = userService.getUserByOpenId(sessionKeyAndOpenIdArray[1]);
        
	        Set<String> adIdList = slifeRedisTemplate.getAllFavorAdIds(localUser.getId());
	        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(adIdList)) {
	            FavorVO favorVO = new FavorVO();
	            List<Long> adIdList2 = adIdList.stream().map(s -> Long.parseLong(s)).collect(Collectors.toList());
	            List<ShopAd> shopAdList = shopAdService.selectBatchIds(adIdList2);
	            if(!CollectionUtils.isEmpty(shopAdList)){
	                List<Long> shopIdList = shopAdList.stream().map(ShopAd::getShopId).distinct().collect(Collectors.toList());
	                List<Shop> shopList = shopService.selectBatchIds(shopIdList);
	                Map<Long, Shop> shopMap = shopList.stream().filter(shop -> shop.getStatus()==3).collect(Collectors.toMap(Shop::getId,shop->shop));
	                List<FavorAdVO> favorAdVOList = shopAdList.stream().map(shopAd -> adBeanMapper(shopMap,shopAd)).filter(Objects::nonNull).collect(Collectors.toList());
	                favorVO.setAds(favorAdVOList);
	                return ReturnDTOUtil.success(favorVO);
	            }
	        }
	        return ReturnDTOUtil.custom(HttpCodeEnum.NO_DATA);
        }
    }

    private String formatTime(Date date){
        Date date2=new Date();
        Calendar cal=Calendar.getInstance();
        cal.setTime(date2);
        cal.add(Calendar.DATE,-1);
        String timeStr = DateUtils.formatDate(date,"HH:mm");
        String dateStr = DateUtils.formatDate(date,"MM-dd");
        boolean yesterday = org.apache.commons.lang3.time.DateUtils.isSameDay(cal.getTime(),date);
        if(yesterday){
            return "昨天 "+timeStr;
        }
        boolean today = org.apache.commons.lang3.time.DateUtils.isSameDay(date2,date);
        if(today){
            long difference=System.currentTimeMillis()-date.getTime();
            long minute=difference/(60*1000);
            if(minute<20){
                return minute+"前";
            }
            return timeStr;
        }
        return dateStr;

    }

    private IndexAdVO shopBeanMapper(Map<Long, Shop> shopMap,ShopAd shopAd,String geohash,User user){
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
        indexAdVO.setFavor(user!=null&&slifeRedisTemplate.isCollect(user.getId(),shopAd.getId()));
        return indexAdVO;
    }

    private FavorAdVO adBeanMapper(Map<Long, Shop> shopMap,ShopAd shopAd){
        Shop shop = shopMap.get(shopAd.getShopId());
        if (shop == null){
            return null;
        }
        FavorAdVO favorAdVO = new FavorAdVO();
        favorAdVO.setShopId(shopAd.getShopId());
        favorAdVO.setAddr(shop.getAddr());
        favorAdVO.setFollowNum(shop.getFollowNum());
        favorAdVO.setName(shop.getName());
        favorAdVO.setLogo(shop.getLogo());
        favorAdVO.setType(shopAd.getType());
        favorAdVO.setAdName(shopAd.getTitle());
        favorAdVO.setItems(JSON.parseArray(shopAd.getItems(),Item.class));
        favorAdVO.setTimeDesc(formatTime(shopAd.getPublishTime()));
        favorAdVO.setType(shopAd.getType());
        favorAdVO.setLat(shop.getLat());
        favorAdVO.setLng(shop.getLng());
        favorAdVO.setAdId(shopAd.getId());
        return favorAdVO;
    }
}
