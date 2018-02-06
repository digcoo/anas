package com.slife.wxapi.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.slife.httpclient.HttpSendManager;
import com.slife.utils.Signature;
import com.slife.utils.XMLParser;
import com.slife.wxapi.request.WxPayReq;
import com.slife.wxapi.response.WxPayRsp;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


@Component
public class WxPayApi {

    private String wxPayUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    private String  notifyUrl = "http://www.digcoo.com/api/shop/wxPayCallBack";

    private String appId = "wx70ce7ab503f9c688";

    private String mchId = "1497413162";


    private String tradeType = "JSAPI";

    private String key = "b86fe0a53b4c34a0a424f8c6879a848b";

    private String clientIp = "47.100.53.10";

    @Resource
    private HttpSendManager httpSendManager;


    public WxPayRsp preparePay(WxPayReq req)  {

        try{
            String nonceStr = RandomStringUtils.randomAlphabetic(16);
            req.setNonce_str(nonceStr);
            req.setAppid(appId);
            req.setMch_id(mchId);
            req.setTrade_type(tradeType);
            req.setNotify_url(notifyUrl);
            req.setSpbill_create_ip(clientIp);

            String sign = Signature.getSign(req, key);
            req.setSign(sign);

            XStream xStreamForRequestPostData = new XStream(new DomDriver(
                    "UTF-8", new XmlFriendlyNameCoder("-_", "_")));

            // 将要提交给API的数据对象转换成XML格式数据Post给API
            String postDataXML = xStreamForRequestPostData.toXML(req);
            String payRsp = httpSendManager.doPost(wxPayUrl,postDataXML);
            JSONObject json = XMLParser.getMapFromXML(payRsp);

            WxPayRsp wxPayRsp =   JSON.parseObject(JSON.toJSONString(json),WxPayRsp.class);
            wxPayRsp.setResponseBody(payRsp);
            return  wxPayRsp;

        }catch (Exception ex){
            return null;
        }




    }


}
