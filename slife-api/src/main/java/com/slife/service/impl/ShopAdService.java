package com.slife.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ReturnDTO addShopAd(AdAddVO adAddVO) {
		Shop localShop = shopDao.selectById(adAddVO.getShopId());
		if(localShop == null) {
			return ReturnDTOUtil.custom(HttpCodeEnum.SHOP_NOT_EXISTS);
		}
		
		if(adAddVO.getType() == 0 || StringUtils.isEmpty(adAddVO.getTitle())){
			return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY);
		}
		
		switch (AdType.getByCode(adAddVO.getType())) {
		case DISCOUNT:		//打折促销
			if(adAddVO.getStartTime() == null || adAddVO.getEndTime() == null){
				return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY);
			}
			break;
		case NEW:			//新品上新
			
			break;
		case OPEN:			//新店开业
			
			break;
		case ADVANCE:		//预告预售
			if(adAddVO.getStartTime() == null || adAddVO.getEndTime() == null){
				return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY);
			}
			break;
		case OTHER:			//其他
			if(adAddVO.getStartTime() == null || adAddVO.getEndTime() == null){
				return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY);
			}			
			break;

		default:
			return ReturnDTOUtil.custom(HttpCodeEnum.UNPROCESABLE_ENTITY);
		}
		
		ShopAd shopAd = new ShopAd();
		shopAd.setShopId(adAddVO.getShopId());
		shopAd.setType((byte)adAddVO.getType());
		shopAd.setTitle(adAddVO.getTitle());
		shopAd.setItems(adAddVO.getItems());
		shopAd.setStartTime(adAddVO.getStartTime());
		shopAd.setEndTime(adAddVO.getEndTime());
		
		//冗余字段
		shopAd.setGeohash(localShop.getGeohash());
		shopAd.setShopName(localShop.getName());
		
		int ret = this.baseMapper.insert(shopAd);
		if(ret > 0){
			return ReturnDTOUtil.success(shopAd);
		}
		return ReturnDTOUtil.fail(); 
	}
	
	@Override
	@Transactional(readOnly = false)
	public ReturnDTO updateShopAd(AdUpdateVO updateVO) {
		ShopAd localShopAd = baseMapper.selectById(updateVO.getAdId());
		if(localShopAd == null) {
			return ReturnDTOUtil.custom(HttpCodeEnum.AD_NOT_EXISTS);
		}
		
		localShopAd.setType(updateVO.getType());
		localShopAd.setTitle(updateVO.getTitle());
		localShopAd.setStartTime(updateVO.getStartTime());
		localShopAd.setEndTime(updateVO.getEndTime());
		localShopAd.setItems(updateVO.getItems());
		
		int ret = this.baseMapper.updateById(localShopAd);
		if(ret > 0){
			return ReturnDTOUtil.success(localShopAd);
		}
		return ReturnDTOUtil.fail(); 
	}
}
