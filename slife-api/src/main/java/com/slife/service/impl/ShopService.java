package com.slife.service.impl;

import com.alibaba.fastjson.JSON;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.service.impl.BaseService;
import com.slife.dao.ShopDao;
import com.slife.entity.Shop;
import com.slife.enums.HttpCodeEnum;
import com.slife.service.IShopService;
import com.slife.service.ISmsService;
import com.slife.util.ReturnDTOUtil;
import com.slife.utils.CodeGenUtils;
import com.slife.utils.RedisKey;
import com.slife.vo.ShopBaseVO;
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

    private DateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    public ReturnDTO requestRegSms( String phone) {

        String phoneCodekey = RedisKey.PhoneCodeKey + phone;
        String phoneCodeCountKey = RedisKey.PhoneCodeCountKey+ dayFormat.format(new Date())+":" + phone;

        long ttl = stringRedisTemplate.getExpire(phoneCodekey,TimeUnit.SECONDS);

        if(ttl > 2*60){
            return ReturnDTOUtil.custom(HttpCodeEnum.SEND_SMS_FAIL);
        }

        String phoneCode = CodeGenUtils.generateVerifyCode();
        Map<String,String> params = new HashMap<String,String>();
        params.put("code",phoneCode);
        boolean sendResult = smsService.sendSms(ISmsService.regSmsTemplateId,phone,params);
        if(!sendResult){
            return ReturnDTOUtil.error(HttpCodeEnum.SEND_SMS_FAIL);
        }
        stringRedisTemplate.opsForValue().set(phoneCodekey,phoneCode,3*60, TimeUnit.SECONDS);

        return ReturnDTOUtil.success();
    }


    @Override
    public ReturnDTO saveShopBase(ShopBaseVO shopBaseVO) {

        String phoneCode = shopBaseVO.getPhoneCode();
        String  code = stringRedisTemplate.opsForValue().get(phoneCode);
        if(code == null || code .equals(phoneCode)){
            return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_SMS_ERROR);
        }
        String shopBaseKey = RedisKey.shopBaseKey + String.valueOf(shopBaseVO.getUserId());
        stringRedisTemplate.opsForValue().set(shopBaseKey, JSON.toJSONString(shopBaseVO),10,TimeUnit.MINUTES);
        return ReturnDTOUtil.success();

    }

}
