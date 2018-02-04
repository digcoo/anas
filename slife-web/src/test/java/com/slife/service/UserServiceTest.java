package com.slife.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import com.alibaba.fastjson.JSON;
import com.slife.WebApplicationTests;
import com.slife.entity.User;
import com.slife.entity.enums.Gender;
import com.slife.entity.enums.UserType;
import com.slife.vo.SessionKeyVO;

/**
 * Created by tod.tao on 2018/1/23.
 */
public class UserServiceTest extends WebApplicationTests{
    @Autowired
    private UserService userService;
    @Autowired
    private CacheManager cacheManager;

    @Test
    public void addUser() throws Exception {
    	User user = new User();
    	user.setOpenId("open-id-dyp-test");
    	user.setCity("shanghai");
    	user.setCountry("china");
    	user.setGender(Gender.MEN.getIndex());
    	user.setHeadImg("2207c3d816e9801116a6205bdbbb87a6.png");
    	user.setMobile("13764611339");
    	user.setNick("杜一平");
    	user.setProvince("shanghai");
    	user.setType(UserType.COMMON.getCode());
    	user.setFollowTime(new Date());
        Integer ret = userService.addUser(user);
        assertThat(ret).isNotNull();
    }
    
    @Test
    public void getUserByOpenId(){
    	User user = userService.getUserByOpenId("open-id-dyp-test");
    	assertThat(user).isNotNull();
    }
    
    @Test
    public void getSessionKeyWx(){
    	SessionKeyVO sessionKeyWx = userService.getSessionKeyWx("071lgpkj16cFRx0AOokj1eJqkj1lgpkp");
    	System.out.println(JSON.toJSONString(sessionKeyWx));
    	assertThat(sessionKeyWx).isNotNull();
    }

    @Test
    public void editNick(){
    	Integer editUser = userService.editNick(1l, "杜一平修改昵称");
    	assertThat(editUser).isNotNull();
    }

    @Test
    public void editHeadImg(){
    	Integer editUser = userService.editHeadImg(1l, "2207c3d816e9801116a6205bdbbb87a6.png");
    	assertThat(editUser).isNotNull();
    }
}