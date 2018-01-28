package com.slife.service;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class CommonTest {
    
    @Test
    public void test(){
    	String tes = "[{\"url\":\"https://img.alicdn.com/bao/uploaded/i2/1910146537/TB1c9k8mJrJ8KJjSspaXXXuKpXa_!!0-item_pic.jpg_160x160.jpg\"},{\"url\":\"https://img.alicdn.com/bao/uploaded/i1/1910146537/TB1RUXmc8LN8KJjSZFpXXbZaVXa_!!0-item_pic.jpg_160x160.jpg\"},{\"url\":\"https://img.alicdn.com/bao/uploaded/i1/1910146537/TB16EIQh_vI8KJjSspjXXcgjXXa_!!0-item_pic.jpg_160x160.jpg\"},{\"url\":\"https://img.alicdn.com/imgextra/i4/1910146537/TB2jRzpdBDH8KJjSszcXXbDTFXa_!!1910146537.jpg_430x430q90.jpg\"},{\"$ref\":\"$[3]\"}]";
    	System.out.println(JSON.parseArray(tes).size());
    }
}
