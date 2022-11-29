package com.kiramie.feignDemo.entity;

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
public class PtCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String templateId;

    private String couponName;

    private String couponDesc;

    private String useDesc;

    private Integer couponType;

    private Long discountAmount;

    private Integer discountRatio;

    private Long demandAmount;

    private String clientScope;

    private String productScope;

    private String taskId;

    private String orgId;

    private String userId;

    private LocalDateTime validTimeStart;

    private LocalDateTime validTimeEnd;

    private Integer useState;

    private LocalDateTime useTime;

    private String refOrder;

    private String useClient;

    private String useProduct;

    private String remark;

    private String memo;

    private String useUserId;

    private String useUsername;

    private String createdBy;

    private String updatedBy;

    private String createdByName;

    private String updatedByName;

    private String createdAt;

    private LocalDateTime updatedAt;


}
