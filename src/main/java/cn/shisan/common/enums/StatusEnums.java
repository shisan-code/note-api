package cn.shisan.common.enums;

public enum StatusEnums implements IEnum<Integer> {
    ENABLE(1, "启用"),
    DISABLE(2, "停用");

    private Integer code;
    private String text;

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