package com.shisan.note.utils;

import cn.shisan.common.exception.BusinessException;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestContextUtils {


    /**
     * 获取当前的活动请求
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    /**
     * 获取登录用户Id
     */
    public static Long getUserId() {
        String object = getRequest().getHeaders("user").nextElement();
        if (null != object) {
            return JSONObject.parseObject(object).getLong("userId");
        }
        throw new BusinessException("系统繁忙！");
    }
}
