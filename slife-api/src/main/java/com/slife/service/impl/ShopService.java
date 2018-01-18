package com.slife.service.impl;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.ShopDao;
import com.slife.entity.Shop;
import com.slife.service.IShopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tod
 * @date 2018/1/2
 * <p>
 * Email will_tao@126.com
 * <p>
 * Describe: merchant service
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ShopService extends BaseService<ShopDao, Shop> implements IShopService {

}
