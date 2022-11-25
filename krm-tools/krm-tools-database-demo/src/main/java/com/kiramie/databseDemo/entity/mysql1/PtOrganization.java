package com.kiramie.databseDemo.entity.mysql1;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 伙伴端-组织(企业/团队)表
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("pt_organization")
@ApiModel(value = "PtOrganization对象", description = "伙伴端-组织(企业/团队)表")
public class PtOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("组织(企业/个体)Id")
    @TableId(value = "organization_id", type = IdType.ASSIGN_ID)
    private String organizationId;

    @ApiModelProperty("组织名称")
    @TableField("organization_name")
    private String organizationName;

    @ApiModelProperty("组织简称")
    @TableField("organization_short_name")
    private String organizationShortName;

    @ApiModelProperty("组织类型(0:企业/1:个体)")
    @TableField("organization_type")
    private Boolean organizationType;

    @ApiModelProperty("法人姓名/真实姓名")
    @TableField("legal_persons_name")
    private String legalPersonsName;

    @ApiModelProperty("法人身份证号")
    @TableField("legal_persons_id_card")
    private String legalPersonsIdCard;

    @ApiModelProperty("社会信用代码")
    @TableField("social_credit_code")
    private String socialCreditCode;

    @ApiModelProperty("正面身份证件")
    @TableField("positive_id_card_url")
    private String positiveIdCardUrl;

    @ApiModelProperty("发面身份证件")
    @TableField("opposite_id_card_url")
    private String oppositeIdCardUrl;

    @ApiModelProperty("营业执照")
    @TableField("license_url")
    private String licenseUrl;

    @ApiModelProperty("组织LOGO")
    @TableField("organization_logo_url")
    private String organizationLogoUrl;

    @ApiModelProperty("组织管理员Id（弃用）")
    @TableField("administrator_id")
    private String administratorId;

    @ApiModelProperty("组织管理员姓名（弃用）")
    @TableField("administrator_name")
    private String administratorName;

    @ApiModelProperty("组织管理员手机号（弃用）")
    @TableField("administrator_mobile")
    private String administratorMobile;

    @ApiModelProperty("团队成员数上限")
    @TableField("member_count")
    private Integer memberCount;

    @ApiModelProperty("账户Id(后续根据Pos提供信息完善)")
    @TableField("account_id")
    private String accountId;

    @ApiModelProperty("账户记录")
    @TableField("account_detail")
    private String accountDetail;

    @ApiModelProperty("状态(企业状态 0:未认证/1:已认证/2:认证中/3:认证未通过)")
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
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;


}
