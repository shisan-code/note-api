package com.shisan.note.config.security;

import cn.shisan.common.domain.common.JResult;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * 描述：无权访问自定义响应
 *
 * @author shisan
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.print(JSONObject.toJSONString(JResult.failed(HttpStatus.FORBIDDEN.value(),"没有访问权限!")));
        writer.flush();
        writer.close();
    }
}