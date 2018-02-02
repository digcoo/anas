package com.slife.aliyun;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.util.DateUtils;
import com.slife.util.MD5Tool;
import com.slife.util.StringUtils;


/**
 * Created by cq on 17-5-22.
 */
@Component
public class OSSMultipartManager {

    @Resource
    private OSSClient ossClient;

    @Value("${aliyun.oss.bucket}")
    private String bucketName;
    
    public String uploadImages(MultipartFile uploadFile, String bucketName) throws Exception {
        String fileName;
        InputStream inputStream = uploadFile.getInputStream();
        String date = DateUtils.formatDate(new Date(), "yyyyMMddHH");
        String remoteFileName = date + File.separator + getObjectName(uploadFile.getOriginalFilename());
        fileName = uploadSmallFile(inputStream, uploadFile.getSize(), remoteFileName, bucketName);
        return fileName;
    }

    //小于5M的文件专用上传
    public String uploadSmallFile(InputStream inputStream, long fileSize, String remoteFileName, String bucketName) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(fileSize);
        if (remoteFileName.endsWith("xml")) {
            objectMetadata.setContentType("text/xml");
        } else if (remoteFileName.endsWith("html")) {
            objectMetadata.setContentType("text/html");
        } else if (remoteFileName.endsWith("jpg")) {
            objectMetadata.setContentType("image/jpeg");
        } else if (remoteFileName.endsWith("png")) {
            objectMetadata.setContentType("image/png");
        }
        /** 判断是否使用默认bucketName **/
        if (StringUtils.isEmpty(bucketName)) {
            bucketName = this.bucketName;
        } else if (!ossClient.doesBucketExist(bucketName)) {
            throw new SlifeException(HttpCodeEnum.BUCKET_NAME_NOT_FOUND);
        }
        PutObjectResult result = ossClient.putObject(bucketName, remoteFileName, inputStream, objectMetadata);
        return remoteFileName;
    }

    public Bucket createBucketName(String bucketName) {
        if (ossClient.doesBucketExist(bucketName)) {
            throw new SlifeException(HttpCodeEnum.BUCKET_NAME_EXIST);
        }
        Bucket bucket = ossClient.createBucket(bucketName);
        if(bucket != null){
            ossClient.setBucketAcl(bucket.getName(), CannedAccessControlList.PublicRead);
        }
        return bucket;
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
