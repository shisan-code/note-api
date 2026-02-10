package com.shisan.note.domain.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode {

    AUTHORIZED_ERROR("A0300", "访问权限异常"),
    ACCESS_UNAUTHORIZED("A0301", "访问未授权"),
    TOKEN_INVALID_OR_EXPIRED("A0230", "token无效或已过期"),
    TOKEN_ACCESS_FORBIDDEN("A0231", "token已被禁止访问"),
    USER_ERROR("A0001", "用户端错误"),
    USER_LOGIN_ERROR("A0200", "用户登录异常"),

    USER_NOT_EXIST("A0201", "用户不存在"),
    USER_ACCOUNT_LOCKED("A0202", "用户账户被冻结"),
    USER_ACCOUNT_INVALID("A0203", "用户账户已作废"),

    USERNAME_OR_PASSWORD_ERROR("A0210", "用户名或密码错误"),
    PASSWORD_ENTER_EXCEED_LIMIT("A0211", "用户输入密码次数超限"),
    CLIENT_AUTHENTICATION_FAILED("A0212", "客户端认证失败"),
    SYSTEM_EXECUTION_ERROR("B0001", "系统执行出错"),
    SYSTEM_EXECUTION_TIMEOUT("B0100", "系统执行超时"),
    SUCCESS("00000", "一切ok"),
    ;
    


    private String code;
    private String msg;


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public static ResultCode getValue(String code){
        for (ResultCode value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return SYSTEM_EXECUTION_ERROR;
    }


}
