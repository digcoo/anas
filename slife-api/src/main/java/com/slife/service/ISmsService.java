package com.slife.service;

import java.util.Map;

public interface ISmsService {

     public static  String regSmsTemplateId = "";

     boolean sendSms(String templateId,String to,Map<String,String> params);
}
