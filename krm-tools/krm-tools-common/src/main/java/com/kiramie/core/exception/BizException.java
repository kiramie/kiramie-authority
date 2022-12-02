package com.kiramie.core.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * 自定义异常类，用来处理业务
 *
 * @Author:fgx
 * @Date:2020/10/22
 */
@Getter
@ToString
public class BizException extends RuntimeException {

    private static final Integer CODE = 1;
    private static final String ERROR_MSG = "业务异常！";
    private Integer code = CODE;
    private String msg = ERROR_MSG;
    private Object data = null;

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BizException(String msg, Object data) {
        super(msg);
        this.msg = msg;
        this.data = data;
    }

    public BizException(Integer code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BizException(Integer code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BizException(Integer code, Object data) {
        super(ERROR_MSG);
        this.code = code;
        this.data = data;
    }

    public BizException(Object data) {
        super(ERROR_MSG);
        this.code = CODE;
        this.data = data;
    }
}
