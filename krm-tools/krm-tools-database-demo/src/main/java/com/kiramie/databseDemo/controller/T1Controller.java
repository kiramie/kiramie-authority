package com.kiramie.databseDemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiramie.databseDemo.entity.mysql1.PtCoupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangbin
 * @since 2022/11/23
 **/
@Slf4j
@RestController
@RequestMapping("/t1")
public class T1Controller {

    @GetMapping("/m1")
    public JSONObject m1() {
        return new JSONObject() {{
            put("code", 0);
            put("data", new JSONObject() {{
                put("k1", "v1");
                put("k2", "v2");
            }});
            put("msg", "ok");
        }};
    }

    @PostMapping("/m2")
    public JSONObject m2(@RequestBody JSONObject jsonObject) {
        log.info("m2:{}", jsonObject.toJSONString());
        return jsonObject;
    }

    @PostMapping("/m3")
    public PtCoupon m3(@RequestBody PtCoupon ptCoupon) throws JsonProcessingException {
        log.info(ptCoupon.getCouponType().getDesc());
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(ptCoupon);
        log.info(s);
        return ptCoupon;
    }
}
