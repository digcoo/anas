package com.slife.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

/**
 * Created by vip on 2017/5/2.
 */
public class ResourceUtil {
    public static String readUrlFile(String url) throws IOException{
        String localUrl = StringUtils.EMPTY;
        Resource res = new UrlResource(url);
        //ins = res.getInputStream();
        String name = res.getFilename();
        //byte[] gifByte= IOUtils.toByteArray(ins);
        File file = new File("/Users/vip/Downloads/lipImage");
        if(!file.exists() && !file.isDirectory()){
            file.mkdir();
        }
        localUrl = "/Users/vip/Downloads/lipImage/"+ UUID.randomUUID()+"."+ StringUtils.substringAfterLast(name,".");
        //org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(localUrl), gifByte);
        org.apache.commons.io.FileUtils.copyURLToFile(new URL(url),new File(localUrl));
        return localUrl;
    }
    public static String readUrlFileWithMD5(String url) throws IOException{
        String localUrl = StringUtils.EMPTY;
            File dire = new File("/Users/vip/Downloads/lipImage");
            if(!dire.exists() && !dire.isDirectory()){
                dire.mkdir();
            }
            localUrl = "/Users/vip/Downloads/lipImage/"+ DigestUtils.md5Hex(url)+".jpg";
            File file = new File(localUrl);
            if(file.exists() && file.isFile()){
               return localUrl;
            }else{
                org.apache.commons.io.FileUtils.copyURLToFile(new URL(url),new File(localUrl));
            }
        return localUrl;
    }

    public static String generateLocalUrl() throws IOException{
        String localUrl = StringUtils.EMPTY;
        File dire = new File("/Users/vip/Downloads/lipImage");
        if(!dire.exists() && !dire.isDirectory()){
            dire.mkdir();
        }
        localUrl = "/Users/vip/Downloads/lipImage/"+ UUID.randomUUID()+".jpg";
        return localUrl;
    }
}
