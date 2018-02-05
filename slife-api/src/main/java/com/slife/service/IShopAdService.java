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

    public List<ShopAd> selectAdsByGeohash(Integer index,String geohash,List<Long> businessList);

    /**
     * 根据geohash以及活动名称模糊搜索获取附近的活动
     *
     * @param geohash
     * @return
     */
    public List<ShopAd> selectAdsByGeohashAndName(String geohash,String name);


    /**
     * 根据shopId获取该商家有效的活动
     *
     * @param shopId
     * @return
     */
    public List<ShopAd> selectAdsByShopId(Integer index,Long shopId);

    /**
     * 新增商家广告
     * @param adAddVO
     * @return
     */
    public ReturnDTO addShopAd(AdAddVO adAddVO);

    /**
     * 修改商家广告
     * @param adUpdateVO
     * @return
     */
    public ReturnDTO updateShopAd(AdUpdateVO adUpdateVO);
    

    /**
     * 上架商家广告
     * @param adId
     * @return
     */
    public ReturnDTO publishShopAd(Long adId);


    /**
     * 下架商家广告
     * @param adId
     * @return
     */
    public ReturnDTO offShopAd(Long adId);
    

    /**
     * 删除商家广告
     * @param adId
     * @return
     */
    public ReturnDTO delShopAd(Long adId);
    
    /**
     * 商家活动列表接口（商家自查）
     * @param shopId
     * @param index
     * @return
     */
    public ReturnDTO listForShop(Long shopId, int index);
    

    /**
     * 商家广告(9元上头条)
     * @param adId
     * @return
     */
    public ReturnDTO upShopAd(Long adId);

}
