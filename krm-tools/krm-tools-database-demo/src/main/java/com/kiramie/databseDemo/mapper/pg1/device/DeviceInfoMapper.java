package com.kiramie.databseDemo.mapper.pg1.device;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kiramie.databseDemo.entity.pg1.device.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 设备信息表 Mapper 接口
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Mapper
@DS("pg1")
public interface DeviceInfoMapper extends BaseMapper<DeviceInfo> {

}
