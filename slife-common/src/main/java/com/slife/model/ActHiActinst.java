package com.slife.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class ActHiActinst implements Serializable {
    private String id;

    private String procDefId;

    private String procInstId;

    private String executionId;

    private String actId;

    private String taskId;

    private String callProcInstId;

    private String actName;

    private String actType;

    private String assignee;

    private Date startTime;

    private Date endTime;

    private Long duration;

    private String tenantId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCallProcInstId() {
        return callProcInstId;
    }

    public void setCallProcInstId(String callProcInstId) {
        this.callProcInstId = callProcInstId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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
        ActHiActinst other = (ActHiActinst) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProcDefId() == null ? other.getProcDefId() == null : this.getProcDefId().equals(other.getProcDefId()))
            && (this.getProcInstId() == null ? other.getProcInstId() == null : this.getProcInstId().equals(other.getProcInstId()))
            && (this.getExecutionId() == null ? other.getExecutionId() == null : this.getExecutionId().equals(other.getExecutionId()))
            && (this.getActId() == null ? other.getActId() == null : this.getActId().equals(other.getActId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getCallProcInstId() == null ? other.getCallProcInstId() == null : this.getCallProcInstId().equals(other.getCallProcInstId()))
            && (this.getActName() == null ? other.getActName() == null : this.getActName().equals(other.getActName()))
            && (this.getActType() == null ? other.getActType() == null : this.getActType().equals(other.getActType()))
            && (this.getAssignee() == null ? other.getAssignee() == null : this.getAssignee().equals(other.getAssignee()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProcDefId() == null) ? 0 : getProcDefId().hashCode());
        result = prime * result + ((getProcInstId() == null) ? 0 : getProcInstId().hashCode());
        result = prime * result + ((getExecutionId() == null) ? 0 : getExecutionId().hashCode());
        result = prime * result + ((getActId() == null) ? 0 : getActId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getCallProcInstId() == null) ? 0 : getCallProcInstId().hashCode());
        result = prime * result + ((getActName() == null) ? 0 : getActName().hashCode());
        result = prime * result + ((getActType() == null) ? 0 : getActType().hashCode());
        result = prime * result + ((getAssignee() == null) ? 0 : getAssignee().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", procDefId=").append(procDefId);
        sb.append(", procInstId=").append(procInstId);
        sb.append(", executionId=").append(executionId);
        sb.append(", actId=").append(actId);
        sb.append(", taskId=").append(taskId);
        sb.append(", callProcInstId=").append(callProcInstId);
        sb.append(", actName=").append(actName);
        sb.append(", actType=").append(actType);
        sb.append(", assignee=").append(assignee);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", duration=").append(duration);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}