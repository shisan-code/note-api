package cn.shisan.common.enums;

public class UserEnums {


    public enum PermissionType implements IEnum<Integer> {
        MENU(1, "菜单"),
        API(2, "API");
        private Integer code;
        private String text;
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
