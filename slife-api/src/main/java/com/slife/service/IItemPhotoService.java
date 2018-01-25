package com.slife.service;

import java.util.List;
import java.util.Map;

import com.slife.vo.ItemPhotoVO;

/**
 * Created by duyp on 18-1-23.
 */
public interface IItemPhotoService {
	
	Map<Integer, List<ItemPhotoVO>> findIndexs();
	
    List<ItemPhotoVO> findPageByCategory(Integer index, Integer category);
    
    List<ItemPhotoVO> search(Integer index, String key);

}
