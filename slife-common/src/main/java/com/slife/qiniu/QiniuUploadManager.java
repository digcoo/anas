package com.slife.qiniu;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.util.DateUtils;
import com.slife.util.MD5Tool;
import com.slife.util.StringUtils;


/**
 * Created by duyp on 18-3-2.
 */
@Component
public class QiniuUploadManager {

    @Value("${qiniu.oss.bucket}")
    private String bucketName;
    
    @Value("${qiniu.oss.accessKey}")
    private String accessKey;

    @Value("${qiniu.oss.secretKey}")
    private String secretKey;
    
    public String upload(MultipartFile uploadFile) throws Exception {
    	
		Configuration cfg = new Configuration(Zone.zone0());
    	
    	UploadManager uploadManager = new UploadManager(cfg);
		
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = DateUtils.formatDate(new Date(), "yyyyMMddHH") + File.separator + getObjectName(uploadFile.getOriginalFilename());
		
	    Auth auth = Auth.create(accessKey, secretKey);
	    
	    String upToken = auth.uploadToken(bucketName);
	    
        Response response = uploadManager.put(uploadFile.getInputStream(), key, upToken,null, null);
        
//        //解析上传成功的结果
//        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        
        return key;
    	        
    }

    /**
     * 获取对对象名称
     *
     * @param fileName
     * @return
     */
    public String getObjectName(String fileName) {
        String suffix = "";
        String name = "";
        long calendar = System.currentTimeMillis();
        if (fileName != null && !"".equals(fileName)) {
            name = fileName.substring(0, fileName.lastIndexOf("."));
            suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        String str = MD5Tool.MD5(name + calendar, "utf-8") + suffix;
        return str;
    }
}
