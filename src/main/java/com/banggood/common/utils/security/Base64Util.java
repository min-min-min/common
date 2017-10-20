package com.banggood.common.utils.security;

import lombok.NonNull;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * base64 加密解密
 * Created by Chenjing on 2017/7/5.
 *
 * @author Chenjing
 */
public class Base64Util {


    /**
     * base64解密
     *
     * @param var 经过base64加密的字符串
     * @return base64解密后的字符串 byte数组
     * @throws UnsupportedEncodingException 不支持的异常
     */
    public static byte[] decodeData(@NonNull String var) throws UnsupportedEncodingException {
        return Base64.decodeBase64(var);
    }

    /**
     * base64加密
     *
     * @param var 需要加密的字符串
     * @return base64加密后的字符串 byte数组
     * @throws UnsupportedEncodingException 不支持的异常
     */
    public static byte[] encodeData(@NonNull String var) throws UnsupportedEncodingException {
        return Base64.encodeBase64(var.getBytes("utf-8"));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        decodeData(null);
        System.out.println(new String(decodeData(new String(encodeData("aa")))));
        System.out.println(new String(encodeData("aa")));
    }

}
