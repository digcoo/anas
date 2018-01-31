package com.slife.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.slife.baidu.FaceMatchService;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.service.impl.BaseService;
import com.slife.dao.ShopDao;
import com.slife.dao.UserDao;
import com.slife.entity.Shop;
import com.slife.entity.ShopCountPerMallView;
import com.slife.entity.enums.ShopStatus;
import com.slife.entity.enums.UserType;
import com.slife.enums.HttpCodeEnum;
import com.slife.service.IShopService;
import com.slife.service.ISmsService;
import com.slife.util.ReturnDTOUtil;
import com.slife.utils.CodeGenUtils;
import com.slife.utils.RedisKey;
import com.slife.vo.ShopBaseVO;
import com.slife.vo.ShopMallVO;
import com.slife.vo.ShopVO;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant service
 */
@Service
public class ShopService extends BaseService<ShopDao, Shop> implements IShopService {


    @Value("${aliyun.oss.domain}")
    private String ossDomain;

    @Autowired
    private ISmsService smsService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private UserDao userDao;

    private DateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    public ReturnDTO requestRegSms( String phone) {

        String phoneCodekey = RedisKey.PHONE_CODE_KEY + phone;
        String phoneCodeCountKey = RedisKey.PHONE_CODE_COUNT+ dayFormat.format(new Date())+":" + phone;

        long ttl = stringRedisTemplate.getExpire(phoneCodekey,TimeUnit.SECONDS);

        if(ttl > 9*60){
            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_SMS_FRE);
        }

        String phoneCode = CodeGenUtils.generateVerifyCode();
        Map<String,String> params = new HashMap<String,String>();
        params.put("code",phoneCode);
        boolean sendResult = smsService.sendSms(ISmsService.regSmsTemplateId,phone,params);
        if(!sendResult){
            return ReturnDTOUtil.error(HttpCodeEnum.SEND_SMS_FAIL);
        }
        stringRedisTemplate.opsForValue().set(phoneCodekey,phoneCode,10*60, TimeUnit.SECONDS);

        return ReturnDTOUtil.success();
    }


    @Override
    @Transactional(readOnly = false)
    public ReturnDTO<ShopBaseVO> saveShopBase(ShopBaseVO shopBaseVO) {
    	
//    	String phoneCodekey = RedisKey.PHONE_CODE_KEY + shopBaseVO.getPhone();
//
//    	String phoneCode = shopBaseVO.getPhoneCode();
//    	String  code = stringRedisTemplate.opsForValue().get(phoneCodekey);
//
//    	if(code == null || !code .equals(phoneCode)){
//    		return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_SMS_ERROR);
//    	}
        
        Shop shop = shopDao.selectByUserId(shopBaseVO.getUserId());
        if(shop != null){
        	if (shop.getStatus() == ShopStatus.AUDITED.getCode()) {
        		 return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_USER_DUP);
			}
        }else {
        	shop = new Shop();
		}

        shop.setUserId(shopBaseVO.getUserId());
        shop.setName(shopBaseVO.getShopName());
        shop.setAgentPosition(shopBaseVO.getPosition());
        shop.setTel(shopBaseVO.getPhone());
        shop.setAddr(shopBaseVO.getAddr());
        shop.setLat(shopBaseVO.getLat());
        shop.setLng(shopBaseVO.getLng());
        shop.setGeohash(shopBaseVO.getGeohash());
        shop.setBusinessId(shopBaseVO.getBusinessId());
        shop.setOpenMobile(shopBaseVO.isOpenMobile()?Byte.valueOf("1"):Byte.valueOf("0"));
        shop.setStatus((byte)ShopStatus.RIGISTER_ONE.getCode());
        shop.setFollowNum(0);
        if(shop.getId() == null){
            shopDao.insert(shop);
        }else{
            shopDao.updateById(shop);
        }
        return ReturnDTOUtil.success(shopBaseVO);

    }

    @Override
    public ReturnDTO<ShopVO> getShopInfo(long userId) {
        Shop shop = shopDao.selectByUserId(userId);
        if(shop == null ){
            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_USER_NOT_FOUND);
        }
        ShopVO shopVO  = new ShopVO();
        shopVO.setId(shop.getId());
        shopVO.setUserId(shop.getUserId());
        shopVO.setAddr(shop.getAddr());
        shopVO.setAgentIdentifyCard(shop.getAgentIdentifyCard());
        shopVO.setAgentPortrait(shop.getAgentPortrait());
//        shopVO.setAuditState(shop.getAuditState());
        shopVO.setBusinessId(shop.getBusinessId());
        shopVO.setBusinessLicense(shop.getBusinessLicense());
        shopVO.setFloor(shop.getFloor());
        shopVO.setFollowum(shop.getFollowNum());
        shopVO.setGeohash(shop.getGeohash());
        shopVO.setLat(shop.getLat());
        shopVO.setLng(shop.getLng());
        shopVO.setLogo(shop.getLogo());
        shopVO.setMallId(shop.getMallId());
        shopVO.setOpenMobile(shop.getOpenMobile().equals(Byte.valueOf("1"))?true:false);
        shopVO.setPhone(shop.getTel());
        shopVO.setPicture(shop.getPicture());
        shopVO.setPosition(shop.getAgentPosition());
        shopVO.setRoom(shop.getRoom());
        shopVO.setShopName(shop.getName());
        shopVO.setShopType(shop.getShopType());
        return ReturnDTOUtil.success(shopVO);

    }

    @Transactional(readOnly = false)
    public ReturnDTO saveShop(ShopMallVO shopMallVO) {
    	
        Shop shop = shopDao.selectByUserId(shopMallVO.getUserId());

        if(shop == null ){
            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_USER_NOT_FOUND);
        }
        if(shop.getStatus() == ShopStatus.AUDITED.getCode()){
            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_USER_DUP);
        }

        shop.setShopType(shopMallVO.getShopType());
        shop.setMallId(shopMallVO.getMallId());
        shop.setFloor(shopMallVO.getFloor());
        shop.setRoom(shopMallVO.getRoom());
        shop.setAgentIdentifyCard(shopMallVO.getAgentIdentifyCard());
        shop.setAgentPortrait(shopMallVO.getAgentPortrait());
        shop.setBusinessLicense(shopMallVO.getBusinessLicense());
        shop.setStatus((byte)ShopStatus.RIGISTER_TWO.getCode());	//注册第二部，等待审核
        
        //todo 调用自动识别身份
//        JSONObject parseObject = JSON.parseObject(shopMallVO.getAgentIdentifyCard());
//        String idA = ossDomain + "/" + parseObject.getString("ID_A");
//        String agentPortrait = ossDomain + "/" + shopMallVO.getAgentPortrait();
//        String matchStr = FaceMatchService.match(idA, agentPortrait);
//        if (StringUtils.isNotEmpty(matchStr) && JSON.parseObject(matchStr).getJSONArray("result").getJSONObject(0).getFloat("score") > 50) {
        	shop.setStatus((byte)ShopStatus.AUDITED.getCode());	//直接审核通过
//		}else {
//            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_AUDIT_DENY);
//		}
        
        //升级为商家用户
        userDao.updateToShop(shop.getUserId(), UserType.SHOP_USER.getCode());
        
        shopDao.updateById(shop);
        return ReturnDTOUtil.success();
    }

    @Override
    public List<ShopCountPerMallView> countShopByMallId(List<Long> mallIdList) {
        return this.baseMapper.countShopNumsByMallId(mallIdList);
    }
}
