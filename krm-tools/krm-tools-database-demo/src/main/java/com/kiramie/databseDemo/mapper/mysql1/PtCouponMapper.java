package com.kiramie.databseDemo.mapper.mysql1;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kiramie.databseDemo.entity.mysql1.PtCoupon;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 优惠券 Mapper 接口
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Mapper
@DS("mysql1")
public interface PtCouponMapper extends BaseMapper<PtCoupon> {

}
