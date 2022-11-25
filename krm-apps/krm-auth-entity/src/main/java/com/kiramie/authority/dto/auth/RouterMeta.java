package com.kiramie.authority.dto.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * Vue路由 Meta
 */
@Data
public class RouterMeta implements Serializable {

    private static final long serialVersionUID = 5499925008927195914L;
    private String title;
    private String icon = "";
    private Boolean breadcrumb = true;

}
