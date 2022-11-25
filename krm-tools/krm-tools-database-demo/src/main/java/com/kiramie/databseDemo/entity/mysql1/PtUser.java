package com.kiramie.databseDemo.entity.mysql1;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 伙伴端-用户表
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("pt_user")
@ApiModel(value = "PtUser对象", description = "伙伴端-用户表")
public class PtUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户编号")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private String userId;

    @ApiModelProperty("用户唯一标识")
    @TableField("uuid")
    private String uuid;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码")
    @TableField("`password`")
    private String password;

    @ApiModelProperty("用户名")
    @TableField("salt")
    private String salt;

    @ApiModelProperty("手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty("别名")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty("真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty("用户头像uri")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("邀请人id")
    @TableField("inviter_id")
    private String inviterId;

    @ApiModelProperty("邀请人电话(业务员)")
    @TableField("inviter_mobile")
    private String inviterMobile;

    @ApiModelProperty("来源渠道, sem、seo、wxapp。。。可扩展")
    @TableField("utm_source")
    private String utmSource;

    @ApiModelProperty("来源媒介, keyword、product、article。。。可扩展")
    @TableField("utm_medium")
    private String utmMedium;

    @ApiModelProperty("媒介参数,链接...")
    @TableField("utm_content")
    private String utmContent;

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
