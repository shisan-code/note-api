package cn.shisan.service.sys;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.shisan.domain.entity.sys.UserRole;
import cn.shisan.mapper.sys.UserRoleMapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private final UserRoleMapper userRoleMapper;

    @Override
    public void deleteByUserId(Long userId) {
        UserRole userRole = new UserRole();
        userRole.setDeleted(1);
        userRole.setModified(LocalDateTime.now());
        userRoleMapper.update(userRole, Wrappers.<UserRole>lambdaUpdate()
                .eq(UserRole::getDeleted, 0)
                .eq(UserRole::getUserId, userId));
    }

}