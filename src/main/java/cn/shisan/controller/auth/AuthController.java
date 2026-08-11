package cn.shisan.controller.auth;

import cn.shisan.common.JResult;
import cn.shisan.controller.BaseController;
import cn.shisan.dto.auth.UserRegister;
import cn.shisan.service.auth.AuthUserService;
import cn.shisan.dto.auth.LoginDto;
import cn.shisan.vo.LoginVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "授权认证API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthUserService authUserService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public JResult<LoginVo> authenticate(@RequestBody LoginDto loginDto) {
        LoginVo login = authUserService.login(loginDto);
        return success(login);
    }

    @Operation(summary = "注册用户")
    @PostMapping("/register")
    public JResult<String> register(@RequestBody UserRegister register) {
        authUserService.register(register);
        return success();
    }


}
