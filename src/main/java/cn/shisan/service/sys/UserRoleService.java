package cn.shisan.service.sys;

import cn.shisan.domain.entity.sys.UserRole;
import com.baomidou.mybatisplus.spring.service.IService;

public interface UserRoleService extends IService<UserRole> {

    void deleteByUserId(Long userId);
	
}