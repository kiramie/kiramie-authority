package com.kiramie.databseDemo.config;

import com.kiramie.common.handler.DefaultGlobalExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yangbin
 * @since 2022/12/2
 **/
//@Configuration
//@ControllerAdvice(annotations = {RestController.class, Controller.class})
@RestControllerAdvice
public class ExceptionConfiguration extends DefaultGlobalExceptionHandler {
}
