package com.kiramie.authority.controller.test;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangbin
 * @since 2022/11/21
 **/
@Api(tags = "公司信息查询")
@RestController
@RequestMapping("/t1")
public class T1Controller {

    @ApiOperation(value = "模糊查询公司信息")
    @PostMapping("/m1")
    public JSONObject m1(@RequestBody JSONObject jsonObject) {
        System.out.println("m1: " + jsonObject);
        return jsonObject;
    }

    @ApiOperation(value = "查询公司信息")
    @PostMapping("/m2")
    public Param1 m2(@RequestBody @Validated Param1 param1) {
        System.out.println("m2: " + JSONObject.toJSONString(param1));
        return param1;
    }
}
