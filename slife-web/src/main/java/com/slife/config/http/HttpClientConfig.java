package com.slife.config.http;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cq on 17-1-11.
 */
@Configuration
public class HttpClientConfig {
    @Value("${http.max.connection}")
    private int maxTotal;  //最大连接数

    @Value("${http.max.perRoute}")
    private int defaultMaxPerRoute; //每个主机默认并发

    @Bean
    public CloseableHttpClient closeableHttpClient(){
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(httpConnectionConfig())
                .build();
        /*CloseableHttpClient httpClient = HttpClients.createDefault();//如果不采用连接池就是这种方式获取连接*/
        return httpClient;
    }


    @Bean(name = "httpConnectionConfig")
    public PoolingHttpClientConnectionManager httpConnectionConfig(){
        // 配置连接池管理器
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry());
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        httpClientConnectionManager.setMaxTotal(maxTotal);
        return httpClientConnectionManager;
    }

    @Bean
    public Registry<ConnectionSocketFactory> socketFactoryRegistry(){
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        return socketFactoryRegistry;
    }
}
