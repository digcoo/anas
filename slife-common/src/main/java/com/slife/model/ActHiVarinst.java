package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class ActHiVarinst implements Serializable {
    private String id;

    private String procInstId;

    private String executionId;

    private String taskId;

    private String name;

    private String varType;

    private Integer rev;

    private String bytearrayId;

    private Double double;

    private Long long;

    private String text;

    private String text2;

    private Date createTime;

    private Date lastUpdatedTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getBytearrayId() {
        return bytearrayId;
    }

    public void setBytearrayId(String bytearrayId) {
        this.bytearrayId = bytearrayId;
    }

    public Double getDouble() {
        return double;
    }

    public void setDouble(Double double) {
        this.double = double;
    }

    public Long getLong() {
        return long;
    }

    public void setLong(Long long) {
        this.long = long;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}