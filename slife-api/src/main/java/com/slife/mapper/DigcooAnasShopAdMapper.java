package com.slife.mapper;

import com.slife.model.DigcooAnasShopAd;

public interface DigcooAnasShopAdMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DigcooAnasShopAd record);

    DigcooAnasShopAd selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DigcooAnasShopAd record);
}