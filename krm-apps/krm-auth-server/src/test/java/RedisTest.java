import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kiramie.authority.AuthServerApplication;
import com.kiramie.authority.dto.demo.Demo1Dto;
import com.kiramie.redis.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
}
