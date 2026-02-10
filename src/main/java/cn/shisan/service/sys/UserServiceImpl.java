package cn.shisan.service.sys;

import cn.shisan.common.domain.common.PageQuery;
import cn.shisan.common.domain.enums.IEnum;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.shisan.domain.common.enums.StatusEnums;
import cn.shisan.dto.sys.UserDto;
import cn.shisan.dto.sys.UserRoleDto;
import cn.shisan.dto.sys.UserStatusDto;
import cn.shisan.dto.query.UserQueryDto;
import cn.shisan.domain.entity.sys.User;
import cn.shisan.domain.entity.sys.UserRole;
import cn.shisan.mapper.sys.UserMapper;
import cn.shisan.utils.AssertUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final UserRoleService userRoleService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void setRole(UserRoleDto userRoleDto) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getDeleted, 0)
                .eq(User::getId, userRoleDto.getUserId()));
        AssertUtils.isNull(user, "用户不存在");

        // 保存用户角色
        List<UserRole> roles = userRoleDto.getRoleIds().stream().map(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(user.getId());
            userRole.setCreated(LocalDateTime.now());
            return userRole;
        }).collect(Collectors.toList());
        userRoleService.deleteByUserId(userRoleDto.getUserId());
        if (!CollectionUtils.isEmpty(roles)) {
            boolean b = userRoleService.saveBatch(roles);
            AssertUtils.isTrue(!b, "用户角色变更失败，请重试!");
        }

    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getDeleted, 0)
                .eq(User::getUserName, userName));
    }

    @Override
    public PageInfo<UserDto> pageList(PageQuery<UserQueryDto> query) {
        PageHelper.startPage(query.getPage(), query.getSize());
        List<UserDto> sheepList = baseMapper.list(query);
        return new PageInfo<>(sheepList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatus(UserStatusDto statusDto) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getDeleted, 0)
                .eq(User::getId, statusDto.getId()));
        AssertUtils.isNull(user, "用户不存在");

        StatusEnums statusEnums = IEnum.parseByCode(statusDto.getStatus(), StatusEnums.class);
        AssertUtils.isNull(statusEnums, "状态值错误");

        User u = new User();
        u.setId(user.getId());
        u.setStatus(statusDto.getStatus());
        u.setModified(LocalDateTime.now());
        int update = userMapper.updateById(u);
        AssertUtils.isTrue(update <= 0, "用户状态变更失败，请重试!");
    }

}
