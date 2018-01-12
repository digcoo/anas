package com.slife.mapper;


import com.slife.model.DigcooAnasShopAdSpread;

public interface DigcooAnasShopAdSpreadMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DigcooAnasShopAdSpread record);

    DigcooAnasShopAdSpread selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DigcooAnasShopAdSpread record);
}