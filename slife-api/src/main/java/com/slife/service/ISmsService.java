package com.slife.service;

import java.util.Map;

public interface ISmsService {

     public static  String regSmsTemplateId = "SMS_122292246";

     boolean sendSms(String templateId,String to,Map<String,String> params);
}
