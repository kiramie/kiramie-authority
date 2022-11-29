package com.kiramie.feignDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yangbin
 * @since 2022/11/29
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.kiramie.feignDemo.openFeign"})
public class FeignDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignDemoApplication.class, args);
    }
}
