package com.slife.service.impl;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.ShopAdDao;
import com.slife.entity.ShopAd;
import com.slife.service.IShopAdService;
import com.slife.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant service
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ShopAdService extends BaseService<ShopAdDao, ShopAd> implements IShopAdService {


    @Override
    public List<ShopAd> selectAdsByGeohash(Integer index,String geohash) {

        return this.baseMapper.selectAdsByGeohash(index,geohash);
    }

    @Override
    public List<ShopAd> selectAdsByGeohashAndName(Integer index,String geohash,String name) {
        if(StringUtils.isBlank(name)){
            return selectAdsByGeohash(index,geohash);
        }else{
            return this.baseMapper.selectAdsByGeohashAndName(index,geohash,name);
        }
    }

    @Override
    public List<ShopAd> selectAdsByShopId(Integer index, Long shopId) {
        return this.baseMapper.selectAdsByShopId(index,shopId);
    }
}
