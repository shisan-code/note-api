package cn.shisan.config.security;

import cn.shisan.service.auth.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 注入你自己的Bean
    private final JwtAuthenticationFilter jwtFilter;
    private final MyAuthorizationManager authorizationManager;
    private final MyAccessDeniedHandler myAccessDeniedHandler;
    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    private final CustomUserDetailsService userLoginService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. 关闭HttpBasic明文认证
                .httpBasic(AbstractHttpConfigurer::disable)
                // 2. 关闭CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // 3. 关闭表单登录页面
                .formLogin(AbstractHttpConfigurer::disable)
                // 4. 关闭默认退出登录页面
                .logout(AbstractHttpConfigurer::disable)
                // 5. 无状态会话（JWT必须配置）
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 6. 权限放行规则
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().access(authorizationManager)
                )
                // 7. 异常统一配置：未认证入口 + 权限拒绝处理器
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(myAuthenticationEntryPoint)
                        .accessDeniedHandler(myAccessDeniedHandler)
                )
                // 8. 自定义认证器
                .authenticationProvider(authenticationProvider(userLoginService, passwordEncoder()))
                // 9. JWT过滤器前置
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService userLoginService,
                                                         PasswordEncoder passwordEncoder) {
        // 传入UserDetailsService构造，废弃警告直接消失
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userLoginService);
        // 后续属性依旧用set方法设置
        provider.setPasswordEncoder(passwordEncoder);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    /**
     * 配置 AuthenticationManager bean
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * 在security安全框架中，提供了若干密码解析器实现类型。
     * 其中BCryptPasswordEncoder 叫强散列加密。可以保证相同的明文，多次加密后，
     * 密码有相同的散列数据，而不是相同的结果。
     * 匹配时，是基于相同的散列数据做的匹配。
     * Spring Security 推荐使用 BCryptPasswordEncoder 作为密码加密和解析器。
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}