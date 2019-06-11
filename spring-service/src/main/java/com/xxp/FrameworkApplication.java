package com.xxp;

import BO.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * <p>  </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/3/26 下午10:06
 * @Version
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:service-config.xml"})
public class FrameworkApplication {

    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(FrameworkApplication.class, args);
        ServiceConfig serviceConfig = applicationContext.getBean(ServiceConfig.class);
        System.out.println(serviceConfig.getServiceName());
    }
}
