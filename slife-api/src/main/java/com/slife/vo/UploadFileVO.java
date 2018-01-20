package com.slife.vo;

import java.io.Serializable;

/**
 * Created by cq on 18-1-21.
 */
public class UploadFileVO implements Serializable{

    private String domain;

    private String path;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
