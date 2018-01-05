package com.slife.model;

import java.io.Serializable;

/**
 * @author 
 */
public class ActIdMembershipKey implements Serializable {
    private String userId;

    private String groupId;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}