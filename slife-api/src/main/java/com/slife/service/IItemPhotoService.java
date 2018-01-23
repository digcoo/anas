package com.slife.service;

import java.util.List;

import com.slife.vo.ItemPhotoVO;

/**
 * Created by duyp on 18-1-23.
 */
public interface IItemPhotoService {
	
	List<ItemPhotoVO> findIndexs();
	
    List<ItemPhotoVO> findPageByCategory(Integer index, String category);
    
    List<ItemPhotoVO> search(Integer index, String key);

}
