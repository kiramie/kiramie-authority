package com.kiramie.feignDemo.openFeign;

import com.alibaba.fastjson.JSONObject;
import com.kiramie.feignDemo.entity.PtCoupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * https://blog.csdn.net/Hatakefiftyfifty/article/details/124790463
 *
 * @author yangbin
 * @since 2022/11/29
 **/
@Component
@FeignClient(name = "T1", url = "http://127.0.0.1:8079/t1")
public interface T1Feign {

    @GetMapping("/m1")
    JSONObject m1();

    @PostMapping("/m2")
    JSONObject m2(@RequestBody JSONObject jsonObject);

    @PostMapping("/m3")
    PtCoupon m3(@RequestBody PtCoupon ptCoupon);

    @GetMapping("/selectPtCoupon/{page}/{size}")
    JSONObject selectPtCoupon(@PathVariable Integer page, @PathVariable Integer size);
}
