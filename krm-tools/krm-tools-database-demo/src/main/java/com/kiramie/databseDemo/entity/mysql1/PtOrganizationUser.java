package com.kiramie.databseDemo.entity.mysql1;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 伙伴端-组织成员用户关联表
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("pt_organization_user")
@ApiModel(value = "PtOrganizationUser对象", description = "伙伴端-组织成员用户关联表")
public class PtOrganizationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键Id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("公司(组织)编号")
    @TableField("organization_id")
    private String organizationId;

    @ApiModelProperty("用户编号")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("组织角色(非职位/排序规则 0:超级管理员/1:管理员/2:成员) ")
    @TableField("role")
    private String role;

    @ApiModelProperty("职位")
    @TableField("position")
    private String position;

    @ApiModelProperty("邀请人id")
    @TableField("inviter_id")
    private String inviterId;

    @ApiModelProperty("加入时间")
    @TableField("join_time")
    private LocalDateTime joinTime;

    @ApiModelProperty("状态")
    @TableField("state")
    private Boolean state;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("拓展")
    @TableField("memo")
    private String memo;

    @ApiModelProperty("乐观锁 初始:0")
    @TableField("version")
    private Integer version;

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
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime updatedAt;

    @ApiModelProperty("最近操作时间(用于组织切换)")
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime latestUseTime;


}
