package com.slife.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.slife.WebApplicationTests;
import com.slife.service.impl.ItemPhotoService;
import com.slife.vo.ItemPhotoVO;

/**
 * Created by duyp on 2018/1/25.
 */
public class ItemPhotoServiceTest extends WebApplicationTests {

	@Autowired
	private ItemPhotoService itemPhotoService;

	@Test
	public void findIndexs() throws Exception {
		Map<Integer, List<ItemPhotoVO>> indexs = itemPhotoService.findIndexs();
		assertThat(indexs).isNotEmpty();
	}

	@Test
	public void findPageByCategory() throws Exception {
		List<ItemPhotoVO> pages = itemPhotoService.findPageByCategory(0, 1);
		assertThat(pages).isNotEmpty();
	}

	@Test
	public void search() throws Exception {
		List<ItemPhotoVO> pages = itemPhotoService.search(0, "饼干");
		assertThat(pages).isNotEmpty();
	}
}