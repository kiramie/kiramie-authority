package com.kiramie.authority.controller.test;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangbin
 * @since 2022/11/21
 **/
@Data
public class Param1 {
    @NotBlank(message = "id不能为空")
    private String id;

    @Range(min = 1, max = 100, message = "条数必须为1~100之间的整数")
    private Integer age;

    @NotEmpty(message = "tags不能为空")
    private List<@NotBlank(message = "tag不能为空") String> tags;

    @Positive(message = "价格下限必须是正数")
    @Digits(integer = 20, fraction = 2, message = "价格只允许在20位整数和2位小数范围内")
    private BigDecimal priceUpper;

    private String remark;
}
