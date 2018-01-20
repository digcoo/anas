package com.slife.api.controller;

import com.slife.aliyun.OSSMultipartManager;
import com.slife.base.entity.ReturnDTO;
import com.slife.enums.HttpCodeEnum;
import com.slife.util.ReturnDTOUtil;
import com.slife.vo.IndexVO;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(description = "上传文件")
public class UploadFileController {

    @Resource
    private OSSMultipartManager ossMultipartManager;

    private long fileSizeFlag = 5 * 1024 * 1024; //用来区分上传文件为大文件还是小文件


    @ResponseBody
    @ApiOperation(value = "上传图片接口", notes = "用户上传文件,返回key")
    @ApiImplicitParam(name = "uploadImg", paramType = "MultipartFile", dataType = "File", required = true)
    @PostMapping(value = "image", consumes = "multipart/form-data")
    @ApiResponses({@ApiResponse(code = 200,message = "成功",response = String.class)})
    public ReturnDTO<String> image(@RequestParam("uploadImg") MultipartFile uploadImg) {
        if (uploadImg.isEmpty()) {
            return ReturnDTOUtil.custom(HttpCodeEnum.UPLOAD_FILE_NOT_FOUND);
        }
        long fileSize = uploadImg.getSize();
        if (fileSize > fileSizeFlag) {
            return ReturnDTOUtil.custom(HttpCodeEnum.OVER_MAX_SIZE);
        }
        String imgUrl = "";
        try {
            imgUrl = ossMultipartManager.uploadImages(uploadImg);
        } catch (Exception e) {
            return ReturnDTOUtil.error();
        }
        ReturnDTO returnDTO = new ReturnDTO();
        returnDTO.setMessage(imgUrl);
        return returnDTO;
    }
}
