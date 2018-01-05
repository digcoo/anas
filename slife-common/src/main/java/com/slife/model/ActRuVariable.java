package com.slife.model;

import java.io.Serializable;

/**
 * @author 
 */
public class ActRuVariable implements Serializable {
    private String id;

    private Integer rev;

    private String type;

    private String name;

    private String executionId;

    private String procInstId;

    private String taskId;

    private String bytearrayId;

    private Double double;

    private Long long;

    private String text;

    private String text2;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
}