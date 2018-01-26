package com.slife.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slife.base.entity.ReturnDTO;
import com.slife.base.service.impl.BaseService;
import com.slife.dao.ShopAdDao;
import com.slife.dao.ShopDao;
import com.slife.entity.Shop;
import com.slife.entity.ShopAd;
import com.slife.entity.enums.AdType;
import com.slife.enums.HttpCodeEnum;
import com.slife.service.IShopAdService;
import com.slife.util.ReturnDTOUtil;
import com.slife.util.StringUtils;

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

    protected Logger logger= LoggerFactory.getLogger(getClass());
	
	@Autowired
	ShopDao shopDao;

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

	@Override
	@Transactional(readOnly = false)
	public ReturnDTO addShopAd(ShopAd shopAd) {
		Shop localShop = shopDao.selectById(shopAd.getShopId());
		if(localShop == null) {
			return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_NOT_EXISTS);
		}
		
		if(shopAd.getType() == 0 || StringUtils.isEmpty(shopAd.getTitle())){
			return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY);
		}
		
		switch (AdType.getByCode(shopAd.getType())) {
		case DISCOUNT:
			
			break;
		case NEW:
			
			break;
		case OPEN:
			
			break;
		case ADVANCE:
			
			break;
		case OTHER:
			
			break;

		default:
			break;
		}
		
		int ret = this.baseMapper.insert(shopAd);
		if(ret > 0){
			return ReturnDTOUtil.success(shopAd);
		}
		return ReturnDTOUtil.fail(); 
	}
	
	@Override
	public ReturnDTO updateShopAd(ShopAd shopAd) {
		ShopAd localShopAd = baseMapper.selectById(shopAd.getId());
		if(localShopAd == null) {
			return ReturnDTOUtil.custom(HttpCodeEnum.AD_NOT_EXISTS);
		}
		int ret = this.baseMapper.updateById(shopAd);
		if(ret > 0){
			return ReturnDTOUtil.success(shopAd);
		}
		return ReturnDTOUtil.fail(); 
	}
}
