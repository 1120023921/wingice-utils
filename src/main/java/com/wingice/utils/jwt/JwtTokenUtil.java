package com.wingice.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * @author 胡昊
 * Description: Jwt工具类
 * Date: 2019/5/22
 * Time: 20:30
 * Create: DoubleH
 */
public class JwtTokenUtil {

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @param secret 密钥
     * @param expire 过期时间
     * @return 令牌
     */
    public static String generateToken(Map<String, Object> claims, String secret, Long expire) {
        final Date expirationDate = new Date(System.currentTimeMillis() + expire);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token  令牌
     * @param secret 密钥
     * @return 数据声明
     */
    public static Claims getClaimsFromToken(String token, String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token  令牌
     * @param secret 密钥
     * @return 用户名
     */
    public static String getUsernameFromToken(String token, String secret) {
        return getClaimsFromToken(token, secret).getSubject();
    }

    /**
     * 判断令牌是否过期
     *
     * @param token  令牌
     * @param secret 密钥
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token, String secret) {
        try {
            final Claims claims = getClaimsFromToken(token, secret);
            final Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return Boolean.TRUE;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token  原令牌
     * @param secret 密钥
     * @param expire 过期时间
     * @return 新令牌
     */
    public static String refreshToken(String token, String secret, Long expire) {
        final Claims claims = getClaimsFromToken(token, secret);
        claims.put("created", new Date());
        return generateToken(claims, secret, expire);
    }
}
