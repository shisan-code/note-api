package com.shisan.note.service.auth;

import com.shisan.note.domain.common.enums.StatusEnums;
import com.shisan.note.domain.common.enums.UserEnums;
import com.shisan.note.dto.auth.LoginUser;
import com.shisan.note.domain.entity.sys.Permission;
import com.shisan.note.domain.entity.sys.User;
import com.shisan.note.service.sys.PermissionService;
import com.shisan.note.service.sys.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getId());
        loginUser.setUserName(user.getUserName());
        loginUser.setPassword(user.getPassword());
        ArrayList<String> roles = new ArrayList<>();
        boolean admin = permissionService.isAdmin(user.getId());
        if (!admin) {
            List<Permission> permissions = permissionService.findByUserId(user.getId(), UserEnums.PermissionType.API.getCode());
            for (Permission permission : permissions) {
                roles.add(permission.getUrl());
            }
        }
        loginUser.setAdmin(admin);
        loginUser.setPermissions(roles);
        // 是否启用
        loginUser.setEnabled(!Objects.equals(user.getStatus(), StatusEnums.DISABLE.getCode()));

        if (!loginUser.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!loginUser.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!loginUser.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return loginUser;
    }
}

