package cn.shisan.utils;

import cn.shisan.vo.AuthUserVo;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
@Component
public class JwtTokenUtil {

    // 密钥（生产环境请使用环境变量或配置中心，且长度至少32位）
    @Value("${jwt.secret}")
    private String secret;

    // Token 过期时间（单位：毫秒）
    @Value("${jwt.expiration}")
    private Long expiration;

    // 刷新 Token 过期时间（单位：毫秒），建议7天
    @Value("${jwt.refresh-expiration}")
    private Long refreshExpiration;

    /**
     * 获取加密密钥
     *
     * @author lijing
     * @Date 2026/8/11 11:37
     */
    private SecretKey getSecretKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成访问Token
     *
     * @author lijing
     * @Date 2026/1/15 10:28
     */
    public String generateToken(AuthUserVo authUser) {
        // 全部基于UTC时间运算
        Instant nowUtc = Instant.now();
        Instant expireUtc = nowUtc.plusMillis(expiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("user", JSON.toJSONString(authUser));
        return Jwts.builder()
                .claims(claims)
                .subject(authUser.getUserName())
                .issuedAt(Date.from(nowUtc))
                .expiration(Date.from(expireUtc))
                .signWith(getSecretKey())
                .compact();
    }

    /**
     * 校验token合法性
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("令牌过期：{}", e.getMessage());
        } catch (io.jsonwebtoken.security.SignatureException e) {
            log.info("签名错误、密钥不一致、token篡改：{}", e.getMessage());
            // 签名错误、密钥不一致、token篡改
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            log.info("格式错误、空token：{}", e.getMessage());
        }
        return false;
    }

    /**
     * 从Token中获取 用户信息
     *
     * @author lijing
     * @Date 2026/1/15 10:29
     */
    public AuthUserVo getUserByToken(String token) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        String user = claims.get("user", String.class);
        return JSON.parseObject(user, AuthUserVo.class);
    }


}