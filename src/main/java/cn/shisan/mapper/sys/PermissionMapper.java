package cn.shisan.mapper.sys;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.shisan.domain.entity.sys.Permission;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper extends BaseMapper<Permission> {

	/**
	 * 根据角色id查询
	 * @param roleIds
	 * @return
	 */
	List<Permission> findInRoleIds(@Param("roleIds") List<Long> roleIds, @Param("type")Integer type);
	
}