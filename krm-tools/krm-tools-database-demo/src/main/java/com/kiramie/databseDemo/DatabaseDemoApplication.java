package com.kiramie.databseDemo;

import com.kiramie.dozer.EnableDozerConfig;
import com.kiramie.validator.EnableFormValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangbin
 * @since 2022/11/23
 **/
@SpringBootApplication
@EnableFormValidator
@EnableDozerConfig
public class DatabaseDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatabaseDemoApplication.class, args);
    }
}
