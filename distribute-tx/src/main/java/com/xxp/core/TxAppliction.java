package com.xxp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 分布式事务启动类 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/4 下午4:56
 * @Version V1.0
 */
@RestController
@SpringBootApplication
public class TxAppliction {

    public static void main(String[] args) {
        SpringApplication.run(TxAppliction.class, args);
    }

    @RequestMapping("/test")
    public String health() {
        return "success";
    }
}
