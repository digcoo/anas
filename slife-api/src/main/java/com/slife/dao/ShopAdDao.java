package com.slife.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slife.base.dao.CrudDao;
import com.slife.entity.ShopAd;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant dao
 */
public interface ShopAdDao extends CrudDao<ShopAd> {

    ShopAd selectById(long id);

    /**
     * 根据geohash获取附近的活动
     *
     * @param geohash
     * @return
     */
    public List<ShopAd> selectAdsByGeohash(@Param("index") Integer index, @Param("geohash") String geohash);

    /**
     * 根据geohash获取附近的活动
     *
     * @param geohash
     * @param name
     * @return
     */
    public List<ShopAd> selectAdsByGeohashAndName(@Param("geohash") String geohash, @Param("name") String name);


    /**
     * 根据shopId获取该商家的所有活动
     *
     * @param shopId
     * @return
     */
    public List<ShopAd> selectAdsByShopId(@Param("index") Integer index, @Param("shopId") Long shopId);


    /**
     * 修改活动状态:上架、下架、过期、删除
     *
     * @param adId
     * @param status
     * @return
     */
    public int updateStatus(@Param("adId") Long adId, @Param("status") int status);


    /**
     * 商家活动列表接口（商家自查）
     *
     * @param shopId
     * @param statuses
     * @return
     */
    public List<ShopAd> listForShop(@Param("shopId") Long shopId, @Param("statuses") List<Integer> statuses, @Param("index") int index);

    /**
     * 商家活动发布上线
     * @param adId
     * @param publishTime
     * @param status
     * @return
     */
    public int publishShopAd(@Param("adId") Long adId, @Param("publishTime") Date publishTime, @Param("status") int status);
}
