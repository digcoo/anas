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
import com.slife.entity.enums.ShopType;
import com.slife.service.impl.ShopAdService;
import com.slife.service.impl.ShopService;
import com.slife.vo.AdAddVO;
import com.slife.vo.AdUpdateVO;
import com.slife.vo.ShopBaseVO;
import com.slife.vo.ShopMallVO;
import com.slife.vo.ShopVO;

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
    public void saveShopBase(){
    	ShopBaseVO shopBaseVO = new ShopBaseVO();
    	shopBaseVO.setAddr("沪闵路6888号");
    	shopBaseVO.setBusinessId(63l);
    	shopBaseVO.setGeohash("wtw31234");
    	shopBaseVO.setLat(31.0101);
    	shopBaseVO.setLng(121.2121);
    	shopBaseVO.setOpenMobile(true);
    	shopBaseVO.setPhone("13764611339");
    	shopBaseVO.setPhoneCode("phoneCode");
    	shopBaseVO.setPosition("店长");
    	shopBaseVO.setShopName("一点点(莘庄龙之梦店)");
    	shopBaseVO.setUserId(1l);
    	ReturnDTO<ShopBaseVO> saveShopBase = shopService.saveShopBase(shopBaseVO);
    	System.out.println(JSON.toJSONString(saveShopBase));
    }
    
    @Test
    public void saveShop(){
    	ShopMallVO shopMallVO = new ShopMallVO();
    	shopMallVO.setUserId(1l);
    	shopMallVO.setAgentIdentifyCard("{\"ID_A\":\"d1ba576e3f9dcd331c703338ab87803b.jpeg\", \"ID_B\":\"26a98d9660c3bb6cc7272a7f2d6e9728.jpeg\"}");
    	shopMallVO.setAgentPortrait("d1ba576e3f9dcd331c703338ab87803b.jpeg");
    	shopMallVO.setShopType("" + ShopType.MALL.getCode());
    	shopMallVO.setMallId(1l);
    	shopMallVO.setFloor("B3F");
    	shopMallVO.setRoom("302");
    	ReturnDTO saveShop = shopService.saveShop(shopMallVO);
    	System.out.println(JSON.toJSONString(saveShop));
    }
    
    @Test
    public void getShopInfo(){
    	ReturnDTO<ShopVO> shopInfo = shopService.getShopInfo(2l);
    	System.out.println(JSON.toJSONString(shopInfo));
    }
    
    @Test
    public void updatePicture(){
    	int updatePicture = shopService.updatePicture(2l, "[\"d1ba576e3f9dcd331c703338ab87803b.jpeg\", \"26a98d9660c3bb6cc7272a7f2d6e9728.jpeg\"]");
    	System.out.println(updatePicture);
    }
    
    
    @Test
    public void addShopAd(){
    	Calendar calendar = Calendar.getInstance();
    	AdAddVO shopAd = new AdAddVO();
    	shopAd.setShopId(2l);
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
    public void publishShopAd(){
    	ReturnDTO retDto = shopAdService.publishShopAd(1l);
    	System.out.println(JSON.toJSONString(retDto));
    }
    

    @Test
    public void offShopAd(){
    	ReturnDTO retDto = shopAdService.offShopAd(1l);
    	System.out.println(JSON.toJSONString(retDto));
    }

    @Test
    public void delShopAd(){
    	ReturnDTO retDto = shopAdService.delShopAd(1l);
    	System.out.println(JSON.toJSONString(retDto));
    }

    @Test
    public void listAdsForShop(){
    	ReturnDTO retDto = shopAdService.listForShop(2l, 0);
    	System.out.println(JSON.toJSONString(retDto));
    }
    
    @Test
    public void upShopAd(){
    	ReturnDTO retDto = shopAdService.upShopAd(956714007690412033l);
    	System.out.println(JSON.toJSONString(retDto));
    }

}