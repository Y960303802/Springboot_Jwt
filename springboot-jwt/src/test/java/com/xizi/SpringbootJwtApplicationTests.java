package com.xizi;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootJwtApplicationTests {

    @Test
    void contextLoads() {
        HashMap<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,60);

        String token = JWT.create()
                .withHeader(map) //head
                .withClaim("userId", 21)  //payload
                .withClaim("username", "xizi")
                .withExpiresAt(instance.getTime())  //过期时间 20秒之后
                .sign(Algorithm.HMAC256("QWER"));//签名
        System.out.println("-------------------------");
        System.out.println(token);
        System.out.println("-------------------------");
    }

    //验证
    @Test
    public void test(){
        //创建验证对象
        Verification verification = JWT.require(Algorithm.HMAC256("QWER"));//签名
        JWTVerifier jwtVerifier = verification.build();
        //token
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDg3NzQwMjEsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGl6aSJ9.eo0iGbk4aZRyXcVH3_8GuxlTULn9VeOnGfqMeRFRV5U");
        //获取payload中存储的信息
      Claim userId = verify.getClaim("userId");
        Claim username = verify.getClaim("username");
        System.out.println(userId.asInt());
        System.out.println(username.asString());
        System.out.println("过期时间："+verify.getExpiresAt());
    }


}
