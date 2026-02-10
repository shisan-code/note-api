package cn.shisan.controller.auth;

import cn.shisan.common.domain.common.JResult;
import cn.shisan.controller.BaseController;
import cn.shisan.dto.auth.UserRegister;
import cn.shisan.service.auth.AuthUserService;
import cn.shisan.dto.auth.LoginDto;
import cn.shisan.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "授权认证API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthUserService authUserService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public JResult<LoginVo> authenticate(@RequestBody LoginDto loginDto) {
        LoginVo login = authUserService.login(loginDto);
        return success(login);
    }

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public JResult<String> register(@RequestBody UserRegister register) {
        authUserService.register(register);
        return success();
    }


}
