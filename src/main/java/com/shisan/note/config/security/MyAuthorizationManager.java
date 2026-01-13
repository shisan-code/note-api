package com.shisan.note.config.security;

import com.shisan.note.config.AuthProperties;
import com.shisan.note.entity.LoginUser;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Resource
    private AuthProperties authProperties;


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        HttpServletRequest request = requestAuthorizationContext.getRequest();

        // 用户白名单
        List<String> ignores = authProperties.getIgnores();
        // 检查请求是否匹配忽略路径（白名单）
        if (ignores.stream().anyMatch(url -> antPathMatcher.match(url, request.getRequestURI()))) {
            return new AuthorizationDecision(true);
        }

        //获取用户认证信息
        Object principal = authentication.get().getPrincipal();
        log.info("Authorities：{}", authentication.get().getAuthorities());
        //判断数据是否为空 以及类型是否正确
        if (principal instanceof LoginUser) {
            LoginUser loginUser = (LoginUser) principal;
            // 超管放行
            if (loginUser.isAdmin()) {
                return new AuthorizationDecision(true);
            }
            // 用户白名单
            List<String> urls = authProperties.getAuths();
            // 检查请求用户白名单
            if (urls.stream().anyMatch(url -> antPathMatcher.match(url, request.getRequestURI()))) {
                return new AuthorizationDecision(true);
            }
        }
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
        boolean hasPermission = false;
        for (GrantedAuthority authority : authorities) {
            if (antPathMatcher.match(authority.getAuthority(), request.getRequestURI())) {
                hasPermission = true;
                break;
            }
        }
        return new AuthorizationDecision(hasPermission);
    }

}