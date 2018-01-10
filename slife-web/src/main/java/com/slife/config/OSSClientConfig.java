package com.slife.config;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cq on 17-5-22.
 */
@Configuration
public class OSSClientConfig {

    @Value("${oss.bucket.name}")
    private String bucketName;
    @Value("${oss.endpoint}")
    private String ossEndpoint;
    @Value("${oss.access.key.id}")
    private String accessKeyId;
    @Value("${oss.access.key.secret}")
    private String accessKeySecret;

    @Bean(name = "ossClient")
    public OSSClient ossClient(){
        // 可以使用ClientConfiguration对象设置代理服务器、最大重试次数等参数。
        //创建OSS连接
        ClientConfiguration config = new ClientConfiguration();
        OSSClient client = new OSSClient(ossEndpoint, accessKeyId, accessKeySecret, config);
        //创建bucket
        client.createBucket(bucketName);
        //设置bucket的访问权限， public-read-write权限
        client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        return client ;
    }

}
