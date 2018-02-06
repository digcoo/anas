package com.slife.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.service.impl.BaseService;
import com.slife.dao.PayOrderDao;
import com.slife.dao.ShopAdDao;
import com.slife.dao.ShopAdSpreadDao;
import com.slife.dao.ShopDao;
import com.slife.dao.UserDao;
import com.slife.entity.PayOrder;
import com.slife.entity.Shop;
import com.slife.entity.ShopAd;
import com.slife.entity.ShopAdSpread;
import com.slife.entity.User;
import com.slife.entity.enums.AdStatus;
import com.slife.entity.enums.AdType;
import com.slife.entity.enums.PayStatus;
import com.slife.entity.enums.SpreadType;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.IShopAdService;
import com.slife.util.ReturnDTOUtil;
import com.slife.util.StringUtils;
import com.slife.utils.RedisKey;
import com.slife.utils.SlifeRedisTemplate;
import com.slife.vo.Ad;
import com.slife.vo.AdAddVO;
import com.slife.vo.AdUpdateVO;
import com.slife.vo.Item;
import com.slife.vo.PrepayVO;
import com.slife.wxapi.request.WxPayApi;
import com.slife.wxapi.request.WxPayReq;
import com.slife.wxapi.response.WxPayRsp;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant service
 */
