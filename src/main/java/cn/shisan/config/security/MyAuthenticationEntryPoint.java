package cn.shisan.config.security;

import cn.shisan.common.JResult;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * 未登录 自定义响应
 *
 * @author shisan
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        // 设置响应格式
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        PrintWriter printWriter = response.getWriter();
        JResult<Object> failed = JResult.failed(HttpStatus.UNAUTHORIZED.value(), "未登录或Token已失效，请重新登录");
        printWriter.print(JSON.toJSONString(failed));
        printWriter.flush();
        printWriter.close();
    }
}
