package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class ActHiComment implements Serializable {
    private String id;

    private String type;

    private Date time;

    private String userId;

    private String taskId;

    private String procInstId;

    private String action;

    private String message;

    private byte[] fullMsg;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getFullMsg() {
        return fullMsg;
    }

    public void setFullMsg(byte[] fullMsg) {
        this.fullMsg = fullMsg;
    }
}