package com.shisan.note.config.security;

import cn.shisan.common.domain.common.JResult;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 未登录 自定义响应
 *
 * @author shisan
 */
 public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

     @Override
     public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
         response.setStatus(HttpServletResponse.SC_OK);
         response.setCharacterEncoding("utf-8");
         response.setContentType(MediaType.APPLICATION_JSON_VALUE);
         PrintWriter printWriter = response.getWriter();
         printWriter.print(JSONObject.toJSONString(JResult.failed(HttpStatus.UNAUTHORIZED.value(),"未登录!")));
         printWriter.flush();
         printWriter.close();
     }

}
