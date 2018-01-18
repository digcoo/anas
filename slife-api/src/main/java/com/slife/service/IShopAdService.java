package com.slife.service;

import com.slife.base.service.IBaseService;
import com.slife.entity.ShopAd;

import java.util.List;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant service
 */
public interface IShopAdService extends IBaseService<ShopAd> {



    /**
     * 根据geohash获取附近的活动
     *
     * @param geohash
     * @return
     */
    public List<ShopAd> selectAdsByGeohash(Integer index,String geohash);
}
