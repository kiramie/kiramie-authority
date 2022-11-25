package com.kiramie.databseDemo.entity.mysql2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author kiramie
 * @since 2022-11-23
 */
@Data
@TableName("chain")
@ApiModel(value = "Chain对象", description = "")
public class Chain implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("parent_id")
    private Long parentId;

    @TableField("`name`")
    private String name;


}
