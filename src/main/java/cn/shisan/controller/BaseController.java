package cn.shisan.controller;

import cn.shisan.common.domain.common.JResult;
import cn.shisan.common.exception.BusinessException;
import cn.shisan.utils.JwtTokenUtil;
import cn.shisan.vo.AuthUserVo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * controller的父类
 *
 * @author lijing
 */
public class BaseController {
    @Resource
    private JwtTokenUtil jwtTokenUtil;

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
        String authorization = getRequest().getHeaders("Authorization").nextElement();
        AuthUserVo authUserVo = jwtTokenUtil.extractUser(authorization);
        if (authUserVo != null) {
            return authUserVo.getId();
        }
        throw new BusinessException("未登录！");
    }
}
