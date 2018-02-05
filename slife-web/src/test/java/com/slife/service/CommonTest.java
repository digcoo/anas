package com.slife.service;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import com.slife.util.DateUtils;

public class CommonTest {
    
    @Test
    public void test(){
    	Date date = new Date();
    	System.out.println(DateUtils.formatDate(date, "yyyy-MM-dd HH:mm"));
    	System.out.println(UUID.nameUUIDFromBytes("XzLyPrPtF2018".getBytes()).toString().replace("-", ""));
    }
}
