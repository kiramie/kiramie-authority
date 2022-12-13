package com.kiramie.databseDemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kiramie.core.base.R;
import com.kiramie.core.base.page.PageData;
import com.kiramie.databseDemo.entity.mysql1.PtCoupon;
import com.kiramie.databseDemo.mapper.mysql1.PtCouponMapper;
import com.kiramie.databseDemo.vo.Coupon1Vo;
import com.kiramie.databseDemo.vo.Test1Vo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author yangbin
 * @since 2022/12/3
 **/
@Slf4j
@RestController
@RequestMapping("/t2")
public class T2Controller {

    @Resource
    private PtCouponMapper ptCouponMapper;

    @PostMapping("/m1")
    public R<Test1Vo> m1(@RequestBody @Validated Test1Vo vo) {
        log.info("m1:\n{}", JSONObject.toJSONString(vo, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));
        return R.success(vo);
    }

    @PostMapping("/m2")
    public R<PageData<PtCoupon>> m2(@RequestBody @Validated Coupon1Vo vo) {
        log.info("m2:\n{}", JSONObject.toJSONString(vo, SerializerFeature.PrettyFormat));
        IPage<PtCoupon> ptCouponIPage = ptCouponMapper.selectPage(vo.getPage(), Wrappers.<PtCoupon>lambdaQuery()
                .like(StringUtils.isNotBlank(vo.getCouponName()), PtCoupon::getCouponName, vo.getCouponName())
                .eq(Objects.nonNull(vo.getCouponType()), PtCoupon::getCouponType, vo.getCouponType())
                //.gt(StringUtils.isNotBlank(vo.getBeginTime()), PtCoupon::getUpdatedAt, vo.getBeginTime())
                //.lt(StringUtils.isNotBlank(vo.getEndTime()), PtCoupon::getUpdatedAt, vo.getEndTime())
                .gt(Objects.nonNull(vo.getBegin()), PtCoupon::getUpdatedAt, vo.getBegin())
                .lt(Objects.nonNull(vo.getEnd()), PtCoupon::getUpdatedAt, vo.getEnd())
        );
        return R.success(new PageData<>(ptCouponIPage));
    }

    @Test
    public void t1() {

    }
}
