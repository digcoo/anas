package com.slife.api.controller;

import com.aliyun.oss.model.Bucket;
import com.slife.aliyun.OSSMultipartManager;
import com.slife.base.entity.ReturnDTO;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.util.ReturnDTOUtil;
import com.slife.vo.UploadFileVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.function.Predicate;

/**
 * Created by cq on 17-5-21.
 */
@RequestMapping("/api/upload/")
@Controller
@Api(description = "图片操作相关接口")
public class UploadFileController {

    @Value("${aliyun.oss.domain}")
    private String ossDomain;
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Resource
    private OSSMultipartManager ossMultipartManager;

    private long fileSizeFlag = 5 * 1024 * 1024; //用来区分上传文件为大文件还是小文件


    @ResponseBody
    @ApiOperation(value = "上传图片接口", notes = "用户上传文件,返回key", response = String.class)
//    @ApiImplicitParam(name = "file", paramType = "body", dataType = "file", required = true)
    @PostMapping(value = "image", produces = "application/json", consumes = "multipart/form-data")
    //consumes = "multipart/form-data"
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = String.class)})
    public ReturnDTO<UploadFileVO> image(@RequestPart("uploadFile") MultipartFile uploadFile) {
        checkParam.test(uploadFile);
        String imgUrl = "";
        try {
            imgUrl = ossMultipartManager.uploadImages(uploadFile, null);
        } catch (Exception e) {
            return ReturnDTOUtil.error();
        }
        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setPath(imgUrl);
        uploadFileVO.setDomain(ossDomain);
        return ReturnDTOUtil.success(uploadFileVO);
    }

    @ResponseBody
    @ApiOperation(value = "上传图片接口到指定的bucket中", notes = "用户上传文件,返回key", response = String.class)
    @PostMapping(value = "upload/{bucketName}", produces = "application/json", consumes = "multipart/form-data")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = String.class)})
    public ReturnDTO<UploadFileVO> uploadFileToBucket(@PathVariable("bucketName") String bucketName, @RequestPart("uploadFile") MultipartFile uploadFile) {
        checkParam.test(uploadFile);
        String imgUrl = "";
        try {
            imgUrl = ossMultipartManager.uploadImages(uploadFile, bucketName);
        } catch (Exception e) {
            return ReturnDTOUtil.error();
        }
        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setPath(imgUrl);
        uploadFileVO.setDomain(getOssDomain(bucketName));
        return ReturnDTOUtil.success(uploadFileVO);
    }
    public String getOssDomain(String bucketName){
        StringBuilder sb = new StringBuilder("http://");
        sb.append(bucketName);
        sb.append(".");
        String domain = endpoint.substring(7,endpoint.length());
        sb.append(domain);
        return sb.toString();
    }

    @ResponseBody
    @ApiOperation(value = "创建bucket", notes = "1.一个用户最多可创建 10 个Bucket,2.只能包括小写字母，数字和短横线,3.必须以小写字母或者数字开头,4.长度必须在 3-63 字节之间"
            , response = String.class)
    @GetMapping(value = "create/bucket", produces = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = String.class)})
    public ReturnDTO<String> createBucketName(@RequestParam("bucketName") String bucketName) {
        try {
            Bucket bucket = ossMultipartManager.createBucketName(bucketName);
            return ReturnDTOUtil.success(bucket.getName());
        } catch (Exception e) {
            return ReturnDTOUtil.error();
        }
    }

    Predicate<MultipartFile> checkParam = (uploadFile) -> {
        if (uploadFile == null) {
            throw new SlifeException(HttpCodeEnum.UPLOAD_FILE_NOT_FOUND);
        }
        if (uploadFile.isEmpty()) {
            throw new SlifeException(HttpCodeEnum.UPLOAD_FILE_NOT_FOUND);
        }
        long fileSize = uploadFile.getSize();
        if (fileSize > fileSizeFlag) {
            throw new SlifeException(HttpCodeEnum.OVER_MAX_SIZE);
        }
        return true;
    };
}