@Service
//@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ShopAdService extends BaseService<ShopAdDao, ShopAd> implements IShopAdService {
	
	private final static int MAX_COUNT_FREE_PUBLISH_OF_PER_DAY = 3;		//商家每天免费发布的活动条数

	private final static int DURING_PUBLISH_BETWEEN_AD = 5;		//同一商家发布广告的时间间隔（分钟）
	
	private final static int NEW_SHOP_REMAIN_DAYS = 30;		//新店开业呈现时间
	
	private final static int NEW_PRODUCT_REMAIN_DAYS = 15;		//新店开业呈现时间


	private static final SimpleDateFormat YMDHMD_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat DAY_TIME_FORMAT = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private SlifeRedisTemplate slifeRedisTemplate;

	protected Logger logger= LoggerFactory.getLogger(getClass());

	@Autowired
	ShopDao shopDao;

	@Autowired
	UserDao userDao;

	@Autowired
	PayOrderDao payOrderDao;


	@Autowired
	ShopAdSpreadDao shopAdSpreadDao;

	@Autowired
	StringRedisTemplate stringRedisTemplate;


	@Autowired
	WxPayApi wxPayApi;

    @Override
    public List<ShopAd> selectAdsByGeohash(Integer index,String geohash,List<Long> businessList) {

        return this.baseMapper.selectAdsByGeohash(index,geohash,businessList);
    }

	@Override
	public List<ShopAd> selectAdsByGeohashAndName(String geohash,String name) {
		return this.baseMapper.selectAdsByGeohashAndName(geohash,name);
	}

    @Override
    public List<ShopAd> selectAdsByShopId(Integer index, Long shopId) {
        return this.baseMapper.selectAdsByShopId(index,shopId);
    }

	@Override
	@Transactional(readOnly = false)
	public ReturnDTO addShopAd(AdAddVO adAddVO) {
		Shop localShop = shopDao.selectById(adAddVO.getShopId());
		if(localShop == null) {
			return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_NOT_EXISTS);
		}
		
		if(adAddVO.getType() == 0 || StringUtils.isEmpty(adAddVO.getTitle())){
			return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY);
		}
		Calendar calendar = Calendar.getInstance();
		switch (AdType.getByCode(adAddVO.getType())) {
		case DISCOUNT:		//打折促销
			if(adAddVO.getStartTime() == null || adAddVO.getEndTime() == null){
				return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY);
			}
			break;
		case NEW:			//新品上新
			adAddVO.setStartTime(calendar.getTime());
			calendar.add(Calendar.DAY_OF_YEAR, NEW_PRODUCT_REMAIN_DAYS);
			adAddVO.setEndTime(calendar.getTime());
			break;
		case OPEN:			//新店开业
			adAddVO.setStartTime(calendar.getTime());
			calendar.add(Calendar.DAY_OF_YEAR, NEW_SHOP_REMAIN_DAYS);
			adAddVO.setEndTime(calendar.getTime());
			break;
		case ADVANCE:		//预告预售
			if(adAddVO.getStartTime() == null || adAddVO.getEndTime() == null){
				return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY);
			}
			break;
		case OTHER:			//其他
			if(adAddVO.getStartTime() == null || adAddVO.getEndTime() == null){
				return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY);
			}
			break;

		default:
			return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY);
		}
		
		//items
		if(StringUtils.isNotEmpty(adAddVO.getItems())){
			List<Item> items = JSON.parseArray(adAddVO.getItems(), Item.class);
			for (Item item : items) {
					item.setLabel("¥" + item.getLabel());
				item.setLabel(item.getLabel());
			}
			adAddVO.setItems(JSON.toJSONString(items));
		}

		ShopAd shopAd = new ShopAd();
		shopAd.setShopId(adAddVO.getShopId());
		shopAd.setType((byte)adAddVO.getType());
		shopAd.setTitle(adAddVO.getTitle());
		shopAd.setItems(adAddVO.getItems());
		shopAd.setStartTime(adAddVO.getStartTime());
		shopAd.setEndTime(adAddVO.getEndTime());
		shopAd.setStatus((byte)AdStatus.INIT.getStatus());
		
		//冗余字段
		shopAd.setGeohash(localShop.getGeohash());
		shopAd.setShopName(localShop.getName());
		shopAd.setBusinessId(localShop.getBusinessId());
		shopAd.setMallId(localShop.getMallId());
		shopAd.setFloor(localShop.getFloor());
		
		int ret = this.baseMapper.insert(shopAd);
		if(ret > 0){
			return ReturnDTOUtil.success(shopAd);
		}
		return ReturnDTOUtil.fail(); 
	}
	
	@Override
	@Transactional(readOnly = false)
	public ReturnDTO updateShopAd(AdUpdateVO updateVO) {
		ShopAd localShopAd = baseMapper.selectById(updateVO.getAdId());
		if(localShopAd == null) {
			return ReturnDTOUtil.custom(HttpCodeEnum.AD_NOT_EXISTS);
		}
		
		if((int)localShopAd.getStatus() == AdStatus.ON.getStatus()){
			return ReturnDTOUtil.custom(HttpCodeEnum.AD_NOT_PERMIT);
		}
		
		localShopAd.setType(updateVO.getType());
		localShopAd.setTitle(updateVO.getTitle());
		localShopAd.setStartTime(updateVO.getStartTime());
		localShopAd.setEndTime(updateVO.getEndTime());
		localShopAd.setItems(updateVO.getItems());
		
		int ret = this.baseMapper.updateById(localShopAd);
		if(ret > 0){
			return ReturnDTOUtil.success(localShopAd);
		}
		return ReturnDTOUtil.fail(); 
	}

	@Override
	@Transactional(readOnly = false)
	public ReturnDTO publishShopAd(Long adId) {
		// 只有下架、初始状态的ad能上架
		
		ShopAd localShopAd = baseMapper.selectById(adId);
		if(localShopAd == null){
			return ReturnDTOUtil.custom(HttpCodeEnum.AD_NOT_EXISTS);
		}
		
		Date publishTime = new Date();
		
		//判断上一条发布的活动时间间隔是否超过10分钟
		List<ShopAd> publishedAds = baseMapper.selectAdsByShopId(0, localShopAd.getShopId());
		
		if(publishedAds.size() > 1 && (publishTime.getTime() - publishedAds.get(1).getPublishTime().getTime()) < DURING_PUBLISH_BETWEEN_AD * 60 * 1000){
			return ReturnDTOUtil.custom(HttpCodeEnum.AD_NOT_PERIOD);
		}
		
		//判断当天是否达到免费发布的上线
		int currentTimes = 0;
		for (ShopAd shopAd : publishedAds) {
			if (DateUtils.isSameDay(shopAd.getPublishTime(), publishTime)) {
				currentTimes++;
			}
		}
		Shop shop = shopDao.selectById(localShopAd.getShopId());
		if (currentTimes >= getUserCanPubCount(shop.getUserId())) {
			return ReturnDTOUtil.custom(HttpCodeEnum.AD_OVER_LIMIT);
		}
		
		ShopAd shopAd = new ShopAd();
		shopAd.setId(adId);
		shopAd.setPublishTime(publishTime);
		shopAd.setStatus((byte)AdStatus.ON.getStatus());
		
		int ret = baseMapper.updateById(shopAd);
		if(ret > 0){
			return ReturnDTOUtil.success();
		}
		return ReturnDTOUtil.fail();
	}

	@Override
	@Transactional(readOnly = false)
	public ReturnDTO offShopAd(Long adId) {
		// 只有上架状态的ad能下架
		int ret = baseMapper.updateStatus(adId, AdStatus.OFF.getStatus());
		if(ret > 0){
			return ReturnDTOUtil.success();
		}
		return ReturnDTOUtil.fail();
	}

	@Override
	@Transactional(readOnly = false)
	public ReturnDTO delShopAd(Long adId) {
		// 只有下架、初始、过期状态的ad能下架
		int ret = baseMapper.updateStatus(adId, AdStatus.EXPIRED.getStatus());
		if(ret > 0){
			return ReturnDTOUtil.success();
		}
		return ReturnDTOUtil.fail();
	}

	@Override
	public ReturnDTO listForShop(Long shopId, int index) {
        Shop shop = shopDao.selectById(shopId);
        if(shop == null){
            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_NOT_EXISTS);
        }
		List<Integer> statuses = Arrays.asList(AdStatus.INIT.getStatus(), AdStatus.OFF.getStatus(), AdStatus.ON.getStatus());
		List<ShopAd> shopAdList = baseMapper.listForShop(shopId, statuses, index);
		List<Ad>  adList = shopAdList.stream().map(
                shopAd -> {
                    Ad ad = new Ad();
                    ad.setType(shopAd.getType());
                    ad.setAdId(shopAd.getId());
                    ad.setAdName(shopAd.getTitle());
                    ad.setItems(JSON.parseArray(shopAd.getItems(),Item.class));
                    ad.setStatus((int)shopAd.getStatus());
                    ad.setTimeDesc(com.slife.util.DateUtils.formatDate(shopAd.getPublishTime(), "yyyy-MM-dd HH:mm"));
                    ad.setFavorNum(0);
                    ad.setFlag(shopAd.getFlag());
//                    ad.setFavorNum(slifeRedisTemplate.getFavorNum(shopAd.getId()));
                    return ad;
                }
        ).collect(Collectors.toList());
		return ReturnDTOUtil.success(adList);
	}

	@Override
	@Transactional(readOnly = false)
	public ReturnDTO upShopAd(Long adId) {
		//只有已发布(或当日没费次数超限)的活动，才会有必用“上头条”，前端业务流程需更改：隐藏9元上头条的逻辑，待用户超过限制之后再弹出
		Date publishTime = new Date();
		
		ShopAd shopAd = new ShopAd();
		shopAd.setId(adId);
		shopAd.setPublishTime(publishTime);
		shopAd.setStatus((byte)AdStatus.ON.getStatus());
		int updateAd = baseMapper.updateById(shopAd);
		
		ShopAdSpread adSpread = new ShopAdSpread();
		adSpread.setAdId(adId);
		adSpread.setType((byte)SpreadType.TOP.getType());
		int addSpread = shopAdSpreadDao.insert(adSpread);
		
		if(updateAd == 0 || addSpread == 0){
			throw new SlifeException(HttpCodeEnum.UN_KNOW_ERROR);
		}
		return ReturnDTOUtil.success();
	}

	public ReturnDTO<PrepayVO> payAd(long userId) {
		User original = userDao.selectByPrimaryKey(userId);
		PayOrder payOrder = new PayOrder();
		payOrder.setItemName("大喇叭-广告服务费");
		payOrder.setPrice(300);
		payOrder.setQuantity(1);
		payOrder.setTotal(300);
		payOrder.setOpenId(original.getOpenId());
		payOrder.setUserId(original.getId());
		payOrder.setPayStatus(PayStatus.PRE_PAY.getIndex());
		Calendar expireDate = Calendar.getInstance();
		expireDate.add(Calendar.HOUR, 2);
		payOrder.setExpireDate(expireDate.getTime());
		payOrderDao.insert(payOrder);

		WxPayReq wxPayReq = new WxPayReq();

		wxPayReq.setBody(payOrder.getItemName());
		wxPayReq.setOpenid(payOrder.getOpenId());
		wxPayReq.setOut_trade_no(String.valueOf(payOrder.getId()));
		wxPayReq.setTotal_fee(String.valueOf(payOrder.getTotal()));
		wxPayReq.setTime_start(YMDHMD_TIME_FORMAT.format(new Date()));
		wxPayReq.setTime_expire(YMDHMD_TIME_FORMAT.format(payOrder.getExpireDate()));

		WxPayRsp wxPayRsp = wxPayApi.preparePay(wxPayReq);
		if(wxPayRsp == null || !"SUCCESS".equals(wxPayRsp.getResult_code()) ){
			throw new SlifeException(HttpCodeEnum.PAY_ERROR);
		}
		payOrder.setPrepayId(wxPayRsp.getPrepay_id());
		payOrder.setAttach(wxPayRsp.getResponseBody());
		payOrderDao.updateById(payOrder);

		PrepayVO prepayVO = new PrepayVO();
		prepayVO.setPrepareId(wxPayRsp.getPrepay_id());
		prepayVO.setPayOrderId(payOrder.getId());

		return ReturnDTOUtil.success(prepayVO);

	}

	public boolean paySuccess(JSONObject callBackObject) {
		long payOrderId = callBackObject.getLongValue("out_trade_no");
		PayOrder payOrder = payOrderDao.selectById(payOrderId);
		if(payOrder == null){
			return  true;
		}
		if(payOrder.getPayStatus() ==PayStatus.PAID.getIndex()){
			return  true;
		}
		payOrder.setCallback(callBackObject.getString("callBackBody"));
		payOrder.setPayStatus(PayStatus.PAID.getIndex());
		payOrder.setPayDate(new Date());
		payOrder.setTransactionId(callBackObject.getString("transaction_id"));
		long incrResult = incrUserCanPubCount(payOrder.getUserId());
		if(incrResult > 0){
			payOrderDao.updateById(payOrder);
			return  true;
		}
		return false;
	}


	private long getUserCanPubCount(long userId){
		String key = RedisKey.SHOP_CAN_PUBLISH_NUM + String.valueOf(userId)+":"+DAY_TIME_FORMAT.format(new Date());
		String value = stringRedisTemplate.opsForValue().get(key);
		if(value ==null){
			return MAX_COUNT_FREE_PUBLISH_OF_PER_DAY;
		}
		else{
			return Integer.valueOf(value);
		}
	}

	private long  incrUserCanPubCount(long userId){
		String key = RedisKey.SHOP_CAN_PUBLISH_NUM + String.valueOf(userId)+":"+DAY_TIME_FORMAT.format(new Date());
		String value = stringRedisTemplate.opsForValue().get(key);
		long result = 0L;
		if(value == null){
			result =  stringRedisTemplate.opsForValue().increment(key,MAX_COUNT_FREE_PUBLISH_OF_PER_DAY  + 1);
		} else{
			result = stringRedisTemplate.opsForValue().increment(key,1);
		}
		return  result;
	}
}
