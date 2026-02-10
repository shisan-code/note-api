package cn.shisan.convert;

import cn.shisan.domain.common.enums.StatusEnums;
import cn.shisan.dto.auth.UserRegister;
import cn.shisan.domain.entity.sys.User;

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
