package com.slife.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slife.base.entity.ReturnDTO;
import com.slife.service.FeedbackService;
import com.slife.util.ReturnDTOUtil;
import com.slife.util.StringUtils;
import com.slife.vo.FeedBackVO;

/**
 * Created by cq on 18-1-21.
 */
@Api(description = "意见反馈操作接口")
@RestController
@RequestMapping("/api/feedback/")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @ApiOperation(value = "意见反馈", notes = "用户提交意见信息")
    @PostMapping("add")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = Boolean.class)})
    public ReturnDTO<Boolean> add(@RequestBody FeedBackVO feedback) {
        if (feedback == null || StringUtils.isEmpty(feedback.getContent())) {
            ReturnDTOUtil.paramError();
        }
        boolean bl = feedbackService.addFeedBack(feedback);
        return bl?ReturnDTOUtil.success():ReturnDTOUtil.fail();
    }
}
