package com.slife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.ItemPhotoDao;
import com.slife.entity.ItemPhoto;
import com.slife.service.IItemPhotoService;
import com.slife.vo.ItemPhotoVO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ItemPhotoService extends BaseService<ItemPhotoDao, ItemPhoto> implements IItemPhotoService{
	
	@Override
	public List<ItemPhotoVO> findIndexs() {
//		List pages = this.baseMapper.findIndexs();
		//to map
		return new ArrayList<ItemPhotoVO>();
	}
	

	@Override
	public List<ItemPhotoVO> findPageByCategory(Integer index, String category) {
//		List pages = this.baseMapper.findPageByCategory(index, category);
		
		return new ArrayList<ItemPhotoVO>();
	}

	@Override
	public List<ItemPhotoVO> search(Integer index, String key) {
//		List pages = this.baseMapper.search(index, key);
		return new ArrayList<ItemPhotoVO>();
	}

	
}
