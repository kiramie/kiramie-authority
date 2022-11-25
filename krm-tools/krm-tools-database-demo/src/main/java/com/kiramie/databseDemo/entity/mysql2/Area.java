package com.kiramie.databseDemo.entity.mysql2;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 地区表
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("area")
@ApiModel(value = "Area对象", description = "地区表")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("地区ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("地区Code码")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty("地区Value值")
    @TableField("area_value")
    private String areaValue;

    @ApiModelProperty("地区名称")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty("地区全称")
    @TableField("area_full_name")
    private String areaFullName;

    @ApiModelProperty("地区简称")
    @TableField("area_simple_name")
    private String areaSimpleName;

    @ApiModelProperty("级别")
    @TableField("`level`")
    private Integer level;

    @ApiModelProperty("排序")
    @TableField("sort_no")
    private Integer sortNo;

    @ApiModelProperty("父级ID")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty("父级Code")
    @TableField("parent_code")
    private String parentCode;

    @ApiModelProperty("是否有子节点")
    @TableField("has_child")
    private Boolean hasChild;

    @ApiModelProperty("状态")
    @TableField("state")
    private Boolean state;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("拓展")
    @TableField("memo")
    private String memo;

    @ApiModelProperty("是否启用(1:已启用/0:未启用)")
    @TableField("is_enabled")
    private Boolean isEnabled;

    @ApiModelProperty("是否逻辑删除(1:已删除/0:未删除)")
    @TableField("is_deleted")
    @TableLogic(value = "0", delval = "1")
    private Boolean isDeleted;

    @ApiModelProperty("创建者编号")
    @TableField("created_by")
    private String createdBy;

    @ApiModelProperty("修改者编号")
    @TableField("updated_by")
    private String updatedBy;

    @ApiModelProperty("创建者姓名")
    @TableField("created_by_name")
    private String createdByName;

    @ApiModelProperty("修改者姓名")
    @TableField("updated_by_name")
    private String updatedByName;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;


}
