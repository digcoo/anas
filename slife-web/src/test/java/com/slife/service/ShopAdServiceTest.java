package com.slife.service;

import com.slife.WebApplicationTests;
import com.slife.entity.Shop;
import com.slife.entity.ShopAd;
import com.slife.service.impl.ShopAdService;
import com.slife.service.impl.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by vip on 2018/1/23.
 */
public class ShopAdServiceTest extends WebApplicationTests{
    @Autowired
    private ShopAdService shopAdService;
    @Autowired
    private ShopService shopService;
    @Test
    public void selectAdsByGeohash() throws Exception {
        List<ShopAd> shopAdList = shopAdService.selectAdsByGeohash(0,"wtw2");
        Assert.notEmpty(shopAdList,"为空");
    }

    @Test
    public void selectAdsByGeohashAndName() throws Exception {
        List<ShopAd> shopAdList = shopAdService.selectAdsByGeohashAndName(0,"wtw2","奶茶");
        Assert.notEmpty(shopAdList,"为空");
    }

    @Test
    public void selectAdsByShopId() throws Exception {
        Shop shop = shopService.selectById(1l);
        Assert.notNull(shop,"店铺不存在");
    }

}