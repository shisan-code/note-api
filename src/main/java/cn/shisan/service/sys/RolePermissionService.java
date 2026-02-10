package cn.shisan.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.shisan.domain.entity.sys.RolePermission;
import java.util.List;

public interface RolePermissionService extends IService<RolePermission> {

	void deleteByRoleId(Long roleId);

	List<RolePermission> findByRoleId(Long roleId);
}