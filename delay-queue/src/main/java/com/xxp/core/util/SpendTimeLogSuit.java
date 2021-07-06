package com.xxp.core.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.function.Supplier;

/**
 * @author: xiexipeng
 * @create: 2021/03/16 16:29:41
 * @description: 记录方法执行时间
 * @Version V1.0
 **/
@Slf4j
@UtilityClass
public class SpendTimeLogSuit {

    public static <T> T wrap(Supplier<T> supplier, String title) {
        log.info("Spend time log start to run, title:[{}]", title);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        T result = supplier.get();
        stopWatch.stop();
        log.info("Spend time log complete, title:[{}], time:[{}]", title, stopWatch.getTotalTimeMillis());
        return result;
    }
}
