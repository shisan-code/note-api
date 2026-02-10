package com.shisan.note.controller;

import cn.shisan.common.domain.common.JResult;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * controller的父类
 *
 * @author lijing
 */
public class BaseController {
    protected <T> JResult<T> error(String msg) {
        return new JResult<>(2, msg);
    }

    protected <T> JResult<T> fail(String msg) {
        return new JResult<>(1, msg);
    }

    protected <T> JResult<T> success(T data) {
        return new JResult<>(0, "成功", data);
    }

    protected <T> JResult<T> success() {
        return new JResult<>(0, "成功");
    }


    /**
     * 获取当前的活动请求
     */
    protected HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    /**
     * 获取登录用户Id
     */
    protected Long getUserId() {
        String object = getRequest().getHeaders("user").nextElement();
        return JSONObject.parseObject(object).getLong("userId");
    }
}
