package com.kiramie.authority.dto.demo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yangbin
 * @since 2022/11/25
 **/
@Data
public class Demo1Dto {
    private Long id;
    private String name;
    private Integer age;
    private Boolean gender;
    private LocalDateTime createTime;
}
