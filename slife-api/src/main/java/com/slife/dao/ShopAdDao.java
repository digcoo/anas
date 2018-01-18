package com.slife.dao;


import com.slife.base.dao.CrudDao;
import com.slife.entity.ShopAd;
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
public interface ShopAdDao extends CrudDao<ShopAd> {

    /**
     * 根据geohash获取附近的活动
     *
     * @param geohash
     * @return
     */
    public List<ShopAd> selectAdsByGeohash(@Param("index") Integer index,@Param("geohash") String geohash);

}
