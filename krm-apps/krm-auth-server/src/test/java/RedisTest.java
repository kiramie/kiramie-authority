import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kiramie.authority.AuthServerApplication;
import com.kiramie.authority.dto.demo.Demo1Dto;
import com.kiramie.redis.RedisUtil;
import com.kiramie.redis.RedissonDistributedLocker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author yangbin
 * @since 2022/11/25
 **/
@SpringBootTest(classes = AuthServerApplication.class)
@Slf4j
public class RedisTest {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonDistributedLocker redissonDistributedLocker;

    private int l = 0;

    @Test
    public void t1() {
        redisUtil.set("k1", "value1", 30);
    }

    @Test
    public void t2() {
        redisUtil.hset("testHash", "t1", new Demo1Dto() {{
            setId(8989L);
            setName("jijfj诶uv额我");
            setAge(34);
            setGender(true);
            setCreateTime(LocalDateTime.now());
        }}, 120);
    }

    @Test
    public void t3() {
        redisUtil.set("common:dict:yui", new JSONObject() {{
            put("k1", new JSONArray() {{
                add("hello");
                add("world");
            }});
        }});
        redisUtil.set("common:dict:bnj", new JSONObject() {{
            put("k2", "hello world");
        }});
    }

    @Test
    public void t4() {
        Integer i = 100;
        System.out.println(i >> 2);//4
    }

    @Test
    public void t5() {
        long l = System.currentTimeMillis();
        System.out.println(l);
        redisUtil.zadd("granwin:function:ZSetQueue:0", l, "granwin:function:timerQueue:upgrade-device");
    }

    @Test
    public void t6() {
        BoundListOperations<String, Object> list1 = redisTemplate.boundListOps("list1");
        //list1.leftPushAll("aa", "bb", "cc", "ee", "dd");
        list1.leftPushAll("bb", "ee");
    }

    //@Test
    //public void t7(){
    //    BoundListOperations<String, Object> list1 = redisTemplate.boundListOps("list1");
    //    list1.rightPop()
    //}

    private void add(int size) {
        for (int i = 0; i < size; i++) {
            l++;
        }
    }

    @Test
    public void t8() {
        log.info(">>>l: {}", l);
        add(10000);
        add(10000);
        add(10000);
        add(10000);
        add(10000);
        log.info("<<<l: {}", l);
    }

    @Test
    public void t9() {
        log.info(">>>l: {}", l);
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> add(10000));
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> add(10000));
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> add(10000));
        CompletableFuture<Void> future4 = CompletableFuture.runAsync(() -> add(10000));
        CompletableFuture<Void> future5 = CompletableFuture.runAsync(() -> add(10000));
        future1.join();
        future2.join();
        future3.join();
        future4.join();
        future5.join();
        log.info("<<<l: {}", l);
    }

    private void addWithRedisson(int size) {
        for (int i = 0; i < size; i++) {
            boolean lock = redissonDistributedLocker.tryLock("addWithRedissonLock", TimeUnit.SECONDS, 1, 3600);
            if (lock) {
                try {
                    l++;
                } finally {
                    redissonDistributedLocker.unlock("addWithRedissonLock");
                }
            }
        }
    }

    @Test
    public void t10() {
        log.info(">>>l: {}", l);
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> addWithRedisson(10000));
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> addWithRedisson(10000));
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> addWithRedisson(10000));
        CompletableFuture<Void> future4 = CompletableFuture.runAsync(() -> addWithRedisson(10000));
        CompletableFuture<Void> future5 = CompletableFuture.runAsync(() -> addWithRedisson(10000));
        future1.join();
        future2.join();
        future3.join();
        future4.join();
        future5.join();
        log.info("<<<l: {}", l);
    }
}
