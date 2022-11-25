package com.kiramie.databseDemo.entity.pg1.base;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("base.t_sys_menu")
@ApiModel(value = "SysMenu对象", description = "")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("父ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("父名称")
    @TableField("parent_name")
    private String parentName;

    @ApiModelProperty("菜单类型：【1】分组、【2】菜单、【3】按钮")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("菜单名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("菜单名称，英文")
    @TableField("name_en")
    private String nameEn;

    @ApiModelProperty("菜单KEY")
    @TableField("menu_key")
    private String menuKey;

    @ApiModelProperty("级别")
    @TableField("level")
    private Integer level;

    @ApiModelProperty("优先级")
    @TableField("priority")
    private Integer priority;

    @ApiModelProperty("菜单URL")
    @TableField("url")
    private String url;

    @ApiModelProperty("菜单METHOD")
    @TableField("method")
    private String method;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("菜单描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("状态：【1】正常、【2】禁用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("创建人ID")
    @TableField("created_by")
    private Long createdBy;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人ID")
    @TableField("updated_by")
    private Long updatedBy;

    @ApiModelProperty("是否删除 【0】否、【1】是")
    @TableField("deleted")
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;


}
