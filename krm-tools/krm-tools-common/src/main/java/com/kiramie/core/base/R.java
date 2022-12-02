package com.kiramie.core.base;

import lombok.Data;

import java.io.Serializable;

/**
 * API返回实体类
 *
 * @param <T>
 * @Author:fgx
 * @Date:2020/10/22
 */
@Data
public class R<T> implements Serializable {

    private static final Integer SUCCESS_CODE = 0;
    private static final Integer ERROR_CODE = -1;
    private static final Integer WARNING_CODE = 1;
    private static final Integer AUTHORIZE_CODE = 100000;
    private static final String SUCCESS_MSG = "请求成功！";
    private static final String ERROR_MSG = "服务器繁忙！";

    public static final R<String> SUCCESS = new R(SUCCESS_CODE, SUCCESS_MSG, null);
    public static final R<String> ERROR = new R(ERROR_CODE, ERROR_MSG, null);

    private Integer code;
    private String message;
    private T data;

    private R() {
    }

    private R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> R<T> result(Integer code, String msg, T data) {
        return new R(code, msg, data);
    }

    public static <T> R<T> success(T data) {
        return result(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> R<T> success(String message, T data) {
        return result(SUCCESS_CODE, message, data);
    }

    public static <T> R<T> error(String msg) {
        return result(ERROR_CODE, msg, null);
    }

    public static <T> R<T> error(String msg, T data) {
        return result(ERROR_CODE, msg, data);
    }

    public static <T> R<T> warning(String msg) {
        return result(WARNING_CODE, msg, null);
    }

    public static <T> R<T> warning(String msg, T data) {
        return result(WARNING_CODE, msg, data);
    }

    public static <T> R<T> authorize(String msg) {
        return result(AUTHORIZE_CODE, msg, null);
    }

    public static <T> R<T> authorize(String msg, T data) {
        return result(AUTHORIZE_CODE, msg, data);
    }
}
