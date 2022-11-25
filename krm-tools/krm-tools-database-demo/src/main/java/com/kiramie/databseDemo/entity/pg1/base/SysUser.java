package com.kiramie.databseDemo.entity.pg1.base;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("base.t_sys_user")
@ApiModel(value = "SysUser对象", description = "系统用户表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("登录账号")
    @TableField("account")
    private String account;

    @ApiModelProperty("登录密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("密码盐值")
    @TableField("salt")
    private String salt;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("级别")
    @TableField("level")
    private Integer level;

    @ApiModelProperty("联系人")
    @TableField("contact")
    private String contact;

    @ApiModelProperty("联系人电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("联系人邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("性别：【1】男、【2】女")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("省份ID")
    @TableField("province_id")
    private String provinceId;

    @ApiModelProperty("省份")
    @TableField("province")
    private String province;

    @ApiModelProperty("城市ID")
    @TableField("city_id")
    private String cityId;

    @ApiModelProperty("城市")
    @TableField("city")
    private String city;

    @ApiModelProperty("区县ID")
    @TableField("area_id")
    private String areaId;

    @ApiModelProperty("区县")
    @TableField("area")
    private String area;

    @ApiModelProperty("详细地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("最后登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("角色ID")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty("角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("所属商户ID")
    @TableField("merchant_id")
    private Long merchantId;

    @ApiModelProperty("所属商户名称")
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

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("修改人ID")
    @TableField("update_by")
    private Long updateBy;

    @ApiModelProperty("已删除：【0】未删除、【1】已删除")
    @TableField("deleted")
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;


}
