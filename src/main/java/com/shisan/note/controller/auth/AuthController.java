package com.shisan.note.controller.auth;

import cn.shisan.common.domain.common.JResult;
import com.shisan.note.controller.BaseController;
import com.shisan.note.dto.auth.UserRegister;
import com.shisan.note.service.UserService;
import com.shisan.note.dto.auth.LoginDto;
import com.shisan.note.vo.LoginVo;
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

    private final UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public JResult<LoginVo> authenticate(@RequestBody LoginDto loginDto) {
        LoginVo login = userService.login(loginDto);
        return success(login);
    }

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public JResult<String> authenticate(@RequestBody UserRegister register) {
        userService.register(register);
        return success();
    }


}
