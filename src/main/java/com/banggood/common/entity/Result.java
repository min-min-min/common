package com.banggood.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by chenjing on 2017/5/3.
 * 统一返回结果
 *
 * @author Chenjing
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
}
