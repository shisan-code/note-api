package com.shisan.note.service.sys;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shisan.note.domain.entity.sys.UserRole;

public interface UserRoleService extends IService<UserRole> {

    void deleteByUserId(Long userId);
	
}