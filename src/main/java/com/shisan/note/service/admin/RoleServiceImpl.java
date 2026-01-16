package com.shisan.note.service.admin;

import cn.shisan.common.domain.common.PageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shisan.note.convert.RoleConvert;
import com.shisan.note.dto.admin.RoleDto;
import com.shisan.note.dto.admin.RolePermissionDto;
import com.shisan.note.dto.query.RoleQueryDto;
import com.shisan.note.entity.admin.Role;
import com.shisan.note.entity.admin.RolePermission;
import com.shisan.note.mapper.admin.RoleMapper;
import com.shisan.note.utils.AssertUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMapper roleMapper;
    private final RolePermissionService rolePermissionService;

    @Override
    public Role findById(Long id) {
        return roleMapper.selectOne(Wrappers.<Role>lambdaQuery()
                .eq(Role::getDeleted, 0)
                .eq(Role::getId, id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(RoleDto roleDto) {
        Role role = RoleConvert.convert(roleDto);
        int insert = roleMapper.insert(role);
        AssertUtils.isTrue(insert <= 0, "角色添加失败，请重试!");
    }

    @Override
    public void update(RoleDto roleDto) {
        Role role = findById(roleDto.getId());
        AssertUtils.isNull(role, "角色不存在");

        Role r = RoleConvert.convert(roleDto);
        int update = roleMapper.updateById(r);
        AssertUtils.isTrue(update <= 0, "角色修改失败，请重试!");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        Role role = new Role();
        role.setId(id);
        role.setDeleted(1);
        role.setModified(LocalDateTime.now());
        int delete = roleMapper.updateById(role);
        AssertUtils.isTrue(delete <= 0, "角色删除失败，请重试!");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void setPermission(RolePermissionDto rolePermissionDto) {
        Role role = findById(rolePermissionDto.getRoleId());
        AssertUtils.isNull(role, "角色不存在");

        // 保存角色权限资源
        List<RolePermission> permissions = rolePermissionDto.getPermissionIds().stream().map(o -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(rolePermissionDto.getRoleId());
            rolePermission.setPermissionId(o);
            rolePermission.setCreated(LocalDateTime.now());
            return rolePermission;
        }).collect(Collectors.toList());
        rolePermissionService.deleteByRoleId(rolePermissionDto.getRoleId());
        if (!CollectionUtils.isEmpty(permissions)) {
            rolePermissionService.saveBatch(permissions);
        }
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return roleMapper.findByUserId(userId);
    }

    @Override
    public PageInfo<Role> pageList(PageQuery<RoleQueryDto> query) {
        RoleQueryDto params = query.getParams();
        PageHelper.startPage(query.getPage(), query.getSize());
        // 查询参数
        List<Role> roleList = list(params);
        return new PageInfo<>(roleList);
    }

    @Override
    public List<Role> list(RoleQueryDto query) {
        // 查询参数
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Role::getDeleted, 0);
        if (null != query.getType()) {
            queryWrapper.lambda().eq(Role::getType, query.getType());
        }
        if (StringUtils.isNotBlank(query.getName())) {
            queryWrapper.lambda().like(Role::getName, query.getName());
        }
        queryWrapper.lambda().orderByDesc(Role::getCreated);
        return baseMapper.selectList(queryWrapper);
    }

}