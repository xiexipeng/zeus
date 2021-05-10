package com.xxp.spi;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/05/07 22:51:46
 * @description:
 * @Version
 **/
public class ExtensionLoader<T> {

    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type){
        return new ExtensionLoader<>();
    }

    public <T> T getInstant(String name){
        return null;
    }
}
