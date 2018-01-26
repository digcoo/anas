package com.slife.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import com.alibaba.fastjson.JSON;
import com.slife.WebApplicationTests;
import com.slife.base.entity.ReturnDTO;
import com.slife.entity.enums.AdType;
import com.slife.service.impl.ShopAdService;
import com.slife.service.impl.ShopService;
import com.slife.vo.AdAddVO;
import com.slife.vo.AdUpdateVO;

/**
 * Created by duyp on 2018/1/25.
 */
public class ShopServiceTest extends WebApplicationTests{
    @Autowired
    private ShopAdService shopAdService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CacheManager cacheManager;
    @Test
    public void addShopAd(){
    	Calendar calendar = Calendar.getInstance();
    	AdAddVO shopAd = new AdAddVO();
    	shopAd.setShopId(955727017696542738l);
    	shopAd.setType((byte)AdType.DISCOUNT.getType());	//打折促销
    	shopAd.setStartTime(calendar.getTime());
    	
    	calendar.add(Calendar.DAY_OF_MONTH, 6);
    	
    	shopAd.setEndTime(calendar.getTime());
    	
    	shopAd.setTitle("[神秘巨星 Secret Superstar]在莘庄龙之梦海上国际影城首映式，今日晚间20点，全场所有场次均8折，附近的您快来抢票吧");
    	
    	List<Map<String, String>> items = new ArrayList<Map<String, String>>();
    	Map<String, String> map1 = new HashMap<String, String>();
    	map1.put("url", "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2508925590.webp");
    	items.add(map1);
    	
    	Map<String, String> map2 = new HashMap<String, String>();
    	map2.put("url", "https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1494080264.12.webp");
    	items.add(map2);
    	
    	Map<String, String> map3 = new HashMap<String, String>();
    	map3.put("url", "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13628.webp");
    	items.add(map3);
    	
    	shopAd.setItems(JSON.toJSONString(items));
    	
        ReturnDTO retDto = shopAdService.addShopAd(shopAd);
        //assertThat(retDto);
    }
    
    @Test
    public void updateShopAd(){
    	Calendar calendar = Calendar.getInstance();
    	AdUpdateVO shopAd = new AdUpdateVO();
    	shopAd.setAdId(956720775011205122l);
    	shopAd.setType((byte)AdType.OTHER.getType());	//打折促销
    	shopAd.setStartTime(calendar.getTime());
    	
    	calendar.add(Calendar.DAY_OF_MONTH, 6);
    	
    	shopAd.setEndTime(calendar.getTime());
    	
    	shopAd.setTitle("[神秘巨星 Secret Superstar]在莘庄龙之梦海上国际影城首映式，今日晚间20点，全场所有场次均8折，附近的您快来抢票吧");
    	
    	List<Map<String, String>> items = new ArrayList<Map<String, String>>();
    	Map<String, String> map1 = new HashMap<String, String>();
    	map1.put("url", "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2508925590.webp");
    	items.add(map1);
    	
    	Map<String, String> map2 = new HashMap<String, String>();
    	map2.put("url", "https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1494080264.12.webp");
    	items.add(map2);
    	
    	Map<String, String> map3 = new HashMap<String, String>();
    	map3.put("url", "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13628.webp");
    	items.add(map3);
    	
    	shopAd.setItems(JSON.toJSONString(items));
    	
        ReturnDTO retDto = shopAdService.updateShopAd(shopAd);
        //assertThat(retDto);
    }

    @Test
    public void publicShopAd(){
    	ReturnDTO retDto = shopAdService.publishShopAd(956714007690412033l);
    	System.out.println(JSON.toJSONString(retDto));
    }
    

    @Test
    public void offShopAd(){
    	ReturnDTO retDto = shopAdService.offShopAd(956714007690412033l);
    	System.out.println(JSON.toJSONString(retDto));
    }

    @Test
    public void delShopAd(){
    	ReturnDTO retDto = shopAdService.delShopAd(956714007690412033l);
    	System.out.println(JSON.toJSONString(retDto));
    }

    @Test
    public void listForShop(){
    	ReturnDTO retDto = shopAdService.listForShop(955727017696542738l, 0);
    	System.out.println(JSON.toJSONString(retDto));
    }
}