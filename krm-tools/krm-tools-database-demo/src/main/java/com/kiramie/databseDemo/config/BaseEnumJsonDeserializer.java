package com.kiramie.databseDemo.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kiramie.databseDemo._enum.IEnumCode;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.MessageFormat;

/**
 * @author yangbin
 * @since 2022/11/24
 **/
public class BaseEnumJsonDeserializer extends JsonDeserializer {
    @Override
    public IEnumCode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        IEnumCode result = null;
        String currentName = null;
        try {
            // 获取当前待反序列化的字段名称
            currentName = jsonParser.getCurrentName();
            // 通过反射获取当前字段
            Field declaredField = jsonParser.getCurrentValue().getClass().getDeclaredField(currentName);
            // 当对象类型是BaseEnum类型的子类
            if (IEnumCode.class.isAssignableFrom(declaredField.getType())) {
                // 枚举值
                Object[] enumConstants = declaredField.getType().getEnumConstants();
                for (Object obj : enumConstants) {
                    if (String.valueOf(((IEnumCode) obj).getCode()).equals(jsonParser.getText())) {
                        result = (IEnumCode) obj;
                        break;
                    }
                }
            }
            if (result == null) throw new RuntimeException();
        } catch (Exception e) {
            //String format = MessageFormat.format("BaseEnumJsonDeserializer反序列化异常, obj = {0}, currentName = {1}", jsonParser.getCurrentValue(), jsonParser.getCurrentName());
            String format = MessageFormat.format("BaseEnumJsonDeserializer反序列化异常, currentName = {0}", currentName);
            throw new RuntimeException(format);
        }
        return result;
    }
}
