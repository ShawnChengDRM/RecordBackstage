package com.backstage.record.domain;

/**
 * 统一封装API返回信息
 *
 *
 */
public class RestResult {

    //状态码
    private int code;
    //消息
    private String message;
    //内容
    private Object data;

    public RestResult() {

    }

    public RestResult setCode(ResultCode code) {
        this.code = code.getCode();
        return this;
    }

    public RestResult setCode(int code) {
        this.code = code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public RestResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RestResult setData(Object data) {
        this.data = data;
        return this;
    }

}
