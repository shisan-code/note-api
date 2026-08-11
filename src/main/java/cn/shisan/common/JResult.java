package cn.shisan.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 统一返回
 *
 * @param <T>
 */
@Setter
@Getter
public class JResult<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    public JResult(int status, String message, T obj) {
        this.status = status;
        this.msg = message;
        this.data = obj;
    }

    public JResult(int status, String message) {
        this.status = status;
        this.msg = message;
        this.data = null;
    }

    public JResult(T obj) {
        this.data = obj;
        this.status = 0;
        this.msg = "";
    }

    public JResult() {
    }

    public static <T> JResult<T> failed(String msg) {
        return new JResult<>(2, msg);
    }

    public static <T> JResult<T> failed(int status, String msg) {
        return new JResult<>(status, msg);
    }

    public boolean isSuccess() {
        return status == 0;
    }

}
