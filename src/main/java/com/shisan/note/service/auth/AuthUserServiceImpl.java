package com.shisan.note.service.auth;

import com.shisan.note.convert.AuthConvert;
import com.shisan.note.convert.UserConvert;
import com.shisan.note.dto.auth.LoginDto;
import com.shisan.note.dto.auth.UserRegister;
import com.shisan.note.entity.LoginUser;
import com.shisan.note.entity.User;
import com.shisan.note.service.UserService;
import com.shisan.note.utils.AssertUtils;
import com.shisan.note.utils.JwtTokenUtil;
import com.shisan.note.vo.AuthUserVo;
import com.shisan.note.vo.LoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lijing
 * @Date 2026/1/13 16:22
 */
@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    @Override
    public LoginVo login(LoginDto login) {
        // 身份认证
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword()));
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // 加载用户信息
        User user = userService.findByUserName(login.getUserName());
        // 生成Token
        String jwtToken = jwtTokenUtil.generateToken(loginUser);
        AuthUserVo authUserVo = AuthConvert.convert(user);
        // 封装返回数据
        return LoginVo.builder()
                .accessToken(jwtToken)
                .authUser(authUserVo)
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegister register) {
        AssertUtils.isBlank(register.getUserName(), "用户名不能为空");
        AssertUtils.isBlank(register.getPassword(), "密码不能为空");

        User name = userService.findByUserName(register.getUserName());
        AssertUtils.notNull(name, "用户名已经存在");

        // 构建用户信息
        User user = UserConvert.convert(register);
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        userService.save(user);
    }

}
