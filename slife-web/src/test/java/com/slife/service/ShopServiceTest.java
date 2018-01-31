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
import com.slife.entity.Shop;
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
    	
    	shopAd.setTitle("刚进的新鲜水果，打折促销，快过来看一看，到店即7折");
    	
    	List<Map<String, String>> items = new ArrayList<Map<String, String>>();
    	Map<String, String> map1 = new HashMap<String, String>();
    	map1.put("url", "https://img.alicdn.com/bao/uploaded/i2/1910146537/TB1c9k8mJrJ8KJjSspaXXXuKpXa_!!0-item_pic.jpg_160x160.jpg");
    	items.add(map1);
    	
    	Map<String, String> map2 = new HashMap<String, String>();
    	map2.put("url", "https://img.alicdn.com/bao/uploaded/i1/1910146537/TB1RUXmc8LN8KJjSZFpXXbZaVXa_!!0-item_pic.jpg_160x160.jpg");
    	items.add(map2);
    	
    	Map<String, String> map3 = new HashMap<String, String>();
    	map3.put("url", "https://img.alicdn.com/bao/uploaded/i1/1910146537/TB16EIQh_vI8KJjSspjXXcgjXXa_!!0-item_pic.jpg_160x160.jpg");
    	items.add(map3);
    	
    	Map<String, String> map4 = new HashMap<String, String>();
    	map4.put("url", "https://img.alicdn.com/imgextra/i2/1910146537/TB2dYNgdAfb_uJjSsrbXXb6bVXa_!!1910146537.jpg_160x160.jpg");
    	items.add(map4);
    	
    	Map<String, String> map5 = new HashMap<String, String>();
    	map4.put("url", "https://img.alicdn.com/imgextra/i4/1910146537/TB2jRzpdBDH8KJjSszcXXbDTFXa_!!1910146537.jpg_430x430q90.jpg");
    	items.add(map4);
    	
    	
    	
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
    	ReturnDTO retDto = shopAdService.publishShopAd(956914448743247874l);
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
    
    @Test
    public void upShopAd(){
    	ReturnDTO retDto = shopAdService.upShopAd(956714007690412033l);
    	System.out.println(JSON.toJSONString(retDto));
    }
    
    @Test
    public void shopAdd(){
    	Shop shop = new Shop();
    	shop.setName("dyp-test");
    	shop.setUserId(99l);
    	shop.setTel("111");
    	shop.setAddr("111");
    	shop.setOpenMobile((byte)1);
    	shop.setBusinessId(1l);
    	shop.setLat(31.123);
    	shop.setLng(121.123);
    	shop.setGeohash("ttt");
    	shop.setFollowNum(0);
//    	shop.setAuditState(0);
    	shop.setAgentPosition("14");
    	shop.setStatus((byte)1);
    	shopService.insert(shop);
    }

}