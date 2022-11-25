package com.kiramie.databseDemo.entity.pg1.base;

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
@TableName("base.sys_dictionaries")
@ApiModel(value = "SysDictionaries对象", description = "")
public class SysDictionaries implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dictionaries_id", type = IdType.ASSIGN_ID)
    private String dictionariesId;

    @TableField("bianma")
    private String bianma;

    @TableField("name")
    private String name;

    @TableField("parent_id")
    private String parentId;


}
