package com.kiramie.databseDemo.entity.mysql1;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 优惠券模版
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("pt_coupon_template")
@ApiModel(value = "PtCouponTemplate对象", description = "优惠券模版")
public class PtCouponTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("优惠券名称")
    @TableField("coupon_name")
    private String couponName;

    @ApiModelProperty("优惠描述(前端展示)")
    @TableField("coupon_desc")
    private String couponDesc;

    @ApiModelProperty("使用说明")
    @TableField("use_desc")
    private String useDesc;

    @ApiModelProperty("优惠券类型(0:其他/1:折扣券/2:代金券/3:满减券)")
    @TableField("coupon_type")
    private Integer couponType;

    @ApiModelProperty("优惠金额")
    @TableField("discount_amount")
    private Long discountAmount;

    @ApiModelProperty("折扣比率(折扣券) ")
    @TableField("discount_ratio")
    private Integer discountRatio;

    @ApiModelProperty("满减要求(0:无门槛)")
    @TableField("demand_amount")
    private Long demandAmount;

    @ApiModelProperty("使用范围-平台")
    @TableField("client_scope")
    private String clientScope;

    @ApiModelProperty("使用范围-产品")
    @TableField("product_scope")
    private String productScope;

    @ApiModelProperty("有效期模式(0:永久有效/1:固定时间/2:期限时长)")
    @TableField("validity_mode")
    private Integer validityMode;

    @ApiModelProperty("有效期起")
    @TableField("valid_time_start")
    private LocalDateTime validTimeStart;

    @ApiModelProperty("有效期至")
    @TableField("valid_time_end")
    private LocalDateTime validTimeEnd;

    @ApiModelProperty("期限天数(领取后多少天)")
    @TableField("deadline")
    private Integer deadline;

    @ApiModelProperty("发放状态(0:未派发/1:已派发)")
    @TableField("grant_state")
    private Integer grantState;

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
