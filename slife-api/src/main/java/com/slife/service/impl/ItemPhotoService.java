package com.slife.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<Integer, List<ItemPhotoVO>> findIndexs() {
		
		List<ItemPhoto> photos = this.baseMapper.findIndexs();
		
		//拼装Map
		Map<Integer, List<ItemPhotoVO>> map = new HashMap<Integer, List<ItemPhotoVO>>();
		for (ItemPhoto itemPhoto : photos) {
			if(map.get(itemPhoto.getCategory()) == null){
				List<ItemPhotoVO> list = new ArrayList<ItemPhotoVO>();
				list.add(new ItemPhotoVO(itemPhoto.getCategory(), itemPhoto.getSmallPhoto(), itemPhoto.getBigPhoto()));
				map.put((int)itemPhoto.getCategory(), list);
			}else{
				map.get(itemPhoto.getCategory()).add(new ItemPhotoVO(itemPhoto.getCategory(), itemPhoto.getSmallPhoto(), itemPhoto.getBigPhoto()));
			}
		}
		
		return map;
	}
	

	@Override
	public List<ItemPhotoVO> findPageByCategory(Integer index, Integer category) {
		List<ItemPhotoVO> list = new ArrayList<ItemPhotoVO>();
		List<ItemPhoto> locals = this.baseMapper.findPageByCategory(index, category);
		for (ItemPhoto itemPhoto : locals) {
			list.add(new ItemPhotoVO(itemPhoto.getCategory(), itemPhoto.getSmallPhoto(), itemPhoto.getBigPhoto()));
		}
		return list;
	}

	@Override
	public List<ItemPhotoVO> search(Integer index, String key) {
		List<ItemPhotoVO> list = new ArrayList<ItemPhotoVO>();
		List<ItemPhoto> locals = this.baseMapper.search(index, key);
		for (ItemPhoto itemPhoto : locals) {
			list.add(new ItemPhotoVO(itemPhoto.getCategory(), itemPhoto.getSmallPhoto(), itemPhoto.getBigPhoto()));
		}
		return list;
	}

	
}
