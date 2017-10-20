package com.banggood.common.utils.string;

import com.google.common.base.Splitter;
import lombok.NonNull;

import java.util.List;

/**
 * 字符串分割工具类
 * Created by Chenjing on 2017/5/31.
 *
 * @author Chenjing
 */
public class SplitUtil {
    /**
     * 字符串分割
     *
     * @param reg 按照什么格式分割
     * @param str 要分割的字符串
     * @return list
     */
    public static List<String> split(@NonNull String reg, @NonNull String str) {
        return Splitter.on(reg)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(str);
    }

    public static void main(String[] args) {

    }
}
