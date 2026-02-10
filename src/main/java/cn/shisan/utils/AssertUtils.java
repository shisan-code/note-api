package cn.shisan.utils;

import cn.shisan.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

/**
 * 断言
 *
 * @author lijing
 */
public class AssertUtils {

    /**
     * null 抛出异常
     *
     * @param obj 校验对象
     * @param msg 错误消息
     */
    public static void isNull(Object obj, String msg) {
        if (null == obj) {
            throw new BusinessException(msg);
        }
    }

    public static void notNull(Object obj, String msg) {
        if (null != obj) {
            throw new BusinessException(msg);
        }
    }

    public static void isTrue(boolean expression, String msg) {
        if (expression) {
            throw new BusinessException(msg);
        }
    }

    public static void isFalse(boolean expression, String msg) {
        isTrue(!expression, msg);
    }

    /**
     * String 为空抛出异常
     *
     * @param obj 校验值
     * @param msg 错误消息
     */
    public static void isBlank(String obj, String msg) {
        if (StringUtils.isBlank(obj)) {
            throw new BusinessException(msg);
        }
    }
}
