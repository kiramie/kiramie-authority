package com.kiramie.redis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangbin
 * @since 2022/11/25
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RedisConfig.class, RedissonDistributedLocker.class, RedisUtil.class})
public @interface EnableRedisConfig {
}
