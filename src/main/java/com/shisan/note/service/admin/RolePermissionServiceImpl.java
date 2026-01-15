package com.shisan.note.service.admin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shisan.note.entity.RolePermission;
import com.shisan.note.mapper.admin.RolePermissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    private final RolePermissionMapper rolePermissionMapper;


    @Override
    public void deleteByRoleId(Long roleId) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setDeleted(1);
        rolePermission.setModified(LocalDateTime.now());
        rolePermissionMapper.update(rolePermission, Wrappers.<RolePermission>lambdaUpdate()
                .eq(RolePermission::getDeleted, 0)
                .eq(RolePermission::getRoleId, roleId));
    }

    @Override
    public List<RolePermission> findByRoleId(Long roleId) {
        return rolePermissionMapper.selectList(Wrappers.<RolePermission>lambdaQuery()
                .eq(RolePermission::getDeleted, 0)
                .eq(RolePermission::getRoleId, roleId));
    }
}