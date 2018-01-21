package com.slife.service.impl;

import com.slife.dao.FeedbackDao;
import com.slife.entity.Feedback;
import com.slife.service.FeedbackService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by cq on 18-1-21.
 */
@Component
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackDao feedbackDao;

    @Override
    public boolean addFeedBack(Feedback feedback) {
        int addCount = feedbackDao.insert(feedback);
        return addCount == 1 ? Boolean.TRUE : Boolean.FALSE;
    }
}
