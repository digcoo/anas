package com.slife.dao;

import java.util.List;

import com.slife.base.dao.CrudDao;
import com.slife.entity.ItemPhoto;

public interface ItemPhotoDao extends CrudDao<ItemPhoto> {

	List<ItemPhoto> findIndexs();

	List<ItemPhoto> findPageByCategory(Integer index, String category);

	List<ItemPhoto> search(Integer index, String key);

}
