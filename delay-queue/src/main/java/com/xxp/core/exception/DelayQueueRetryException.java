package com.xxp.core.exception;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/08 17:30:33
 * @description: 异常重试
 * @Version V1.0
 **/
public class DelayQueueRetryException extends RuntimeException {

    private  String reason;

    public DelayQueueRetryException() {
    }

    public DelayQueueRetryException(String reason) {
        super(reason);
        this.reason = reason;
    }
}
