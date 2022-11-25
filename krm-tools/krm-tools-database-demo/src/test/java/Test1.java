import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiramie.databseDemo.DatabaseDemoApplication;
import com.kiramie.databseDemo._enum.CouponTypeEnum;
import com.kiramie.databseDemo.entity.mysql1.PtCoupon;
import com.kiramie.databseDemo.entity.pg1.device.DeviceInfo;
import com.kiramie.databseDemo.mapper.mysql1.PtCouponMapper;
import com.kiramie.databseDemo.mapper.pg1.device.DeviceInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author yangbin
 * @since 2022/11/23
 **/
@SpringBootTest(classes = DatabaseDemoApplication.class)
@Slf4j
public class Test1 {

    @Resource
    private PtCouponMapper ptCouponMapper;

    @Resource
    private DeviceInfoMapper deviceInfoMapper;

    @Test
    public void t1() {
        PtCoupon ptCoupon1 = ptCouponMapper.selectById("CP21111606814841043");
        log.debug("debug...");
        log.warn("warn...");
        log.error("error...");
        log.info("t1:{}", JSONObject.toJSONString(ptCoupon1));
    }

    @Test
    public void t2() {
        DeviceInfo deviceInfo1 = deviceInfoMapper.selectById(727317912045330432L);
        log.info("t2:{}", JSONObject.toJSONString(deviceInfo1));
    }

    @Test
    public void t3() {
        PtCoupon ptCoupon = new PtCoupon() {{
            setTemplateId("temp003");
            setCouponName("测试优惠券4");
            setCouponDesc("描述1。。。");
            setCouponType(CouponTypeEnum.DJQ);
        }};
        log.info("t3 {}", ptCouponMapper.insert(ptCoupon));
    }

    @Test
    public void t4() {
        Page<PtCoupon> ptCouponPage = ptCouponMapper.selectPage(new Page<PtCoupon>().setCurrent(1L).setSize(100L),
                Wrappers.<PtCoupon>lambdaQuery().isNotNull(PtCoupon::getCouponType).orderByDesc(PtCoupon::getCreatedAt));
        log.info("t4, {}", ptCouponPage.getRecords());
        //log.info("t4\n{}", JSONObject.toJSONString(ptCouponPage, SerializerFeature.PrettyFormat));
    }

    @Test
    public void t5() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(CouponTypeEnum.DJQ);
        System.out.println(s);
    }

    @Test
    public void t6() {
        PtCoupon ptCoupon = new PtCoupon() {{
            setId("pt4343");
            setCouponName("优惠34");
            setCreatedAt(LocalDateTime.now());
            setCouponType(CouponTypeEnum.MJQ);
        }};
        String s = JSONObject.toJSONString(ptCoupon);
        log.info("t6:\n{}", s);
    }

    @Test
    public void t7() {
        String s = "{\"couponName\":\"优惠34\",\"couponType\":4,\"createdAt\":\"2022-11-24T15:42:57\",\"id\":\"pt4343\"}";
        PtCoupon ptCoupon = JSONObject.parseObject(s, PtCoupon.class);
        System.out.println(ptCoupon.getCouponType().getDesc());
        System.out.println(JSONObject.toJSONString(ptCoupon));
    }

    @Test
    public void t8() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PtCoupon ptCoupon = new PtCoupon() {{
            setId("pt4343");
            setCouponName("优惠34");
            setCouponType(CouponTypeEnum.MJQ);
        }};
        String s = objectMapper.writeValueAsString(ptCoupon);
        log.info("t8:\n{}", s);
    }

    @Test
    public void t9() throws JsonProcessingException {
        String s = "{\"id\":\"pt4343\",\"templateId\":null,\"couponName\":\"优惠34\",\"couponDesc\":null,\"useDesc\":null,\"couponType\":4,\"discountAmount\":null,\"discountRatio\":null,\"demandAmount\":null,\"clientScope\":null,\"productScope\":null,\"taskId\":null,\"orgId\":null,\"userId\":null,\"validTimeStart\":null,\"validTimeEnd\":null,\"useState\":null,\"useTime\":null,\"refOrder\":null,\"useClient\":null,\"useProduct\":null,\"remark\":null,\"memo\":null,\"useUserId\":null,\"useUsername\":null,\"createdBy\":null,\"updatedBy\":null,\"createdByName\":null,\"updatedByName\":null,\"updatedAt\":null}";
        ObjectMapper objectMapper = new ObjectMapper();
        PtCoupon ptCoupon = objectMapper.readValue(s, PtCoupon.class);
        log.info(ptCoupon.getCouponType().getDesc());
        log.info("t9:\n{}", objectMapper.writeValueAsString(ptCoupon));
    }

}
