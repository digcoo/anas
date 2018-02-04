package com.slife.baidu;

import java.net.URLEncoder;

/**
* 人脸对比
*/
public class FaceMatchService {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String match() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v2/match";
        try {
            // 本地文件路径
            String filePath = "/Users/junifer/Documents/1.jpeg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String filePath2 = "/Users/junifer/Documents/2.jpeg";
            byte[] imgData2 = FileUtil.readFileByBytes(filePath2);
            String imgStr2 = Base64Util.encode(imgData2);
            String imgParam2 = URLEncoder.encode(imgStr2, "UTF-8");

            String param = "images=" + imgParam + "," + imgParam2;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
//            String accessToken = "[调用鉴权接口获取的token]";
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String match(String url1, String url2) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v2/match";
        try {
        	
        	System.out.println("img1 = " + url1);
        	System.out.println("img2 = " + url2);
        	
            // 本地文件路径
            byte[] imgData = FileUtil.readUrlByBytes(url1);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            byte[] imgData2 = FileUtil.readUrlByBytes(url2);
            String imgStr2 = Base64Util.encode(imgData2);
            String imgParam2 = URLEncoder.encode(imgStr2, "UTF-8");

            String param = "images=" + imgParam + "," + imgParam2;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
//            String accessToken = "[调用鉴权接口获取的token]";
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        FaceMatchService.match();
    	String img1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517386890048&di=7070f52cc20b569bccb0978debce7ecc&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fbaike%2Fpic%2Fitem%2F9d82d158ccbf6c81937c8e0dbc3eb13532fa408f.jpg";
    	String img2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517386890048&di=7070f52cc20b569bccb0978debce7ecc&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fbaike%2Fpic%2Fitem%2F9d82d158ccbf6c81937c8e0dbc3eb13532fa408f.jpg";
        FaceMatchService.match(img1, img2);
    }
}