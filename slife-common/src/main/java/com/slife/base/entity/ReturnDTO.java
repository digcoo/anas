package com.slife.base.entity;

/**
 * Created by chen on 2017/5/8.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 返回的 数据模型
 */

public class ReturnDTO<T> {
    private int code;
    private String error;
    private T message;

    public ReturnDTO(T message) {
        this.message = message;
    }

    public ReturnDTO(int code, String error, T message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }
    public ReturnDTO(int code, String error) {
        this.code = code;
        this.error = error;
    }

    public ReturnDTO() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}

