package cn.shisan.service.auth;

import cn.shisan.convert.AuthConvert;
import cn.shisan.convert.UserConvert;
import cn.shisan.dto.auth.LoginDto;
import cn.shisan.dto.auth.UserRegister;
import cn.shisan.dto.auth.LoginUser;
import cn.shisan.domain.entity.sys.User;
import cn.shisan.service.sys.UserService;
import cn.shisan.utils.AssertUtils;
import cn.shisan.utils.JwtTokenUtil;
import cn.shisan.vo.AuthUserVo;
import cn.shisan.vo.LoginVo;
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
        // 封装账号密码认证令牌
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());

        // 调用Security底层校验账号密码（自动调用UserDetailsService查询用户+密码比对）
        Authentication authentication = authenticationManager.authenticate(authToken);
        // 强转自定义LoginUser
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 转换为AuthUserVo
        AuthUserVo authUserVo = AuthConvert.convert(loginUser.getUser());

        // 生成Token
        String jwtToken = jwtTokenUtil.generateToken(authUserVo);
        // 封装返回数据
        return LoginVo.builder()
                .accessToken(jwtToken)
                .expiration(jwtTokenUtil.getExpiration())
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
        boolean save = userService.save(user);
        AssertUtils.isTrue(save, "注册失败");
    }

}
