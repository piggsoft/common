package com.piggsoft.validate.exception;

/**
 * <br>Created by fire pigg on 2016/3/30.
 *
 * @author piggsoft@163.com
 */
public class ValidateException extends RuntimeException {

    private static final long serialVersionUID = 5842928551048581660L;

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
}
