package com.slife.api.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.entity.Feedback;
import com.slife.service.FeedbackService;
import com.slife.util.ReturnDTOUtil;
import com.slife.util.StringUtils;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by cq on 18-1-21.
 */
@Api(description = "意见反馈")
@RestController
@RequestMapping("/feedback/")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    @ApiOperation(value = "意见反馈", notes = "用户提交意见信息")
    @ApiImplicitParam(name = "feedback", paramType = "form", dataType = "Object", required = true)
    @PostMapping("add")
    @ApiResponses({@ApiResponse(code = 200, message = "成功", response = Boolean.class)})
    public ReturnDTO<Boolean> add(@RequestParam("feedback") Feedback feedback) {
        if (feedback == null || StringUtils.isEmpty(feedback.getContent())) {
            ReturnDTOUtil.paramError();
        }
        boolean bl = feedbackService.addFeedBack(feedback);
        return new ReturnDTO<>(bl);
    }
}
