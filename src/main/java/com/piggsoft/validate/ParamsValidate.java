package com.piggsoft.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <br>Created by fire pigg on 2016/01/14.
 *
 * @author piggsoft@163.com
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamsValidate {

    ParamValidate[] value();
}
