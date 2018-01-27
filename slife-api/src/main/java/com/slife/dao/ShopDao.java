package com.slife.dao;


import com.slife.base.dao.CrudDao;
import com.slife.entity.Shop;
import com.slife.entity.ShopAd;
import com.slife.entity.ShopCountPerMallView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant dao
 */
public interface ShopDao extends CrudDao<Shop> {

    Shop selectByUserId(@Param("userId") long userId);

    /**
     * 通过mallId获取该mall下店铺数
     * @param mallIdList mallId List
     * @return
     */
    public List<ShopCountPerMallView> countShopNumsByMallId(@Param("mallIdList") List<Long> mallIdList);

}
