package com.kiramie.authority.dto.auth;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangbin
 * @since 2022/11/22
 **/
@Data
public class VueRouter implements Serializable {
    private static final long serialVersionUID = -3327478146308500708L;

    private Long id;

    private Long parentId;

    private String path;

    private String name;

    private String component;

    private String redirect;

    private RouterMeta meta;

    private Boolean hidden = false;

    private Boolean alwaysShow = false;

    private List<VueRouter> children;
}
