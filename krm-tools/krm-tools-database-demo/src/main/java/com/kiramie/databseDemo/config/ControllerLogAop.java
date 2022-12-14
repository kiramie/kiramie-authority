package com.kiramie.databseDemo.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author gxfeng
 * @description 日志打印
 * @date 2021/6/29
 */
@Slf4j
@Aspect
@Component
public class ControllerLogAop {

    /**
     * 定义拦截规则
     */
    @Pointcut("execution(public * com.kiramie.databseDemo.controller..*.*(..))")
    public void controllerMethodPointcut() {
    }

    /**
     * 拦截器具体实现
     * 指定拦截器规则；也可以直接把“execution(* com.xjj………)”写进这里
     *
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果）
     */
    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //获取被拦截的方法
        Method method = signature.getMethod();
        //获取被拦截的方法名
        String methodName = method.getName();
        String classMethod = method.getDeclaringClass().getName() + "." + methodName + "()";
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest httpRequest = attributes.getRequest();
        String queryString = httpRequest.getQueryString();
        String uri = httpRequest.getRequestURI();
        Object result = null;
        // 保存所有请求参数，用于输出到日志中
        Set<Object> allParams = new LinkedHashSet<>();
        if (queryString != null) {
            allParams.add("queryString:" + queryString);
        }
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Map<?, ?>) {
                //提取方法中的MAP参数，用于记录进日志中
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) arg;
                allParams.add(map);
            } else if (arg instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) arg;
                //获取query string 或 posted form data参数
                Map<String, String[]> paramMap = request.getParameterMap();
                if (paramMap != null && paramMap.size() > 0) {
                    allParams.add(paramMap);
                }
            } else if (arg instanceof HttpServletResponse) {
            } else if (arg instanceof MultipartFile) {
            } else {
                allParams.add(arg);
            }
        }
        String uuid = UUID.randomUUID().toString();
        log.info("\n请求开始 ----------->\nUUID:【{}】，URI:【{}】，方法:【{}】，参数:【{}】", uuid, uri, classMethod, JSONObject.toJSONString(allParams, SerializerFeature.DisableCircularReferenceDetect));
        result = pjp.proceed(args);
        log.info("\nUUID:【{}】，URI:【{}】，方法:【{}】，耗时:【{}ms】，返回:【{}】\n<----------- 请求结束", uuid, uri, classMethod, System.currentTimeMillis() - beginTime, JSONObject.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect));
        return result;
    }
}
