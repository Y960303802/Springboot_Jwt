package com.xizi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    //签名
    private static final  String SIGN="XIZI";

    /**
     * 生成token header.payload.sign
     *
     */
    public  static String getToken(Map<String,String> map){

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);  //七天

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v );
        });
        //指定过期时间
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
        return  token;
    }

    /**
     * 验证token
     */
    public  static DecodedJWT verify(String token){
       //没有抛出异常 验证通过
       return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);

    }


    /**
     * 获取token信息方法
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }




}
