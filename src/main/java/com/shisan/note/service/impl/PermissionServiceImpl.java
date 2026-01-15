package com.shisan.note.service.impl;

import cn.shisan.common.domain.common.PageQuery;
import cn.shisan.common.domain.enums.IEnum;
import cn.shisan.common.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shisan.note.common.enums.StatusEnums;
import com.shisan.note.common.enums.UserEnums;
import com.shisan.note.dto.admin.PermissionTree;
import com.shisan.note.dto.query.PermissionQueryDto;
import com.shisan.note.entity.Permission;
import com.shisan.note.entity.Role;
import com.shisan.note.mapper.admin.PermissionMapper;
import com.shisan.note.mapper.admin.RoleMapper;
import com.shisan.note.service.PermissionService;
import com.shisan.note.utils.AssertUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(rollbackFor = Exception.class)
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {


    private final PermissionMapper permissionMapper;
    private final RoleMapper roleMapper;


    @Override
    public List<PermissionTree> findTree() {
        List<Permission> list = baseMapper.selectList(Wrappers.<Permission>lambdaQuery()
                .eq(Permission::getDeleted, 0));
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        ArrayList<PermissionTree> trees = new ArrayList<>();
        for (Permission permission : list) {
            if (null != permission.getParentId() && permission.getParentId() == 0) {
                PermissionTree permissionTree = new PermissionTree();
                BeanUtils.copyProperties(permission, permissionTree);
                permissionTree.setChildren(getChildren(permissionTree, list));
                trees.add(permissionTree);
            }
        }
        return trees;
    }

    @Override
    public List<PermissionTree> userMenu(Long userId) {
        List<Permission> permissions = findByUserId(userId, UserEnums.PermissionType.MENU.getCode());
        ArrayList<PermissionTree> trees = new ArrayList<>();
        for (Permission permission : permissions) {
            if (null != permission.getParentId() && permission.getParentId() == 0) {
                PermissionTree permissionTree = new PermissionTree();
                BeanUtils.copyProperties(permission, permissionTree);
                permissionTree.setChildren(getChildren(permissionTree, permissions));
                trees.add(permissionTree);
            }
        }
        return trees;
    }

    private List<PermissionTree> getChildren(PermissionTree root, List<Permission> list) {
        ArrayList<PermissionTree> trees = new ArrayList<>();
        for (Permission permission : list) {
            if (root.getId().equals(permission.getParentId())) {
                PermissionTree tree = new PermissionTree();
                BeanUtils.copyProperties(permission, tree);
                tree.setChildren(getChildren(tree, list));
                trees.add(tree);
            }
        }
        return trees;
    }


    @Override
    public Permission findById(Long id) {
        return permissionMapper.selectOne(Wrappers.<Permission>lambdaQuery()
                .eq(Permission::getDeleted, 0)
                .eq(Permission::getId, id));
    }

    private void check(Permission permission) {
        UserEnums.PermissionType permissionType = IEnum.parseByCode(permission.getType(), UserEnums.PermissionType.class);
        AssertUtils.isNull(permissionType, "类型错误");

        StatusEnums status = IEnum.parseByCode(permission.getStatus(), StatusEnums.class);
        AssertUtils.isNull(status, "状态错误");

        if (null != permission.getParentId() && permission.getParentId() != 0) {
            Permission p = findById(permission.getParentId());
            if (null == p) {
                throw new BusinessException("父级不存在");
            }
        }
    }

    @Override
    public int insert(Permission permission) {
        permission.setParentId(Optional.ofNullable(permission.getParentId()).orElse(0L));
        check(permission);
        permission.setCreated(LocalDateTime.now());
        return permissionMapper.insert(permission);
    }

    @Override
    public int update(Permission permission) {
        Permission p = findById(permission.getId());
        AssertUtils.isNull(p, "权限资源不存在");
        check(permission);
        permission.setModified(LocalDateTime.now());
        return permissionMapper.updateById(permission);
    }

    @Override
    public int delete(Long id) {
        Permission p = new Permission();
        p.setId(id);
        p.setDeleted(1);
        p.setModified(LocalDateTime.now());
        return permissionMapper.updateById(p);
    }

    @Override
    public List<Permission> findByUserId(Long userId, Integer type) {
        List<Role> roles = roleMapper.findByUserId(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return Collections.emptyList();
        }
        // 超级管理员查询全部权限资源
        if (roles.stream().anyMatch(role -> role.getType().equals(0))) {
            LambdaQueryWrapper<Permission> lambdaQueryWrapper = Wrappers.<Permission>lambdaQuery()
                    .eq(Permission::getDeleted, 0)
                    .eq(Permission::getStatus, 1);
            if (null != type) {
                lambdaQueryWrapper.eq(Permission::getType, type);
            }
            return permissionMapper.selectList(lambdaQueryWrapper);
        }
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        return permissionMapper.findInRoleIds(roleIds, type);
    }

    @Override
    public List<Permission> findByParentId(Long parentId) {
        return permissionMapper.selectList(Wrappers.<Permission>lambdaQuery()
                .eq(Permission::getDeleted, 0)
                .eq(Permission::getParentId, parentId));
    }

    @Override
    public PageInfo<Permission> pageList(PageQuery<PermissionQueryDto> query) {
        PermissionQueryDto params = query.getParams();
        PageHelper.startPage(query.getPage(), query.getSize());
        // 查询参数
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Permission::getDeleted, 0);
        if (StringUtils.isNotBlank(params.getName())) {
            queryWrapper.lambda().like(Permission::getName, params.getName());
        }
        if (null != params.getType()) {
            queryWrapper.lambda().eq(Permission::getType, params.getType());
        }
        if (null != params.getStatus()) {
            queryWrapper.lambda().eq(Permission::getStatus, params.getStatus());
        }
        queryWrapper.lambda().orderByDesc(Permission::getCreated);
        List<Permission> permissions = baseMapper.selectList(queryWrapper);
        return new PageInfo<>(permissions);
    }

    @Override
    public boolean isAdmin(Long userId) {
        List<Role> roles = roleMapper.findByUserId(userId);
        if (CollectionUtils.isEmpty(roles)) {
            return false;
        }
        return roles.stream().anyMatch(role -> role.getType().equals(0));
    }

}