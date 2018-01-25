package com.slife.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slife.base.dao.CrudDao;
import com.slife.entity.ItemPhoto;

public interface ItemPhotoDao extends CrudDao<ItemPhoto> {

	List<ItemPhoto> findIndexs();

	List<ItemPhoto> findPageByCategory(@Param("index")Integer index, @Param("category")Integer category);

	List<ItemPhoto> search(@Param("index")Integer index, @Param("key")String key);

}
