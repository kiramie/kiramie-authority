package com.kiramie.databseDemo.entity.mysql2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kiramie.databseDemo.util.ITreeNode;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
public class Chain implements Serializable, ITreeNode<Chain, Long> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("parent_id")
    private Long parentId;

    @TableField("name")
    private String name;

    @TableField(value = "children", exist = false)
    private List<Chain> children;
}
