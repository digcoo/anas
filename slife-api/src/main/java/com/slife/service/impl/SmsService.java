package com.slife.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.slife.service.ISmsService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SmsService implements ISmsService {

    private  static final String accessKeyId = "LTAIhTxMgJWWCmqs";
    private static final String accessKeySecret = "Vz6nscR0JP8Va2aQDVHVv7upPybywQ";

    //产品名称:云通信短信API产品,开发者无需替换
    private  static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";


    @Override
    public boolean sendSms(String templateId, String to, Map<String, String> params) {

        try{
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            SendSmsRequest request = new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);

            request.setPhoneNumbers(to);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName("大喇叭");
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateId);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(JSON.toJSONString(params));

            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                return  true;
            }

        }catch (Throwable th){

        }
        return  false;

    }



}
