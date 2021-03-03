package com.code.report.blog.infra.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.code.report.blog.infra.dto.UserDTO;
import com.code.report.blog.infra.exception.CommonException;

import java.util.Calendar;
import java.util.Map;

/**
 * @author zhaotianxin
 * @date 2021-02-07 11:27
 */
public class JWTTokeUtil {
    private static final String HMAC256 = "easy_blog";

    public static String createJWT(UserDTO userDTO, Map<String, Object> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,120);
        String token = JWT.create()
                .withClaim("id",userDTO.getId())//添加payload
                .withClaim("username",userDTO.getName())
                .withExpiresAt(instance.getTime())//设置过期时间
                .sign(Algorithm.HMAC256(HMAC256));
        return token;
    }

    public static  UserDTO decodeJWT(String token){
        //创建验证对象,这里使用的加密算法和密钥必须与生成TOKEN时的相同否则无法验证
        //验证JWT
        DecodedJWT decodedJWT = null;
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(HMAC256)).build();
            decodedJWT = jwtVerifier.verify(token);
        } catch (Exception e){
           throw new CommonException("error.token.illegal",e.getMessage());
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(decodedJWT.getClaim("id").asLong());
        userDTO.setName(decodedJWT.getClaim("username").asString());
        return userDTO;
    }
}
