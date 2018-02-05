package com.slife.service;

import com.slife.WebApplicationTests;
import com.slife.entity.Shop;
import com.slife.entity.ShopAd;
import com.slife.service.impl.ShopAdService;
import com.slife.service.impl.ShopService;
import com.slife.utils.SlifeRedisTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tod.tao on 2018/1/23.
 */
public class RedisTest extends WebApplicationTests{
    @Autowired
    private SlifeRedisTemplate slifeRedisTemplate;
    @Test
    public void collectOrNot() throws Exception {
        slifeRedisTemplate.collectOrNot(1l,1l);
    }

    @Test
    public void getAllFavorAdIds() throws Exception {
        Set<String> set = slifeRedisTemplate.getAllFavorAdIds(1l);
        assertThat(set).containsExactly("1");
    }

    @Test
    public void followOrNot() throws Exception {
        slifeRedisTemplate.followOrNot(1l,1l);
    }

    @Test
    public void getAllFollowShopIds() throws Exception {
        Set<String> set = slifeRedisTemplate.getAllFollowShopIds(1l);
        assertThat(set).containsExactly("1");
    }

}