package cn.shisan.service.auth;

import cn.shisan.domain.common.enums.UserEnums;
import cn.shisan.dto.auth.LoginUser;
import cn.shisan.domain.entity.sys.Permission;
import cn.shisan.domain.entity.sys.User;
import cn.shisan.service.sys.PermissionService;
import cn.shisan.service.sys.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //如果User为null，就会抛出异常信息：UsernameNotFoundException
        User user = userService.findByUserName(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在!");
        }

        List<Permission> permissions = permissionService.findByUserId(user.getId(), UserEnums.PermissionType.API.getCode());
        List<String> collect = permissions.stream().map(Permission::getUrl).collect(Collectors.toList());
        return new LoginUser(user, collect);
    }
}

