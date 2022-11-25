package com.kiramie.databseDemo.mapper.mysql1;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kiramie.databseDemo.entity.mysql1.PtOrganization;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 伙伴端-组织(企业/团队)表 Mapper 接口
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Mapper
@DS("mysql1")
public interface PtOrganizationMapper extends BaseMapper<PtOrganization> {

}
