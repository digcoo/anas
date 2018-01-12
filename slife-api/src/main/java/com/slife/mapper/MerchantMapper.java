package com.slife.mapper;

import com.slife.model.Merchant;

public interface MerchantMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Merchant record);

    Merchant selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Merchant record);
}