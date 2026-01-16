package com.shisan.note.convert;

import com.shisan.note.common.enums.StatusEnums;
import com.shisan.note.dto.auth.UserRegister;
import com.shisan.note.entity.admin.User;

import java.time.LocalDateTime;

public class UserConvert {

    /**
     * 构建-》AuthUserVo
     *
     */
    public static User convert(UserRegister register) {
        User user = new User();
        user.setName(register.getName());
        user.setUserName(register.getUserName());
        user.setPassword(register.getPassword());
        user.setPhone(register.getPhone());
        user.setEmail(register.getEmail());
        user.setCreated(LocalDateTime.now());
        user.setStatus(StatusEnums.ENABLE.getCode());
        user.setSignature(register.getSignature());
        return user;

    }

}
