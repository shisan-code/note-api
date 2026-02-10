package com.shisan.note.domain.common.enums;

import cn.shisan.common.domain.enums.IEnum;

public enum DelEnum implements IEnum<Integer> {
    DEL(1, "已删除"),
    NO_DEL(0, "未删除");

    private final Integer code;
    private final String text;

    DelEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getText() {
        return text;
    }

}
