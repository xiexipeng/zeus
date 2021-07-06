package com.xxp.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author: xiexipeng
 * @create: 2021/06/01 17:17:48
 * @description: 异常处理器
 * @Version V1.0
 **/
@Slf4j
@UtilityClass
public class ExUtil {

    public <T> void catchEx(Consumer<T> consumer, String logContent) {
        catchEx(consumer, null, null, logContent, LogLevel.ERROR);
    }

    public <T> void catchEx(Consumer<T> consumer, T t, String logContent) {
        catchEx(consumer, t, null, logContent, LogLevel.ERROR);
    }

    public <T> void catchEx(Consumer<T> consumer, T t, String logContent, LogLevel logLevel) {
        catchEx(consumer, t, null, logContent, logLevel);
    }

    public <T> void catchEx(Consumer<T> consumer, T t, Class<Throwable> throwable, String logContent, LogLevel logLevel) {
        try {
            consumer.accept(t);
        } catch (Throwable e) {
            log(e, logContent, logLevel);
            if (e.getClass().equals(throwable)) {
                throw e;
            }
        }
    }

    public <T> void catchEx(Consumer<T> consumer, Consumer<T> exConsumer, T t, Class<Throwable> throwable, String logContent, LogLevel logLevel) {
        try {
            consumer.accept(t);
        } catch (Throwable e) {
            log(e, logContent, logLevel);
            exConsumer.accept(t);
            if (e.getClass().equals(throwable)) {
                throw e;
            }
        }
    }

    public <T> T catchEx(Supplier<T> supplier, Class<Throwable> throwable, String logContent, LogLevel logLevel) {
        try {
            return supplier.get();
        } catch (Throwable e) {
            log(e, logContent, logLevel);
            if (e.getClass().equals(throwable)) {
                throw e;
            }
        }
        return null;
    }

    public <T, R> R catchEx(Function<T, R> function, T t, Class<Throwable> throwable, String logContent, LogLevel logLevel) {
        try {
            return function.apply(t);
        } catch (Throwable e) {
            log(e, logContent, logLevel);
            if (e.getClass().equals(throwable)) {
                throw e;
            }
        }
        return null;
    }

    private void log(Throwable e, String logContent, LogLevel logLevel) {
        switch (logLevel) {
            case DEBUG:
                log.debug(logContent, e);
                break;
            case INFO:
                log.info(logContent, e);
                break;
            case WARN:
                log.warn(logContent, e);
                break;
            case ERROR:
                log.error(logContent, e);
                break;
        }
    }

    enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }
}
