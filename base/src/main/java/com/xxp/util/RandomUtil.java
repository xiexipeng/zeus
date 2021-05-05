package com.xxp.util;

import lombok.experimental.UtilityClass;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/04/28 15:04:02
 * @description: 随机数工具
 * @Version
 **/
@UtilityClass
public class RandomUtil {

    /**
     * 用于随机选的数字
     */
    public static final String BASE_NUMBER = "0123456789";
    /**
     * 用于随机选的字符
     */
    public static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
    /**
     * 用于随机选的字符和数字
     */
    public static final String BASE_CHAR_NUMBER = BASE_CHAR + BASE_NUMBER;

    private ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获取随机数字
     *
     * @param length
     * @return
     */
    public int getRandomInt(int length) {
        return getRandom().nextInt(length);
    }

    /**
     * 获取随机字符串
     *
     * @param length
     * @return
     */
    public String getRandomString(int length) {
        return getRandomString(BASE_CHAR_NUMBER, length);
    }

    /**
     * 获取随机字符串
     *
     * @param baseString
     * @param length
     * @return
     */
    public String getRandomString(String baseString, int length) {
        if (baseString == null || baseString == "") {
            return "";
        }
        final StringBuilder sb = new StringBuilder(length);
        if (length < 1) {
            length = 1;
        }
        int baseLength = baseString.length();
        for (int i = 0; i < length; i++) {
            int number = getRandomInt(baseLength);
            sb.append(baseString.charAt(number));
        }
        return sb.toString();
    }
}
