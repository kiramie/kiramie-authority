import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiramie.feignDemo.FeignDemoApplication;
import com.kiramie.feignDemo.entity.PtCoupon;
import com.kiramie.feignDemo.openFeign.T1Feign;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author yangbin
 * @since 2022/11/29
 **/
@SpringBootTest(classes = FeignDemoApplication.class)
@Slf4j
public class FeignDemoTest {

    @Resource
    private T1Feign t1Feign;

    @Test
    public void t1() {
        JSONObject jsonObject = t1Feign.m1();
        log.info("t1:{}", jsonObject);
    }

    @Test
    public void t2() {
        JSONObject jsonObject = t1Feign.selectPtCoupon(1, 20);
        log.info("t2:{}", JSONObject.toJSONString(jsonObject, SerializerFeature.PrettyFormat));
    }

    @Test
    public void t3() throws JsonProcessingException {
        PtCoupon ptCoupon1 = new PtCoupon() {{
            setId("cp2388989224");
            setCouponName("test优惠券");
            setCouponDesc("test描述");
            setCouponType(2);
            setCreatedAt("2021-11-16T18:24:38");
            //setUpdatedAt(LocalDateTime.now());
        }};
        log.info(">>>\n{}", JSONObject.toJSONString(ptCoupon1));
        //ObjectMapper objectMapper = new ObjectMapper();
        //String s = objectMapper.writeValueAsString(ptCoupon1);
        //log.info(">>>\n{}", s);
        PtCoupon ptCoupon = t1Feign.m3(ptCoupon1);
        log.info("t3:{}", ptCoupon);
    }
}
