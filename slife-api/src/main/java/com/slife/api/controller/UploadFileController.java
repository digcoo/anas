package com.slife.api.controller;

import com.slife.aliyun.OSSMultipartManager;
import com.slife.base.entity.ReturnDTO;
import com.slife.enums.HttpCodeEnum;
import com.slife.util.ReturnDTOUtil;
import com.slife.vo.IndexVO;
import com.slife.vo.UploadFileVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by cq on 17-5-21.
 */
@RequestMapping("/upload/")
@Controller
@Api(description = "上传文件")
public class UploadFileController {

    @Value("${aliyun.oss.domain}")
    private String ossDomain;
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
        if (uploadFile == null) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UPLOAD_FILE_NOT_FOUND);
        }
        if (uploadFile.isEmpty()) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UPLOAD_FILE_NOT_FOUND);
        }
        long fileSize = uploadFile.getSize();
        if (fileSize > fileSizeFlag) {
            return ReturnDTOUtil.custom(HttpCodeEnum.OVER_MAX_SIZE);
        }
        String imgUrl = "";
        try {
            imgUrl = ossMultipartManager.uploadImages(uploadFile);
        } catch (Exception e) {
            return ReturnDTOUtil.error();
        }
        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setPath(imgUrl);
        uploadFileVO.setDomain(ossDomain);
        ReturnDTO<UploadFileVO> returnDTO = new ReturnDTO(uploadFileVO);
        return returnDTO;
    }
}
