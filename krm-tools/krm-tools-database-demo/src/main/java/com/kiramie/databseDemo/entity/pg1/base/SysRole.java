package com.kiramie.databseDemo.entity.pg1.base;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("base.t_sys_role")
@ApiModel(value = "SysRole对象", description = "角色信息表")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("父角色ID（一个角色根据不同权限组合可以派生出多个子角色）")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("角色名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("角色类型：【1】员工、【2】商户")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("角色描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("菜单IDs")
    @TableField("menu_ids")
    private String menuIds;

    @ApiModelProperty("默认角色：【0】否、【1】是（默认角色不可删除）")
    @TableField("default_data")
    private Integer defaultData;

    @ApiModelProperty("商户ID")
    @TableField("merchant_id")
    private Long merchantId;

    @ApiModelProperty("商户名称")
    @TableField("merchant_name")
    private String merchantName;

    @ApiModelProperty("状态：【1】正常、【2】停用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("创建人ID")
    @TableField("create_by")
    private Long createBy;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("更新者ID")
    @TableField("update_by")
    private Long updateBy;

    @ApiModelProperty("已删除：【0】未删除、【1】已删除")
    @TableField("deleted")
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;


}
