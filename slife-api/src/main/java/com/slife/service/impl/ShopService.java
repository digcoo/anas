package com.slife.service.impl;

import com.alibaba.fastjson.JSON;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.service.impl.BaseService;
import com.slife.dao.ShopAdDao;
import com.slife.dao.ShopDao;
import com.slife.entity.Shop;
import com.slife.enums.HttpCodeEnum;
import com.slife.service.IShopService;
import com.slife.service.ISmsService;
import com.slife.util.ReturnDTOUtil;
import com.slife.utils.CodeGenUtils;
import com.slife.utils.RedisKey;
import com.slife.vo.ShopBaseVO;
import com.slife.vo.ShopMallVO;
import com.slife.vo.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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


    @Autowired
    private ISmsService smsService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ShopDao shopDao;

    private DateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    public ReturnDTO requestRegSms( String phone) {

        String phoneCodekey = RedisKey.PhoneCodeKey + phone;
        String phoneCodeCountKey = RedisKey.PhoneCodeCountKey+ dayFormat.format(new Date())+":" + phone;

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
    public ReturnDTO<ShopBaseVO> saveShopBase(ShopBaseVO shopBaseVO) {
        String phoneCodekey = RedisKey.PhoneCodeKey + shopBaseVO.getPhone();

        String phoneCode = shopBaseVO.getPhoneCode();
        String  code = stringRedisTemplate.opsForValue().get(phoneCodekey);

        if(code == null || !code .equals(phoneCode)){
            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_SMS_ERROR);
        }
        Shop shop = shopDao.selectByUserId(shopBaseVO.getUserId());
        if(shop != null && (shop.getAuditState() == 1 || shop.getAuditState() == 2)){
            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_USER_DUP);
        }

        shop = new Shop();
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
        shop.setStatus(Byte.valueOf("0"));
        shop.setAuditState(0);
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
        return ReturnDTOUtil.success(shopVO);

    }

    public ReturnDTO saveShop(ShopMallVO shopMallVO) {
        Shop shop = shopDao.selectByUserId(shopMallVO.getUserId());

        if(shop == null ){
            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_USER_NOT_FOUND);
        }
        if(shop.getAuditState() != 0){
            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_USER_DUP);
        }
        shop.setShopType(shopMallVO.getShopType());
        shop.setMallId(shopMallVO.getMallId());
        shop.setFloor(shopMallVO.getFloor());
        shop.setRoom(shopMallVO.getRoom());
        shop.setAgentIdentifyCard(shopMallVO.getAgentIdentifyCard());
        shop.setAgentPortrait(shopMallVO.getAgentPortrait());
        shop.setBusinessLicense(shopMallVO.getBusinessLicense());
        shop.setAuditState(1); //等待审核
        shopDao.updateById(shop);
        return ReturnDTOUtil.success();
    }
}
