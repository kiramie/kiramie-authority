package com.kiramie.common.handler;

import cn.hutool.core.util.StrUtil;
import com.kiramie.core.base.R;
import com.kiramie.core.exception.BizException;
import com.kiramie.core.exception.ExceptionCode;
import com.kiramie.core.util.StrPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 全局异常处理配置类
 */
//@ControllerAdvice(annotations = {RestController.class, Controller.class})
//@ResponseBody
@Slf4j
public abstract class DefaultGlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public R<String> bizException(BizException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] BizException:", request.getRequestURI(), ex);
        return R.result(ex.getCode(), StrPool.EMPTY, ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] HttpMessageNotReadableException:", request.getRequestURI(), ex);
        String message = ex.getMessage();
        if (StrUtil.containsAny(message, "Could not read document:")) {
            String msg = String.format("无法正确的解析json类型的参数：%s", StrUtil.subBetween(message, "Could not read document:", " at "));
            return R.result(ExceptionCode.PARAM_EX.getCode(), StrPool.EMPTY, msg);
        }
        return R.result(ExceptionCode.PARAM_EX.getCode(), StrPool.EMPTY, ExceptionCode.PARAM_EX.getMsg());
    }

    @ExceptionHandler(BindException.class)
    public R bindException(BindException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] BindException:", request.getRequestURI(), ex);
        try {
            String msgs = ex.getBindingResult().getFieldError().getDefaultMessage();
            if (StrUtil.isNotEmpty(msgs)) {
                return R.result(ExceptionCode.PARAM_EX.getCode(), StrPool.EMPTY, msgs);
            }
        } catch (Exception ee) {
        }
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach((oe) ->
                msg.append("参数:[").append(oe.getObjectName())
                        .append(".").append(oe.getField())
                        .append("]的传入值:[").append(oe.getRejectedValue()).append("]与预期的字段类型不匹配.")
        );
        return R.result(ExceptionCode.PARAM_EX.getCode(), StrPool.EMPTY, msg.toString());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] MethodArgumentTypeMismatchException:", request.getRequestURI(), ex);
        MethodArgumentTypeMismatchException eee = (MethodArgumentTypeMismatchException) ex;
        StringBuilder msg = new StringBuilder("参数：[").append(eee.getName())
                .append("]的传入值：[").append(eee.getValue())
                .append("]与预期的字段类型：[").append(eee.getRequiredType().getName()).append("]不匹配");
        return R.result(ExceptionCode.PARAM_EX.getCode(), StrPool.EMPTY, msg.toString());
    }

    @ExceptionHandler(IllegalStateException.class)
    public R illegalStateException(IllegalStateException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] IllegalStateException:", request.getRequestURI(), ex);
        return R.result(ExceptionCode.ILLEGALA_ARGUMENT_EX.getCode(), StrPool.EMPTY, ExceptionCode.ILLEGALA_ARGUMENT_EX.getMsg());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R missingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] MissingServletRequestParameterException:", request.getRequestURI(), ex);
        StringBuilder msg = new StringBuilder().append("缺少必须的[").append(ex.getParameterType()).append("]类型的参数[").append(ex.getParameterName()).append("]");
        return R.result(ExceptionCode.ILLEGALA_ARGUMENT_EX.getCode(), StrPool.EMPTY, msg.toString());
    }

    @ExceptionHandler(NullPointerException.class)
    public R nullPointerException(NullPointerException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] NullPointerException:", request.getRequestURI(), ex);
        return R.result(ExceptionCode.NULL_POINT_EX.getCode(), StrPool.EMPTY, ExceptionCode.NULL_POINT_EX.getMsg());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public R illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] IllegalArgumentException:", request.getRequestURI(), ex);
        return R.result(ExceptionCode.ILLEGALA_ARGUMENT_EX.getCode(), StrPool.EMPTY, ExceptionCode.ILLEGALA_ARGUMENT_EX.getMsg());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public R httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] HttpMediaTypeNotSupportedException:", request.getRequestURI(), ex);
        MediaType contentType = ex.getContentType();
        if (contentType != null) {
            StringBuilder msg = new StringBuilder();
            msg.append("请求类型(Content-Type)[").append(contentType.toString()).append("] 与实际接口的请求类型不匹配");
            return R.result(ExceptionCode.MEDIA_TYPE_EX.getCode(), StrPool.EMPTY, msg.toString());
        }
        return R.result(ExceptionCode.MEDIA_TYPE_EX.getCode(), StrPool.EMPTY, "无效的Content-Type类型");
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public R missingServletRequestPartException(MissingServletRequestPartException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] MissingServletRequestPartException:", request.getRequestURI(), ex);
        return R.result(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), StrPool.EMPTY, ExceptionCode.REQUIRED_FILE_PARAM_EX.getMsg());
    }

    @ExceptionHandler(ServletException.class)
    public R servletException(ServletException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] ServletException:", request.getRequestURI(), ex);
        String msg = "UT010016: Not a multi part request";
        if (msg.equalsIgnoreCase(ex.getMessage())) {
            return R.result(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), StrPool.EMPTY, ExceptionCode.REQUIRED_FILE_PARAM_EX.getMsg());
        }
        return R.result(ExceptionCode.SYSTEM_BUSY.getCode(), StrPool.EMPTY, ex.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public R multipartException(MultipartException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] MultipartException:", request.getRequestURI(), ex);
        return R.result(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), StrPool.EMPTY, ExceptionCode.REQUIRED_FILE_PARAM_EX.getMsg());
    }

    /**
     * jsr 规范中的验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R<String> constraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] ConstraintViolationException:", request.getRequestURI(), ex);
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return R.result(ExceptionCode.BASE_VALID_PARAM.getCode(), StrPool.EMPTY, message);
    }

    /**
     * spring 封装的参数验证异常， 在conttoller中没有写result参数时，会进入
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] MethodArgumentNotValidException:", request.getRequestURI(), ex);
        return R.result(ExceptionCode.BASE_VALID_PARAM.getCode(), StrPool.EMPTY, ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 其他异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R<String> otherExceptionHandler(Exception ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] Exception:", request.getRequestURI(), ex);
        if (ex.getCause() instanceof BizException) {
            return this.bizException((BizException) ex.getCause(), request);
        }
        return R.result(ExceptionCode.SYSTEM_BUSY.getCode(), StrPool.EMPTY, ExceptionCode.SYSTEM_BUSY.getMsg());
    }


    /**
     * 返回状态码:405
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public R<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] HttpRequestMethodNotSupportedException:", request.getRequestURI(), ex);
        return R.result(ExceptionCode.METHOD_NOT_ALLOWED.getCode(), StrPool.EMPTY, ExceptionCode.METHOD_NOT_ALLOWED.getMsg());
    }


    @ExceptionHandler(PersistenceException.class)
    public R<String> persistenceException(PersistenceException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] PersistenceException:", request.getRequestURI(), ex);
        if (ex.getCause() instanceof BizException) {
            BizException cause = (BizException) ex.getCause();
            return R.result(cause.getCode(), StrPool.EMPTY, cause.getMessage());
        }
        return R.result(ExceptionCode.SQL_EX.getCode(), StrPool.EMPTY, ExceptionCode.SQL_EX.getMsg());
    }

    @ExceptionHandler(MyBatisSystemException.class)
    public R<String> myBatisSystemException(MyBatisSystemException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] PersistenceException:", request.getRequestURI(), ex);
        if (ex.getCause() instanceof PersistenceException) {
            return this.persistenceException((PersistenceException) ex.getCause(), request);
        }
        return R.result(ExceptionCode.SQL_EX.getCode(), StrPool.EMPTY, ExceptionCode.SQL_EX.getMsg());
    }

    @ExceptionHandler(SQLException.class)
    public R sqlException(SQLException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] SQLException:", request.getRequestURI(), ex);
        return R.result(ExceptionCode.SQL_EX.getCode(), StrPool.EMPTY, ExceptionCode.SQL_EX.getMsg());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public R dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        log.warn("RequestURI [{}] DataIntegrityViolationException:", request.getRequestURI(), ex);
        return R.result(ExceptionCode.SQL_EX.getCode(), StrPool.EMPTY, ExceptionCode.SQL_EX.getMsg());
    }

}
