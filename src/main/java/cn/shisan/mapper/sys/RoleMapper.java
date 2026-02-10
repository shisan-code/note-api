package com.shisan.note.mapper.sys;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shisan.note.domain.entity.sys.Role;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findByUserId(Long userId);

}

