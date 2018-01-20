package com.slife.utils;

/**
 * Created by jg.chen on 2014/9/17.
 */
public class CodeGenUtils {

    private String letters = "qwertyuiopasdfghjklzxcvbnm";

    private String numbers  ="1234567890";

    public static String generateVerifyCode(){
        long code = Math.round(Math.random() * 899999 + 100000);
        return String.valueOf(code);
    }








}
