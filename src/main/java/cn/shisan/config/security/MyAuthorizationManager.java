package cn.shisan.config.security;

import cn.shisan.config.AuthProperties;
import cn.shisan.dto.auth.LoginUser;

import java.util.List;
import java.util.function.Supplier;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@RequiredArgsConstructor
@Slf4j
@Component
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final AuthProperties authProperties;


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        HttpServletRequest request = requestAuthorizationContext.getRequest();

        // 白名单
        List<String> whitelist = authProperties.getWhitelist();
        // 检查请求是否匹配忽略路径（白名单）
        if (whitelist.stream().anyMatch(url -> antPathMatcher.match(url, request.getRequestURI()))) {
            return new AuthorizationDecision(true);
        }
        // 3. 获取当前登录用户信息
        Authentication auth = authentication.get();
        // 未登录
        if (auth == null || !auth.isAuthenticated()) {
            return new AuthorizationDecision(false);
        }

        // 安全判断：必须是LoginUser类型再强转
        Object principal = auth.getPrincipal();
        if (!(principal instanceof LoginUser loginUser)) {
            return new AuthorizationDecision(false);
        }

        // 拿到用户所有API权限路径
        List<String> userPermPaths = loginUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        log.info("Authorities：{}", auth.getAuthorities());

        // 4. 循环匹配：用户权限任意一个命中当前请求URI，则允许访问
        boolean hasPermission = userPermPaths.stream()
                .anyMatch(permPath -> antPathMatcher.match(permPath, request.getRequestURI()));

        return new AuthorizationDecision(hasPermission);
    }

}