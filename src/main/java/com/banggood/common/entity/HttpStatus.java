package com.banggood.common.entity;

/**
 * Created by Chenjing on 2017/5/3.
 *
 * @author Chenjing
 */
public enum HttpStatus {

    //项目返回成功
    SUCCESS(200, "成功"),
    //项目返回失败
    FAIL(500, "失败"),
    //没有登录
    AUTH_FAIL(401, "抱歉，请登录"),
    //权限不足
    FORBIDDEN(403, "抱歉，没有权限"),
    //项目返回404 不存在
    NOT_EXIST(404, "访问的资源不存在");


    private int code;
    private String msg;

    HttpStatus(int code, String msg) {
        this.setCode(code);
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
