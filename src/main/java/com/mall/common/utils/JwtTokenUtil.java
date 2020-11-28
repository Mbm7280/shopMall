package com.mall.common.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * @Author maiBangMin
 * @Description [JwtToken生成的工具类]
 * @Date 9:26 下午 2020/11/28
 * @Version 1.0
 **/
@Component
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /*
     * @Author maiBangMin
     * @Description [根据负责生成JWT的token]
     * @Date 9:28 下午
     * @Version 1.0
     **/
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /*
     * @Author maiBangMin
     * @Description [从token中获取JWT中的负载]
     * @Date 9:30 下午
     * @Version 1.0
     **/
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}",token);
        }
        return claims;
    }

    /*
     * @Author maiBangMin
     * @Description [从token中获取登录用户名]
     * @Date 9:31 下午
     * @Version 1.0
     **/
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username =  claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /*
     * @Author maiBangMin
     * @Description 验证token是否还有效
     * @Date 9:31 下午 2020/11/28
     * @Param [token-客户端传入的token, userDetails-从数据库中查询出来的用户信息]
     * @return boolean
     **/
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /*
     * @Author maiBangMin
     * @Description [判断token是否已经失效]
     * @Date 9:32 下午
     * @Version 1.0
     **/
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /*
     * @Author maiBangMin
     * @Description [从token中获取过期时间]
     * @Date 9:32 下午
     * @Version 1.0
     **/
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /*
     * @Author maiBangMin
     * @Description [根据用户信息生成token]
     * @Date 9:34 下午
     * @Version 1.0
     **/
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /*
     * @Author maiBangMin
     * @Description [刷新token]
     * @Date 9:34 下午
     * @Version 1.0
     **/
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


    /*
     * @Author maiBangMin
     * @Description [判断token是否可以被刷新]
     * @Date 9:34 下午
     * @Version 1.0
     **/
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /*
     * @Author maiBangMin
     * @Description [生成token的过期时间]
     * @Date 9:29 下午
     * @Version 1.0
     **/
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }




}
