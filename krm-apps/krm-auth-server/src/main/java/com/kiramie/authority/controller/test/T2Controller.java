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
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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

    @GetMapping("/m4")
    public String m4(){
        log.info("t1 start...");
        CountDownLatch cdl = new CountDownLatch(3);
        CompletableFuture<Boolean> powerStateFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("powerStateFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }).applyToEitherAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("powerStateFuture timeout...{}", 6000);
            return false;
        }), Function.identity());

        CompletableFuture<Integer> brightnessFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("brightnessFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 80;
        }).applyToEitherAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("brightnessFuture timeout...{}", 6000);
            return 1;
        }), Function.identity());

        CompletableFuture<Integer> colorTemperatureFuture = CompletableFuture.supplyAsync(() -> {
            try {
                int second = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(second);
                log.info("colorTemperatureFuture...{}", second);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3600;
        }).applyToEitherAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("colorTemperatureFuture timeout...{}", 6000);
            return 1000;
        }), Function.identity());
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject() {{
            put("powerState", powerStateFuture.join());
            put("brightness", brightnessFuture.join());
            put("colorTemperature", colorTemperatureFuture.join());
        }};
        log.info("t1 end...{}", jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

}
