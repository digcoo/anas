package com.slife.service;

import com.google.common.collect.Lists;
import com.slife.WebApplicationTests;
import com.slife.entity.Shop;
import com.slife.entity.ShopAd;
import com.slife.service.impl.ShopAdService;
import com.slife.service.impl.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tod.tao on 2018/1/23.
 */
public class ShopAdServiceTest extends WebApplicationTests{
    @Autowired
    private ShopAdService shopAdService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CacheManager cacheManager;
    @Test
    public void selectAdsByGeohash() throws Exception {
        List<ShopAd> shopAdList = shopAdService.selectAdsByGeohash(0,"wtw", Arrays.asList(53l));
        assertThat(shopAdList).isNotEmpty();
    }

    @Test
    public void selectAdsByGeohashAndName() throws Exception {
        List<ShopAd> shopAdList = shopAdService.selectAdsByGeohashAndName("wtw2","明");
        assertThat(shopAdList).isNotEmpty();
}

    @Test
    public void selectAdsByShopId() throws Exception {
        List<ShopAd> shopAdList = shopAdService.selectAdsByShopId(0,2l);
        assertThat(shopAdList).isNotEmpty();
    }

}