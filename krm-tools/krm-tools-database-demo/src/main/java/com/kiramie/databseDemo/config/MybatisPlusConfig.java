package com.kiramie.databseDemo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kiramie.databseDemo._enum.IEnumCode;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * MybatisPlus Config
 *
 * @author chensicong
 * @since 2021-07-20
 **/
@Configuration
public class MybatisPlusConfig {

    /**
     * 默认填充
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                LocalDateTime now = LocalDateTime.now();
                this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, now);
                this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, now);
                this.strictInsertFill(metaObject, "gmtCreate", LocalDateTime.class, now);
                this.strictInsertFill(metaObject, "gmtModified", LocalDateTime.class, now);
            }
            @Override
            public void updateFill(MetaObject metaObject) {
                LocalDateTime now = LocalDateTime.now();
                this.strictUpdateFill(metaObject, "updatedAt", LocalDateTime.class, now);
                this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime.class, now);
            }
        };
    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }

    //@Bean
    //public ConfigurationCustomizer configurationCustomizer() {
    //    return configuration -> configuration.setUseDeprecatedExecutor(false);
    //}

    //@Bean
    //public JsonDeserializer baseEnumJsonDeserializer(){
    //    return new JsonDeserializer() {
    //        @Override
    //        public IEnumCode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    //            IEnumCode result = null;
    //            try {
    //                // 获取当前待反序列化的字段名称
    //                String currentName = jsonParser.getCurrentName();
    //                // 通过反射获取当前字段
    //                Field declaredField = jsonParser.getCurrentValue().getClass().getDeclaredField(currentName);
    //                // 当对象类型是BaseEnum类型的子类
    //                if (IEnumCode.class.isAssignableFrom(declaredField.getType())) {
    //                    // 枚举值
    //                    Object[] enumConstants = declaredField.getType().getEnumConstants();
    //                    for (Object obj : enumConstants) {
    //                        if (String.valueOf(((IEnumCode) obj).getCode()).equals(jsonParser.getText())) {
    //                            result = (IEnumCode) obj;
    //                            break;
    //                        }
    //                    }
    //                }
    //            } catch (NoSuchFieldException e) {
    //                String format = MessageFormat.format("BaseEnumJsonDeserializer反序列化异常, obj = {0}, currentName = {1}", jsonParser.getCurrentValue(), jsonParser.getCurrentName());
    //                throw new RuntimeException(format);
    //            }
    //            return result;
    //        }
    //    };
    //}

}
