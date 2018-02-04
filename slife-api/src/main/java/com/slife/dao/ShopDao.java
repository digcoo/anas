package com.slife.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slife.base.dao.CrudDao;
import com.slife.entity.Shop;
import com.slife.entity.ShopCountPerMallView;

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
    
    /**
     * 修改店铺logo
     * @param userId
     * @param logo
     * @return
     */
    public int updateLogo(@Param("userId") long userId, @Param("logo") String logo);


    /**
     * 修改店铺背景墙picture
     * @param shopId
     * @param picture
     * @return
     */
    public int updatePicture(@Param("shopId") long shopId, @Param("picture") String picture);
}
