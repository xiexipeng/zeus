package com.xxp.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.DigestUtils;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/06/17 17:17:54
 * @description: 加密算法相关
 * @Version V1.0
 **/
@UtilityClass
public class SecretUtil {

    public String md5(String s){
        return DigestUtils.md5DigestAsHex(s.getBytes());
    }
}
