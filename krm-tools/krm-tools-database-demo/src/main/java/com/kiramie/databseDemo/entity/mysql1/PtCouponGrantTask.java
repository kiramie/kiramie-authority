package com.kiramie.databseDemo.entity.mysql1;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 优惠券派发任务表
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("pt_coupon_grant_task")
@ApiModel(value = "PtCouponGrantTask对象", description = "优惠券派发任务表")
public class PtCouponGrantTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("活动主题")
    @TableField("activity_title")
    private String activityTitle;

    @ApiModelProperty("优惠券Ids")
    @TableField("template_ids")
    private String templateIds;

    @ApiModelProperty("优惠券类型s")
    @TableField("coupon_types")
    private String couponTypes;

    @ApiModelProperty("使用范围-平台s")
    @TableField("client_scopes")
    private String clientScopes;

    @ApiModelProperty("使用范围-产品s")
    @TableField("product_scopes")
    private String productScopes;

    @ApiModelProperty("发放状态(0:未派发/1:已派发/2:已暂停)")
    @TableField("grant_state")
    private Integer grantState;

    @ApiModelProperty("发放excel上传文件")
    @TableField("grant_excel")
    private String grantExcel;

    @ApiModelProperty("发放方式(1:指定发放/2:限量发放)")
    @TableField("grant_method")
    private Integer grantMethod;

    @ApiModelProperty("x张/每个团队/每张优惠劵(用于指定发放)")
    @TableField("grant_apiece_count")
    private Long grantApieceCount;

    @ApiModelProperty("优惠劵Ids对应的发放张数counts")
    @TableField("grant_each_counts")
    private String grantEachCounts;

    @ApiModelProperty("发放范围(1:所有团队/2:所有企业团队/3:所有个人团队/4:指定团队)")
    @TableField("grant_scope")
    private Integer grantScope;

    @ApiModelProperty("指定团队配置参数")
    @TableField("grant_scope_detail")
    private String grantScopeDetail;

    @ApiModelProperty("派发时间类型(1:即时派发/2:定时派发/3:限时派发)")
    @TableField("grant_time_type")
    private Integer grantTimeType;

    @ApiModelProperty("发放时间起(主动领取-字段)")
    @TableField("grant_time_start")
    private LocalDateTime grantTimeStart;

    @ApiModelProperty("发放时间至(主动领取-字段)")
    @TableField("grant_time_end")
    private LocalDateTime grantTimeEnd;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("拓展")
    @TableField("memo")
    private String memo;

    @ApiModelProperty("是否启用(1:已启用/0:未启用)")
    @TableField("is_enabled")
    private Boolean isEnabled;

    @ApiModelProperty("是否逻辑删除(1:已删除/0:未删除) ")
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
