import cn.hutool.core.io.FileUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

/**
 * @author yangbin
 * @since 2022/11/30
 **/
@Slf4j
public class JwtTest {

    @Test
    public void t1() {
        long l = System.currentTimeMillis();
        String jwtStr = JWT.create()
                .withIssuedAt(new Date(l))
                .withExpiresAt(new Date(l + 60 * 1000 * 60 * 24 * 7))
                //.withSubject()
                //.withIssuer()
                .withClaim("k1", "v1")
                .sign(Algorithm.HMAC256("kiramie"));
        log.info("jwtStr 【{}】", jwtStr);
    }

    @Test
    public void t2() {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("kiramie"))
                .build()
                .verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJrMSI6InYxIiwiZXhwIjoxNjcwMzgzNTE0LCJpYXQiOjE2Njk3Nzg3MTR9.pYLVFD0VnxGBvK-ydbZt_eBPyydQXhciJZKLqQ1xOIE");
        log.info("decodedJWT 【{}】Claims【{}】Expired 【{}】", decodedJWT, decodedJWT.getClaims(), decodedJWT.getExpiresAt());
    }

    @Test
    public void t3() throws NoSuchAlgorithmException {
        //自定义 随机密码,  请修改这里
        String password = "kiramie";
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        FileUtil.writeBytes(publicKeyBytes, "./pub.key");
        FileUtil.writeBytes(privateKeyBytes, "./pri.key");
        log.info("completed...");
    }


    @Test
    public void t4() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance("RSA");

        InputStream priInputStream = getClass().getClassLoader().getResourceAsStream("pri.key");
        DataInputStream priDataInputStream = new DataInputStream(priInputStream);
        byte[] priBytes = new byte[priInputStream.available()];
        priDataInputStream.readFully(priBytes);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(priBytes);
        PrivateKey privateKey = kf.generatePrivate(pkcs8EncodedKeySpec);

        //InputStream pubInputStream = getClass().getClassLoader().getResourceAsStream("pub.key");
        //DataInputStream pubDataInputStream = new DataInputStream(pubInputStream);
        //byte[] pubBytes = new byte[pubInputStream.available()];
        //pubDataInputStream.readFully(pubBytes);
        //X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pubBytes);
        //PublicKey publicKey = kf.generatePublic(x509EncodedKeySpec);

        long l = System.currentTimeMillis();
        //JWT.create()
        //        .sign(Algorithm.RSA256((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey))

        String sign = JWT.create()
                .withSubject("testRsa256")
                .withClaim("k1", "v1")
                .withIssuedAt(new Date(l))
                .withExpiresAt(new Date(l + 60 * 1000 * 60 * 24 * 7))
                .sign(Algorithm.RSA256((RSAPrivateKey) privateKey));
        log.info("sign【{}】", sign);
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0UnNhMjU2IiwiazEiOiJ2MSIsImV4cCI6MTY3MDM5Mzk5MCwiaWF0IjoxNjY5Nzg5MTkwfQ.gZ4AZYa9-dMC25x462Kxd_N3sxNaTWkbWKp8MNHd3YCy0yqU_NUrPHw1JEE1MzZ5plmlF0ceSqn8L1kZfvDjAyTi8mVDTnaMBA9RI2b5tKxYhSwRz_f8hOxB7NvtuIec-qQ5PX1I1k30yp5XB97P56XAzyUmWOgxkoQOOglXuW8
    }

    @Test
    public void t5() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        InputStream pubInputStream = getClass().getClassLoader().getResourceAsStream("pub.key");
        DataInputStream pubDataInputStream = new DataInputStream(pubInputStream);
        byte[] pubBytes = new byte[pubInputStream.available()];
        pubDataInputStream.readFully(pubBytes);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pubBytes);
        PublicKey publicKey = kf.generatePublic(x509EncodedKeySpec);
        DecodedJWT decodedJWT = JWT.require(Algorithm.RSA256((RSAPublicKey) publicKey)).build()
                .verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0UnNhMjU2IiwiazEiOiJ2MSIsImV4cCI6MTY3MDM5Mzk5MCwiaWF0IjoxNjY5Nzg5MTkwfQ.gZ4AZYa9-dMC25x462Kxd_N3sxNaTWkbWKp8MNHd3YCy0yqU_NUrPHw1JEE1MzZ5plmlF0ceSqn8L1kZfvDjAyTi8mVDTnaMBA9RI2b5tKxYhSwRz_f8hOxB7NvtuIec-qQ5PX1I1k30yp5XB97P56XAzyUmWOgxkoQOOglXuW8");
        log.info("decodedJWT 【{}】Claims【{}】Expired 【{}】", decodedJWT, decodedJWT.getClaims(), decodedJWT.getExpiresAt());
    }
}
