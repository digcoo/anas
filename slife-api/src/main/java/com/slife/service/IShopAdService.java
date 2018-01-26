package com.slife.service;

import java.util.List;

import com.slife.base.entity.ReturnDTO;
import com.slife.base.service.IBaseService;
import com.slife.entity.ShopAd;
import com.slife.vo.AdAddVO;
import com.slife.vo.AdUpdateVO;

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

    /**
     * 根据geohash以及活动名称模糊搜索获取附近的活动
     *
     * @param geohash
     * @return
     */
    public List<ShopAd> selectAdsByGeohashAndName(Integer index,String geohash,String name);


    /**
     * 根据shopId获取该商家有效的活动
     *
     * @param shopId
     * @return
     */
    public List<ShopAd> selectAdsByShopId(Integer index,Long shopId);

    /**
     * 新增商家广告
     * @param shopAd
     * @return
     */
    public ReturnDTO addShopAd(AdAddVO adAddVO);

    /**
     * 修改商家广告
     * @param shopAd
     * @return
     */
    public ReturnDTO updateShopAd(AdUpdateVO adUpdateVO);


}
