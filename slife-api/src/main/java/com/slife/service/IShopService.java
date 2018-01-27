package com.slife.service;

import com.slife.base.entity.ReturnDTO;
import com.slife.base.service.IBaseService;
import com.slife.entity.Shop;
import com.slife.entity.ShopCountPerMallView;
import com.slife.vo.ShopBaseVO;
import com.slife.vo.ShopVO;
import org.apache.ibatis.annotations.Param;

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

    ReturnDTO<ShopBaseVO> saveShopBase(ShopBaseVO shopBaseVO);


    ReturnDTO<ShopVO> getShopInfo(long userId);

    /**
     * 根据mallId获取该mall下的商家总数
     *
     * @param mallIdList
     * @return
     */
    public List<ShopCountPerMallView> countShopByMallId(List<Long> mallIdList);
}
