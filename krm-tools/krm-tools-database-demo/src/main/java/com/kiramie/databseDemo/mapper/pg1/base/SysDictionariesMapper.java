package com.kiramie.databseDemo.mapper.pg1.base;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kiramie.databseDemo.entity.pg1.base.SysDictionaries;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Mapper
@DS("pg1")
public interface SysDictionariesMapper extends BaseMapper<SysDictionaries> {

}
