package cn.shisan.service.sys;


import com.baomidou.mybatisplus.extension.service.IService;
import cn.shisan.domain.entity.sys.UserRole;

public interface UserRoleService extends IService<UserRole> {

    void deleteByUserId(Long userId);
	
}