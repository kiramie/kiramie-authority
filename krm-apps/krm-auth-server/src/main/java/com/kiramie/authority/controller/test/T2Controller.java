package com.kiramie.authority.controller.test;

import com.alibaba.fastjson.JSONObject;
import com.kiramie.authority.dto.auth.VueRouter;
import com.kiramie.authority.entity.auth.Menu;
import com.kiramie.dozer.DozerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangbin
 * @since 2022/11/22
 **/
@Api(tags = "t2")
@Slf4j
@RestController
@RequestMapping("t2")
public class T2Controller {

    @Resource
    private DozerUtils dozerUtils;

    @ApiOperation(value = "m1")
    @PostMapping("/m1")
    public JSONObject m1(@RequestBody JSONObject jsonObject) {
        System.out.println("m1: " + jsonObject);
        return jsonObject;
    }

    @PostMapping("/m2")
    public String m2(@RequestBody Menu menu) {
        VueRouter vueRouter = dozerUtils.map(menu, VueRouter.class);
        String s = JSONObject.toJSONString(vueRouter);
        log.info("vueRouter: {}", s);
        return s;
    }

    @GetMapping("/m3")
    public String m3(@RequestParam("ids[]") List<Long> ids){
        String s = JSONObject.toJSONString(ids);
        log.info("m3 {}", s);
        return s;
    }

}
