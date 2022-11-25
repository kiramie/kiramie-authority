package com.kiramie.databseDemo.entity.pg1.device;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备信息表
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("device.t_device_info")
@ApiModel(value = "DeviceInfo对象", description = "设备信息表")
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("设备ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("设备名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("设备MAC")
    @TableField("mac")
    private String mac;

    @ApiModelProperty("设备WIFI版本")
    @TableField("wifi_version")
    private String wifiVersion;

    @ApiModelProperty("设备MCU版本")
    @TableField("mcu_version")
    private String mcuVersion;

    @ApiModelProperty("国家编号")
    @TableField("country_id")
    private String countryId;

    @ApiModelProperty("国家名称")
    @TableField("country")
    private String country;

    @ApiModelProperty("省份编号")
    @TableField("province_id")
    private String provinceId;

    @ApiModelProperty("省份名称")
    @TableField("province")
    private String province;

    @ApiModelProperty("城市编号")
    @TableField("city_id")
    private String cityId;

    @ApiModelProperty("城市名称")
    @TableField("city")
    private String city;

    @ApiModelProperty("县区编号")
    @TableField("area_id")
    private String areaId;

    @ApiModelProperty("县区名称")
    @TableField("area")
    private String area;

    @ApiModelProperty("详细地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("商户ID")
    @TableField("merchant_id")
    private Long merchantId;

    @ApiModelProperty("商户名称")
    @TableField("merchant_name")
    private String merchantName;

    @ApiModelProperty("产品ID")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("产品名称")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty("产品密钥")
    @TableField("product_key")
    private String productKey;

    @ApiModelProperty("在线状态：【f】离线、【t】在线")
    @TableField("online_status")
    private Boolean onlineStatus;

    @ApiModelProperty("状态：【1】正常、【2】故障")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("最后在线时间")
    @TableField("last_online_time")
    private LocalDateTime lastOnlineTime;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("创建者ID")
    @TableField("create_by")
    private Long createBy;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新者ID")
    @TableField("update_by")
    private Long updateBy;

    @ApiModelProperty("已删除：【0】否、【1】是")
    @TableField("deleted")
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    @ApiModelProperty("经度")
    @TableField("longitude")
    private String longitude;

    @ApiModelProperty("维度")
    @TableField("latitude")
    private String latitude;


}
