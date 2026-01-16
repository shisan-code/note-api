package com.shisan.note.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shisan.note.entity.admin.RolePermission;
import java.util.List;

public interface RolePermissionService extends IService<RolePermission> {

	void deleteByRoleId(Long roleId);

	List<RolePermission> findByRoleId(Long roleId);
}