package com.shisan.note.convert;

import com.shisan.note.dto.admin.PermissionTree;
import com.shisan.note.entity.Permission;

/**
 * @author lijing
 * @Date 2026/1/15 10:53
 */
public class PermissionTreeConvert {

    /**
     * 构建-》PermissionTree
     *
     */
    public static PermissionTree convertTree(Permission permission) {
        PermissionTree permissionTree = new PermissionTree();
        permissionTree.setId(permission.getId());
        permissionTree.setParentId(permission.getParentId());
        permissionTree.setName(permission.getName());
        permissionTree.setUrl(permission.getUrl());
        permissionTree.setType(permission.getType());
        permissionTree.setStatus(permission.getStatus());
        permissionTree.setRemark(permission.getRemark());
        permissionTree.setSort(permission.getSort());
//        permissionTree.setChildren();
        return permissionTree;
    }

}
