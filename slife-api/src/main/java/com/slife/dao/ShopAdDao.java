package com.slife.dao;


import com.slife.base.dao.CrudDao;
import com.slife.entity.ShopAd;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant dao
 */
@CacheConfig(cacheNames = "ads")
public interface ShopAdDao extends CrudDao<ShopAd> {

    /**
     * 根据geohash获取附近的活动
     *
     * @param geohash
     * @return
     */
    @Cacheable
    public List<ShopAd> selectAdsByGeohash(@Param("index") Integer index,@Param("geohash") String geohash);

    /**
     * 根据geohash获取附近的活动
     *
     * @param geohash
     * @param name
     * @return
     */
    @Cacheable
    public List<ShopAd> selectAdsByGeohashAndName(@Param("index") Integer index,@Param("geohash") String geohash,@Param("name") String name);


    /**
     * 根据shopId获取该商家的有效活动
     *
     * @param shopId
     * @return
     */
    @Cacheable
    public List<ShopAd> selectAdsByShopId(@Param("index") Integer index,@Param("shopId") Long shopId);



}
