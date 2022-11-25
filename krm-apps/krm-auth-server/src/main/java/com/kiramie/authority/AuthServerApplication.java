package com.kiramie.authority;

import com.kiramie.dozer.EnableDozerConfig;
import com.kiramie.redis.EnableRedisConfig;
import com.kiramie.validator.EnableFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangbin
 * @since 2022/11/21
 **/
@SpringBootApplication
@EnableFormValidator
@EnableDozerConfig
@EnableRedisConfig
@Slf4j
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
