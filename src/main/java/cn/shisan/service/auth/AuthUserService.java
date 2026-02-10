package cn.shisan.service.auth;
import cn.shisan.dto.auth.LoginDto;
import cn.shisan.dto.auth.UserRegister;
import cn.shisan.vo.LoginVo;

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
