package com.slife.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.slife.WebApplicationTests;
import com.slife.service.impl.MallService;
import com.slife.vo.MallVO;

/**
 * Created by duyp on 2018/1/25.
 */
public class MallServiceTest extends WebApplicationTests {

	@Autowired
	private MallService mallService;

	@Test
	public void findListBykey() throws Exception {
		List<MallVO> list = mallService.findListBykey("七宝");
		assertThat(list).isNotEmpty();
	}

}