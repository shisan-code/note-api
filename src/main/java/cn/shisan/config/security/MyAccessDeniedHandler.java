package cn.shisan.config.security;

import cn.shisan.common.JResult;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述：无权访问自定义响应
 *
 * @author shisan
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
        PrintWriter writer = response.getWriter();
        JResult<Object> resp = JResult.failed(HttpStatus.FORBIDDEN.value(), "权限不足，禁止访问!");
        writer.print(JSON.toJSONString(resp));
        writer.flush();
        writer.close();
    }
}