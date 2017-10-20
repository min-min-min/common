package com.banggood.common.utils.security;

import lombok.NonNull;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5 加密工具
 * Created by Chenjing on 2017/6/20.
 * @author Chenjing
 */
public class Md5Util {

    /**
     * MD5加密
     *
     * @param data 加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException     异常
     * @throws UnsupportedEncodingException 不支持的异常
     */
    public static String encryption(@NonNull String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        return base64en.encode(md5.digest(data.getBytes("utf-8")));
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(Md5Util.encryption("aa"));
    }
}
