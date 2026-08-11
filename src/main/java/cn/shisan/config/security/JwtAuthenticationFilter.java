package cn.shisan.config.security;

import cn.shisan.service.auth.CustomUserDetailsService;
import cn.shisan.utils.JwtTokenUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从请求头中获取token
        String authorization = request.getHeader("Authorization");
        CustomHttpServletRequest servletRequest = new CustomHttpServletRequest(request);
        String userName = null;
        if (StringUtils.isNotBlank(authorization)) {
            // 验证token是否有效
            if (jwtTokenUtil.validateToken(authorization)) {
                userName = jwtTokenUtil.getUserByToken(authorization).getUserName();
            }
        }

        // 用户名存在 && 当前Security上下文未认证，执行登录授权
        if (StringUtils.isNotBlank(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userName);

            // 封装认证对象，密码置空，权限携带
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // 绑定请求详情
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 将认证信息存入SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        //继续过滤
        filterChain.doFilter(servletRequest, response);
    }
}