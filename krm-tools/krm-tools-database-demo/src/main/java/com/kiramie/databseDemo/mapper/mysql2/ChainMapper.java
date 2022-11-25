package com.kiramie.databseDemo.mapper.mysql2;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kiramie.databseDemo.entity.mysql2.Chain;
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
@DS("mysql2")
public interface ChainMapper extends BaseMapper<Chain> {

}
