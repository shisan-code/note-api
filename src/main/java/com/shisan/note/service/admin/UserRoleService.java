package com.shisan.note.service.admin;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shisan.note.entity.UserRole;

public interface UserRoleService extends IService<UserRole> {

    void deleteByUserId(Long userId);
	
}