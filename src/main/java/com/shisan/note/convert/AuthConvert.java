package com.shisan.note.convert;

import com.shisan.note.entity.User;
import com.shisan.note.vo.AuthUserVo;

public class AuthConvert {


    /**
     * 构建-》AuthUserVo
     *
     */
    public static AuthUserVo convert(User user) {
        AuthUserVo authUserVo = new AuthUserVo();
        authUserVo.setId(user.getId());
        authUserVo.setName(user.getName());
        authUserVo.setUserName(user.getUserName());
        authUserVo.setPhone(user.getPhone());
        authUserVo.setEmail(user.getEmail());
        authUserVo.setStatus(user.getStatus());
        authUserVo.setSignature(user.getSignature());
        return authUserVo;
    }

}
