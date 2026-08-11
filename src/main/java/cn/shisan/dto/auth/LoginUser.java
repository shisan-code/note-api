package cn.shisan.dto.auth;

import cn.shisan.domain.common.enums.StatusEnums;
import cn.shisan.domain.entity.sys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
public class LoginUser implements UserDetails {

    // 原始数据库用户对象
    private User user;
    // 用户权限列表
    private List<String> permissions;


    /**
     * 重点：返回用户拥有的API路径权限集合
     * GrantedAuthority内部就是字符串存储，刚好适配路径
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isEmpty(permissions)) {
            return Collections.emptyList();
        }
        // 切割路径，转为权限对象
        return permissions.stream()
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * ========== 实现 UserDetails 规范方法 ==========
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 账号未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号启用可用
     */
    @Override
    public boolean isEnabled() {
        return Objects.equals(user.getStatus(), StatusEnums.ENABLE.getCode());
    }
}
