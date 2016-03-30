package com.piggsoft.exception;

/**
 * <br>Created by fire pigg on 2016/3/30.
 *
 * @author piggsoft@163.com
 */
public class ValidateException extends RuntimeException {
    private String code;
    private String msg;

    public ValidateException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ValidateException(String code, String msg, String message) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public ValidateException(String code, String msg, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public ValidateException(String code, String msg, Throwable cause) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public ValidateException(String code, String msg, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }
}
