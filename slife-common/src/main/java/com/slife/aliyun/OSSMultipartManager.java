package com.slife.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.slife.util.MD5Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;


/**
 * Created by cq on 17-5-22.
 */
@Component
public class OSSMultipartManager {

    @Resource
    private OSSClient ossClient ;

    @Value("${aliyun.oss.bucket}")
    private String bucketName;


    public String uploadImages(MultipartFile uploadFile) throws Exception{
        String fileName;
        InputStream inputStream=uploadFile.getInputStream();
        String remoteFileName = getObjectName(uploadFile.getOriginalFilename());
        fileName = uploadSmallFile(inputStream, uploadFile.getSize(), remoteFileName);
        return fileName ;
    }

    //小于5M的文件专用上传
    public String uploadSmallFile(InputStream inputStream, long fileSize, String remoteFileName){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(fileSize);
        if(remoteFileName.endsWith("xml")){
            objectMetadata.setContentType("text/xml");
        }else if(remoteFileName.endsWith("html")){
            objectMetadata.setContentType("text/html");
        }else if(remoteFileName.endsWith("jpg")){
            objectMetadata.setContentType("image/jpeg");
        }else if(remoteFileName.endsWith("png")){
            objectMetadata.setContentType("image/png");
        }
        PutObjectResult result = ossClient.putObject(bucketName,remoteFileName,inputStream,objectMetadata);
        return remoteFileName;
    }

    /**
     * 获取对对象名称
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
        String str = MD5Tool.MD5(name+calendar, "utf-8") + suffix;
        return str;
    }
}
