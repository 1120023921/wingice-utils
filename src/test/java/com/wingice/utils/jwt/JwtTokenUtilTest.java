package com.wingice.utils.jwt;

import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 胡昊
 * Description:
 * Date: 2019/5/22
 * Time: 20:40
 * Create: DoubleH
 */
public class JwtTokenUtilTest {

    private Long expire = 3600000L;
    private String secret = "9ba45bfd500642328ec03ad8ef1b6e75";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiLpmYjnjq7lj7YiLCJnZW5kZXIiOiLlpbMiLCJleHAiOjE1NTg1MzMwMDYsImpvYk51bWJlciI6IjIwMTUwMTQwOTMifQ.tFrEFDUd5ViVjm7HZ9vOe-qS-O4BArQq_5gr_T1GUXI1Gc2JRcZ32bUT9v9GgQEBGzzld6nhu9aFZ2nuhTde5Q";

    @Test
    public void generateToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", "陈玮叶");
        claims.put("gender", "女");
        claims.put("jobNumber", "2015014093");
        String result = JwtTokenUtil.generateToken(claims, secret, expire);
        System.out.println(result);
    }

    @Test
    public void getClaimsFromToken() {
        Claims claims = JwtTokenUtil.getClaimsFromToken(token, secret);
        for (Map.Entry entry : claims.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    @Test
    public void getUsernameFromToken() {
        System.out.println(JwtTokenUtil.getUsernameFromToken(token, secret));
    }

    @Test
    public void isTokenExpired() {
        Boolean expired = JwtTokenUtil.isTokenExpired(token, secret);
        System.out.println(expired);
    }

    @Test
    public void refreshToken() {
        String newToken = JwtTokenUtil.refreshToken(token, secret, expire);
        System.out.println(newToken);
    }
}
