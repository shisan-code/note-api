package cn.shisan.domain.common.enums;

import cn.shisan.common.domain.enums.IEnum;

public class UserEnums {


    public enum PermissionType implements IEnum<Integer> {
        MENU(1, "菜单"),
        API(2, "API");
        private final Integer code;
        private final String text;
        PermissionType(Integer code, String text) {
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

}
