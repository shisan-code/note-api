package com.shisan.note.service.auth;
import com.shisan.note.dto.auth.LoginDto;
import com.shisan.note.dto.auth.UserRegister;
import com.shisan.note.vo.LoginVo;

public interface AuthUserService {

    /**
     * 用户登录
     * @param login 用户信息
     */
    LoginVo login(LoginDto login);

    /**
     * 注册用户
     * @param register 用户信息
     */
    void register(UserRegister register);
}
