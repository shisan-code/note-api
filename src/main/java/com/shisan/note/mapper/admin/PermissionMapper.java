package com.shisan.note.mapper.admin;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shisan.note.entity.admin.Permission;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper extends BaseMapper<Permission> {

	/**
	 * 根据角色id查询
	 * @param roleIds
	 * @return
	 */
	List<Permission> findInRoleIds(@Param("roleIds") List<Long> roleIds, @Param("type")Integer type);
	
}