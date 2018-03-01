package com.slife.collect;

import java.io.IOException;
import java.text.MessageFormat;

import com.slife.util.ResourceUtil;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.slife.baidu.FileUtil;
import com.slife.httpclient.HttpUtils;

public class VipSpiderTest {

	@Test
	public void collect() throws IOException{
		String base_url = "http://category.vip.com/ajax/mapi.php?service=product_info&callback=categoryMerchandiseInfo1&productIds={0}&functions=brandShowName,surprisePrice,pcExtra&warehouse=VIP_SH&mobile_platform=1&app_name=shop_pc&app_";
		String productIds = FileUtil.readFileAsString("/Users/vip/Downloads/lipImage/productIds.txt");
		String[] split = productIds.split(",");
		int page = (split.length-1)/50 + 1;
		String url = null;
		for(int i = 0; i < page; i++){
			url = MessageFormat.format(base_url, getByIndex(split, i * 50));
			String content = HttpUtils.getInstance().get(url);
			String jsonStr = content.substring("categoryMerchandiseInfo1(".length(), content.length() - 1);
			System.out.println(jsonStr);
			JSONArray jsonArray = JSON.parseObject(jsonStr).getJSONObject("data").getJSONArray("products");
			for (int j = 0; j < jsonArray.size(); j++) {
				String image_url = jsonArray.getJSONObject(j).getString("smallImage");
				ResourceUtil.readUrlFile(image_url);
				//System.out.println(image_url);
			}
		}
		
	}
	
	public String getByIndex(String[] split, int index) {
		StringBuffer sb = new StringBuffer();
		for (int i = index; i < split.length && i < (index + 50); i ++) {
			sb.append(",").append(split[i]);
		}
		return sb.toString().substring(1);
	}
}
