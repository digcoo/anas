package com.slife.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author 
 */
public class ActGeBytearray implements Serializable {
    private String id;

    private Integer rev;

    private String name;

    private String deploymentId;

    private Byte generated;

    private byte[] bytes;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public Byte getGenerated() {
        return generated;
    }

    public void setGenerated(Byte generated) {
        this.generated = generated;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
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
        ActGeBytearray other = (ActGeBytearray) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRev() == null ? other.getRev() == null : this.getRev().equals(other.getRev()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDeploymentId() == null ? other.getDeploymentId() == null : this.getDeploymentId().equals(other.getDeploymentId()))
            && (this.getGenerated() == null ? other.getGenerated() == null : this.getGenerated().equals(other.getGenerated()))
            && (Arrays.equals(this.getBytes(), other.getBytes()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRev() == null) ? 0 : getRev().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDeploymentId() == null) ? 0 : getDeploymentId().hashCode());
        result = prime * result + ((getGenerated() == null) ? 0 : getGenerated().hashCode());
        result = prime * result + (Arrays.hashCode(getBytes()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", rev=").append(rev);
        sb.append(", name=").append(name);
        sb.append(", deploymentId=").append(deploymentId);
        sb.append(", generated=").append(generated);
        sb.append(", bytes=").append(bytes);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}