package com.shisan.note.convert;

import com.shisan.note.dto.sys.RoleDto;
import com.shisan.note.domain.entity.sys.Role;

import java.time.LocalDateTime;


/**
 * @author lijing
 * @Date 2026/1/15 10:58
 */
public class RoleConvert {

    /**
     * 构建-》Role
     */
    public static Role convert(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        role.setType(roleDto.getType());
        role.setRemark(roleDto.getRemark());
        if (null == roleDto.getId()) {
            role.setCreated(LocalDateTime.now());
        } else {
            role.setModified(LocalDateTime.now());
        }
        return role;
    }

}
