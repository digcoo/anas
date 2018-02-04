package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.slife.base.entity.ApiEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Feedback extends ApiEntity<Feedback> implements Serializable {

    /**
     * 提交者用户id
     */
    private long userId;

    /**
     * 反馈建议
     */
    private String content;


    private static final long serialVersionUID = 1L;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}