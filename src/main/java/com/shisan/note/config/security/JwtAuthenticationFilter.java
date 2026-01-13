package com.shisan.note.config.security;

import com.alibaba.fastjson2.JSONObject;
import com.shisan.note.service.auth.CustomUserDetailsService;
import com.shisan.note.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从请求头中获取token
        String jwtToken = request.getHeader("Authorization");
        CustomHttpServletRequest servletRequest = new CustomHttpServletRequest(request);

        if (StringUtils.isNotBlank(jwtToken)) {
            String userName = jwtTokenUtil.extractUsername(jwtToken);
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userName);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 将认证信息存入SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);

                // 设置用户信息
                HashMap<String, Object> map = new HashMap<>();
                map.put("userName", userName);
                servletRequest.addHeader("user", JSONObject.toJSONString(map));
            }
        }
        //继续过滤
        filterChain.doFilter(servletRequest, response);
    }
}