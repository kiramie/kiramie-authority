package com.kiramie.databseDemo._enum;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yangbin
 * @since 2022/11/24
 **/
@AllArgsConstructor
@Getter
public enum CouponTypeEnum implements IEnumCode<Integer> {

    QT(1, "其他"),
    ZKQ(2, "折扣券"),
    DJQ(3, "代金券"),
    MJQ(4, "满减券")
    ;

    @EnumValue//mp序列化反序列化
    private final Integer type;

    private final String desc;

    @JsonValue//jackson序列化
    @JSONField//fastJson序列化
    public Integer getType() {
        return type;
    }

    @JSONCreator//fastJson反序列化
    public static CouponTypeEnum of(Integer code){
        for (CouponTypeEnum value : values()) {
            if (value.getType().equals(code)) return value;
        }
        return null;
    }

    //jackson反序列化，具体的实体上要加上反序列配置@JsonDeserialize(using = BaseEnumJsonDeserializer.class)
    @Override
    public Integer getCode() {
        return type;
    }
}
