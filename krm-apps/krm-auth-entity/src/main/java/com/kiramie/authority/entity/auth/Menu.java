package com.kiramie.authority.entity.auth;

import lombok.Data;

/**
 * @author yangbin
 * @since 2022/11/22
 **/
@Data
public class Menu {

    private Long id;

    private String name;

    private String describe;

    private Boolean isPublic;

    private String path;

    private String component;

    private Boolean isEnable;

    private Integer sortValue;

    private String icon;

    private String group;

    private Long parentId;
}
