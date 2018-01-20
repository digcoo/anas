package com.slife.dao;

import com.slife.base.dao.CrudDao;
import com.slife.entity.Business;
import com.slife.entity.ShopAd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessDao extends CrudDao<Business> {


     List<Business> selectAll();


}
