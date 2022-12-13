package com.kiramie.databseDemo.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kiramie.core.base.page.LimitVo;
import com.kiramie.databseDemo._enum.CouponTypeEnum;
import com.kiramie.databseDemo.config.BaseEnumJsonDeserializer;
import com.kiramie.databseDemo.entity.mysql1.PtCoupon;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author yangbin
 * @since 2022/12/3
 **/
@Data
public class Coupon1Vo extends LimitVo<PtCoupon> {

    private String couponName;

    @JsonDeserialize(using = BaseEnumJsonDeserializer.class)
    //@NotNull(message = "类型不能为空")
    private CouponTypeEnum couponType;

    private LocalDateTime begin;

    private LocalDateTime end;
}
