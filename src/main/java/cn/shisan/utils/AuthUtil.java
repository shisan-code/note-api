package com.shisan.note.utils;

import cn.shisan.common.exception.BusinessException;

import java.util.Objects;

public class AuthUtil {

    public static void check(Long userId) {
        if (!Objects.equals(userId, RequestContextUtils.getUserId())) {
            throw new BusinessException("无权操作！");
        }
    }

}
