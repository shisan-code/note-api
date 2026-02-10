package cn.shisan.domain.common.enums;

import cn.shisan.common.domain.enums.IEnum;

public enum StatusEnums implements IEnum<Integer> {
    ENABLE(1, "启用"),
    DISABLE(2, "停用");

    private final Integer code;
    private final String text;

    StatusEnums(Integer code, String text) {
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