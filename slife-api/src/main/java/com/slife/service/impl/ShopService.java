package com.slife.service.impl;

import com.slife.base.entity.ReturnDTO;
import com.slife.base.service.impl.BaseService;
import com.slife.dao.ShopDao;
import com.slife.entity.Shop;
import com.slife.enums.HttpCodeEnum;
import com.slife.service.IShopService;
import com.slife.service.ISmsService;
import com.slife.util.ReturnDTOUtil;
import com.slife.utils.CodeGenUtils;
import org.jasig.cas.client.session.HashMapBackedSessionMappingStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Override
    public ReturnDTO requestRegSms( String phone) {

        String phoneCode = CodeGenUtils.generateVerifyCode();
        Map<String,String> params = new HashMap<String,String>();
        params.put("code",phoneCode);
        boolean sendResult = smsService.sendSms(ISmsService.regSmsTemplateId,phone,params);
        if(!sendResult){
            return ReturnDTOUtil.error(HttpCodeEnum.SEND_SMS_FAIL);
        }
        return ReturnDTOUtil.success();
    }
}
