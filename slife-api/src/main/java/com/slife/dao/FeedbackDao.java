package com.slife.dao;


import com.slife.entity.Feedback;

public interface FeedbackDao {

    int deleteByPrimaryKey(long id);

    int insert(Feedback record);

    Feedback selectByPrimaryKey(long id);

    int updateByPrimaryKey(Feedback record);
}