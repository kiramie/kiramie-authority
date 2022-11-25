package com.kiramie.databseDemo.entity.mysql1;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kiramie.databseDemo._enum.CouponTypeEnum;
import com.kiramie.databseDemo.config.BaseEnumJsonDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 优惠券
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("pt_coupon")
@ApiModel(value = "PtCoupon对象", description = "优惠券")
public class PtCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键(优惠券编号) ")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("优惠券模版Id")
    @TableField("template_id")
    private String templateId;

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
    @JsonDeserialize(using = BaseEnumJsonDeserializer.class)
    private CouponTypeEnum couponType;

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

    @ApiModelProperty("派发任务Id")
    @TableField("task_id")
    private String taskId;

    @ApiModelProperty("组织(团队/伙伴)Id")
    @TableField("org_id")
    private String orgId;

    @ApiModelProperty("用户Id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("有效期起")
    @TableField("valid_time_start")
    private LocalDateTime validTimeStart;

    @ApiModelProperty("有效期至")
    @TableField("valid_time_end")
    private LocalDateTime validTimeEnd;

    @ApiModelProperty("使用状态(1:未领取(预留)/2:未使用&回滚/3:已使用/4:已删除/5:已过期/6:冻结中/7:已核销)  ")
    @TableField("use_state")
    private Integer useState;

    @ApiModelProperty("使用时间")
    @TableField("use_time")
    private LocalDateTime useTime;

    @ApiModelProperty("关联订单")
    @TableField("ref_order")
    private String refOrder;

    @ApiModelProperty("应用工作台")
    @TableField("use_client")
    private String useClient;

    @ApiModelProperty("应用产品")
    @TableField("use_product")
    private String useProduct;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("拓展")
    @TableField("memo")
    private String memo;

    @ApiModelProperty("使用者Id")
    @TableField("use_user_id")
    private String useUserId;

    @ApiModelProperty("使用者用户名称")
    @TableField("use_username")
    private String useUsername;

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

    @ApiModelProperty("创建时间(发劵时间/领券时间)")
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;


}
