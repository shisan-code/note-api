package com.shisan.note.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author lijing
 */
@Component
@ConfigurationProperties(prefix = "secure")
@Data
public class AuthProperties {

    /**
     * 白名单（登录后）
     */
    private List<String> auths;

    /**
     * 不需要登录
     */
    private List<String> ignores;


}
