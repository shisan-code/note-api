package cn.shisan.domain.common.enums;

import cn.shisan.common.domain.enums.IEnum;

public class MemberCardEnums {

    // 状态：1-正常，2-冻结，3-注销
    public enum Status implements IEnum<Integer> {
        ACTIVATE(1, "正常"),
        FREEZE(2, "冻结"),
        LOGOUT(3, "注销"),
        ;
        private final Integer code;
        private final String text;

        Status(Integer code, String text) {
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


    // 交易类型：1-充值，2-消费
    public enum TransactionType implements IEnum<Integer> {
        RECHARGE(1, "充值"),
        CONSUME(2, "消费"),
        ;
        private final Integer code;
        private final String text;

        TransactionType(Integer code, String text) {
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