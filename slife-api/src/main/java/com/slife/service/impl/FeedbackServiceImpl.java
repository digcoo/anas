package com.slife.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.slife.dao.FeedbackDao;
import com.slife.entity.Feedback;
import com.slife.service.FeedbackService;
import com.slife.vo.FeedBackVO;

/**
 * Created by cq on 18-1-21.
 */
@Component
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackDao feedbackDao;

    @Override
    public boolean addFeedBack(FeedBackVO feedbackVO) {
    	Feedback feedback = new Feedback();
    	feedback.setUserId(feedbackVO.getUserId());
    	feedback.setContent(feedbackVO.getContent());
        int addCount = feedbackDao.insert(feedback);
        return addCount == 1 ? Boolean.TRUE : Boolean.FALSE;
    }
}
