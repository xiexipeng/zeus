package com.xxp.delaycore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: xiexipeng@u51.com
 * @create: 2021/03/09 10:24:26
 * @description:
 * @Version
 **/
@EnableScheduling
@SpringBootApplication
public class DelayQueueAppliction {

    public static void main(String[] args) {
        SpringApplication.run(DelayQueueAppliction.class, args);
    }
}
