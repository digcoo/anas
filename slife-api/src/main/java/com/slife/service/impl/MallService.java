package com.slife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.MallDao;
import com.slife.entity.Mall;
import com.slife.service.IMallService;
import com.slife.vo.MallVO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MallService extends BaseService<MallDao, Mall> implements IMallService{
	
	@Override
	public List<MallVO> findListBykey(String key) {
		List<Mall> malls = this.baseMapper.findPageByKey(key);
		if(malls != null && malls.size() > 0){
			List<MallVO> vos = new ArrayList<MallVO>(malls.size());
			for (Mall mall : malls) {
				vos.add(new MallVO(mall.getId(), mall.getName()));
			}
			return vos;
		}
		return null;
	}

	@Override
	public List<Mall> selectMallsByGeohash(String geohash) {
		return this.baseMapper.selectMallsByGeohash(geohash);
	}
}
