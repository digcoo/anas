package com.slife.service;

import com.slife.base.entity.ReturnDTO;
import com.slife.base.service.IBaseService;
import com.slife.entity.Shop;

import java.util.List;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant service
 */
public interface IShopService extends IBaseService<Shop> {

    ReturnDTO requestRegSms(String Phone);

}
