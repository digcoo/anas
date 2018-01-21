package com.slife.wxapi.response;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by cq on 18-1-19.
 */
public class WeChatBase implements Serializable {

    @JSONField(name = "errcode")
    private String errcode;

    @JSONField(name = "errmsg")
    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
