```java
package com.gongsibao.common.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gongsibao.dto.UserInfoDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author gxfeng
 * @Description: token 工具类
 * @date 2020/9/8
 * <p>
 * <p>
 * 注意jwt只是对相关信息进行了base64,所以可以在jwt.io官网或者其他网站解析
 * payload里面应该剔除敏感信息
 * </p>
 */
@Component
public class JwtHelper {

    // 签名信息 不能泄露 否则可能会被伪造token
    private static String SEC = "token.secret";
    // 过期时间单位毫秒
    private static long EXP = 60 * 1000 * 60 * 24 * 7;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        String secret = env.getProperty("jwt.auth.secret");
        if (StringUtils.isNotBlank(secret)) {
            SEC = secret;
        } else {
            SEC = "token.secret";
        }
        Long expire = env.getProperty("jwt.auth.expire", Long.class);
        if (expire != null) {
            EXP = expire;
        } else {
            EXP = 60 * 1000 * 60 * 24 * 7;
        }
    }

    public static String generateToken(UserInfoDTO userInfoDTO) {
        long nowMillis = System.currentTimeMillis();
        return JWT.create()
                // 签发时间
                .withIssuedAt(new Date(nowMillis))
                // 过期时间
                .withExpiresAt(new Date(nowMillis + EXP))
                .withSubject(userInfoDTO.getId())
                .withIssuer(userInfoDTO.getParkId())
                // 签名信息
                .sign(Algorithm.HMAC512(SEC));
    }

    public static DecodedJWT getDecodedJWTmFromToken(String token) {
        return JWT
                .require(Algorithm.HMAC512(SEC))
                .build()
                .verify(token);
    }

    public static <T> T getFromToken(String token, Function<DecodedJWT, T> function) {
        DecodedJWT decodedJWT = getDecodedJWTmFromToken(token);
        return function.apply(decodedJWT);
    }

    public static Date getIssuedAtDateFromToken(String token) {
        return getFromToken(token, DecodedJWT::getIssuedAt);
    }

    public static Date getExpiresAtDateFromToken(String token) {
        return getFromToken(token, DecodedJWT::getExpiresAt);
    }

    private static Boolean isTokenExpired(String token) {
        final Date expiresAt = getExpiresAtDateFromToken(token);
        return expiresAt.before(new Date());
    }

    private static Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && !lastPasswordReset.equals("") && created.before(lastPasswordReset));
    }

    public static String getUserIdFromToken(String token) {
        return getFromToken(token, DecodedJWT::getSubject);
    }

    public static String getParkIdFromToken(String token) {
        return getFromToken(token, DecodedJWT::getIssuer);
    }

    public static boolean validateToken(String token, Date lastPassWordResetDate) {
        final Date created = getIssuedAtDateFromToken(token);
//        final Date expiration = getExpirationDateFromToken(token);
//        如果token存在，且token创建日期 > 最后修改密码的日期 则代表token有效
        return !isTokenExpired(token) && !isCreatedBeforeLastPasswordReset(created, lastPassWordResetDate);
    }
}
```
