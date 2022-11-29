import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kiramie.authority.AuthServerApplication;
import com.kiramie.authority.dto.demo.Demo1Dto;
import com.kiramie.redis.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author yangbin
 * @since 2022/11/25
 **/
@SpringBootTest(classes = AuthServerApplication.class)
public class RedisTest {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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
        redisUtil.set("common:dict:yui", new JSONObject(){{
            put("k1", new JSONArray(){{
                add("hello");
                add("world");
            }});
        }});
        redisUtil.set("common:dict:bnj", new JSONObject(){{
            put("k2", "hello world");
        }});
    }

    @Test
    public void t4(){
        Integer i = 100;
        System.out.println(i >> 2);//4
    }

    @Test
    public void t5(){
        long l = System.currentTimeMillis();
        System.out.println(l);
        redisUtil.zadd("granwin:function:ZSetQueue:0", l, "granwin:function:timerQueue:upgrade-device");
    }

    @Test
    public void t6(){
        BoundListOperations<String, Object> list1 = redisTemplate.boundListOps("list1");
        //list1.leftPushAll("aa", "bb", "cc", "ee", "dd");
        list1.leftPushAll("bb", "ee");
    }

    //@Test
    //public void t7(){
    //    BoundListOperations<String, Object> list1 = redisTemplate.boundListOps("list1");
    //    list1.rightPop()
    //}
}
