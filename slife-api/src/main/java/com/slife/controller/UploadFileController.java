package com.slife.controller;

import com.alibaba.fastjson.JSON;
import com.slife.model.entity.Result;
import com.slife.oss.OSSMultipartManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by cq on 17-5-21.
 */
@RequestMapping("/upload/")
@Controller
public class UploadFileController {

    @Resource
    private OSSMultipartManager ossMultipartManager;

    @Value("${oss.url}")
    private String ossUrl;

    private long fileSizeFlag = 5 * 1024 * 1024; //用来区分上传文件为大文件还是小文件

    @RequestMapping(value = "image", consumes = "multipart/form-data")
    @ResponseBody
    public String image(@RequestParam("uploadImg") MultipartFile uploadImg) {
        Result<String> result = new Result<>();
        if (uploadImg.isEmpty()) {
            result.setDesc("文件为空");
            result.setSuccess(false);
            return JSON.toJSONString(result);
        }
        long fileSize = uploadImg.getSize();
        if (fileSize > fileSizeFlag) {
            result.setDesc("上传最大不能超过5M!");
            result.setSuccess(false);
            return JSON.toJSONString(result);
        }
        try {
            String imgUrl = ossMultipartManager.uploadImages(uploadImg);
            result.setT(imgUrl);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setDesc("上传失败!");
            result.setSuccess(false);
        }
        return JSON.toJSONString(result);
    }

    @RequestMapping("test")
    @ResponseBody
    public String getFileName(@RequestParam("uploadImg") MultipartFile uploadImg) {
        try {
            String imgUrl = ossMultipartManager.uploadImages(uploadImg);
            if (StringUtils.isEmpty(imgUrl)) {
                throw new Exception("上传tfs写文件失败");
            }
            String tsfImgPath = ossUrl + imgUrl;
            return tsfImgPath;
        } catch (Exception e) {
            String errorMsg = "文件上传失败";
            return errorMsg;
        }
    }
}
