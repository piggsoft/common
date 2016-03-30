package com.piggsoft.validate;

/**
 * <br>Created by fire pigg on 2016/01/14.
 *
 * @author piggsoft@163.com
 */
public @interface ParamValidate {
    String name();

    String regex() default "";

    boolean required() default false;

    String errorMsg() default "";

    String regexErrorMsg() default "";

}
