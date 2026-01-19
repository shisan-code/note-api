package com.shisan.note.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Data
@Component
public class JwtTokenUtil {

    // 密钥（生产环境请使用环境变量或配置中心，且长度至少32位）
    @Value("${jwt.secret}")
    private String secret;

    // Token 过期时间（单位：秒），建议1小时
    @Value("${jwt.expiration}")
    private Long expiration;

    // 刷新 Token 过期时间（单位：秒），建议7天
    @Value("${jwt.refresh-expiration}")
    private Long refreshExpiration;

    /**
     * 生成签名密钥
     *
     * @author lijing
     * @Date 2026/1/15 10:29
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 从Token中获取用户名
     *
     * @author lijing
     * @Date 2026/1/15 10:29
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 从Token中获取过期时间
     *
     * @author lijing
     * @Date 2026/1/15 10:29
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 提取Token中的声明
     *
     * @author lijing
     * @Date 2026/1/15 10:29
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 提取所有声明
     *
     * @author lijing
     * @Date 2026/1/15 10:30
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 验证Token是否过期
     *
     * @author lijing
     * @Date 2026/1/15 10:29
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 生成访问Token
     *
     * @author lijing
     * @Date 2026/1/15 10:29
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(), expiration);
    }

    /**
     * 生成刷新Token
     *
     * @author lijing
     * @Date 2026/1/15 10:28
     */
    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(), refreshExpiration);
    }

    /**
     * 创建Token
     *
     * @author lijing
     * @Date 2026/1/15 10:28
     */
    private String createToken(Map<String, Object> claims, String subject, Long expireTime) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 验证Token有效性
     *
     * @author lijing
     * @Date 2026/1/15 10:28
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}