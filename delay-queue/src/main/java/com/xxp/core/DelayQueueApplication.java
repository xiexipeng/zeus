package com.xxp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: xiexipeng
 * @create: 2021/03/09 10:24:26
 * @description:
 * @Version
 **/
@EnableScheduling
@SpringBootApplication
public class DelayQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(DelayQueueApplication.class, args);
    }
}
