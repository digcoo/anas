package com.slife.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slife.base.dao.CrudDao;
import com.slife.entity.Mall;

/**
 * @author duyp
 * @date 2018/1/25
 * Describe: merchant duyp
 */
public interface MallDao extends CrudDao<Mall> {

	List<Mall> findPageByKey(@Param("key")String key);
}
