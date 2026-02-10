package com.shisan.note.domain.common;


import cn.shisan.common.domain.common.JResult;
import cn.shisan.common.exception.BusinessException;
import com.shisan.note.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AuthExceptionHandler extends BaseController {

    @ExceptionHandler({UsernameNotFoundException.class})
    public JResult<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.info("处理UsernameNotFoundException：{}", e.getMessage());
        return error(e.getMessage());
    }

    @ExceptionHandler
    public JResult<Void> handleDisabledException(DisabledException e) {
        log.info("处理DisabledException：{}", e.getMessage());
        return error("登录失败，此账号已禁用");
    }

    @ExceptionHandler({BadCredentialsException.class})
    public JResult<String> handleBadCredentialsException(BadCredentialsException e) {
        log.info("处理BadCredentialsException：{}", e.getMessage());
        return error("用户密码错误");
    }

    @ExceptionHandler({AuthenticationException.class})
    public JResult<String> handlerException(AuthenticationException e) {
        log.info("handlerException：{}", e.getMessage());
        return error(e.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    public JResult<String> businessExceptionHandler(BusinessException exception) {
        log.info("BusinessException：", exception);
        return error(exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public JResult<String> exceptionHandler(Exception exception) {
        log.info("Exception：", exception);
        return error("系统繁忙，请联系管理员!");
    }
}
