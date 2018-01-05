package com.slife.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 
 */
public class ActEvtLog implements Serializable {
    private Long logNr;

    private String type;

    private String procDefId;

    private String procInstId;

    private String executionId;

    private String taskId;

    private Date timeStamp;

    private String userId;

    private String lockOwner;

    private Date lockTime;

    private Byte isProcessed;

    private byte[] data;

    private static final long serialVersionUID = 1L;

    public Long getLogNr() {
        return logNr;
    }

    public void setLogNr(Long logNr) {
        this.logNr = logNr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLockOwner() {
        return lockOwner;
    }

    public void setLockOwner(String lockOwner) {
        this.lockOwner = lockOwner;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Byte getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(Byte isProcessed) {
        this.isProcessed = isProcessed;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ActEvtLog other = (ActEvtLog) that;
        return (this.getLogNr() == null ? other.getLogNr() == null : this.getLogNr().equals(other.getLogNr()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getProcDefId() == null ? other.getProcDefId() == null : this.getProcDefId().equals(other.getProcDefId()))
            && (this.getProcInstId() == null ? other.getProcInstId() == null : this.getProcInstId().equals(other.getProcInstId()))
            && (this.getExecutionId() == null ? other.getExecutionId() == null : this.getExecutionId().equals(other.getExecutionId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getTimeStamp() == null ? other.getTimeStamp() == null : this.getTimeStamp().equals(other.getTimeStamp()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLockOwner() == null ? other.getLockOwner() == null : this.getLockOwner().equals(other.getLockOwner()))
            && (this.getLockTime() == null ? other.getLockTime() == null : this.getLockTime().equals(other.getLockTime()))
            && (this.getIsProcessed() == null ? other.getIsProcessed() == null : this.getIsProcessed().equals(other.getIsProcessed()))
            && (Arrays.equals(this.getData(), other.getData()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogNr() == null) ? 0 : getLogNr().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getProcDefId() == null) ? 0 : getProcDefId().hashCode());
        result = prime * result + ((getProcInstId() == null) ? 0 : getProcInstId().hashCode());
        result = prime * result + ((getExecutionId() == null) ? 0 : getExecutionId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getTimeStamp() == null) ? 0 : getTimeStamp().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLockOwner() == null) ? 0 : getLockOwner().hashCode());
        result = prime * result + ((getLockTime() == null) ? 0 : getLockTime().hashCode());
        result = prime * result + ((getIsProcessed() == null) ? 0 : getIsProcessed().hashCode());
        result = prime * result + (Arrays.hashCode(getData()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logNr=").append(logNr);
        sb.append(", type=").append(type);
        sb.append(", procDefId=").append(procDefId);
        sb.append(", procInstId=").append(procInstId);
        sb.append(", executionId=").append(executionId);
        sb.append(", taskId=").append(taskId);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", userId=").append(userId);
        sb.append(", lockOwner=").append(lockOwner);
        sb.append(", lockTime=").append(lockTime);
        sb.append(", isProcessed=").append(isProcessed);
        sb.append(", data=").append(data);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}