package com.piggsoft.sql.exception;

/**
 * <br>Created by fire pigg on 2016/3/30.
 *
 * @author piggsoft@163.com
 */
public class TooManyResultException extends RuntimeException {

    private static final long serialVersionUID = -6425562330170235509L;
    private String code;
    private String msg;

    public TooManyResultException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public TooManyResultException(String code, String msg, String message) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public TooManyResultException(String code, String msg, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public TooManyResultException(String code, String msg, Throwable cause) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }
}
