package com.banggood.common.utils;


import com.banggood.common.entity.HttpStatus;
import com.banggood.common.entity.Result;
import lombok.NonNull;

/**
 * 项目统一返回的结果
 * Created by chenjing on 2017/5/3.
 *
 * @author Chenjing
 */
public class ResultUtil {
    /**
     * 返回失败
     *
     * @param object 数据
     * @return {@link Result}
     */
    public static Result error(Object object) {
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.FAIL.getCode());
        result.setMsg(HttpStatus.FAIL.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 返回成功
     *
     * @param object 数据
     * @return {@link Result}
     */
    public static Result success(Object object) {
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.SUCCESS.getCode());
        result.setMsg(HttpStatus.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 自定义返回结果
     *
     * @param code 状态码
     * @param msg  详细信息
     * @param data 数据
     * @return {@link Result}
     */
    public static Result build(@NonNull int code, @NonNull String msg, Object data) {
        return new Result<>().setCode(code)
                .setMsg(msg)
                .setData(data);
    }

    /**
     * 自定义返回结果
     *
     * @param httpStatus {@link HttpStatus}
     * @param data       数据
     * @return {@link Result}
     */
    public static Result build(@NonNull HttpStatus httpStatus, Object data) {
        return new Result<>().setCode(httpStatus.getCode())
                .setMsg(httpStatus.getMsg())
                .setData(data);
    }
}
