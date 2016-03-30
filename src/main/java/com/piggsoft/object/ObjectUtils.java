package com.piggsoft.object;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.Map;


/**
 * <br>Created by fire pigg on 2016/3/30.
 *
 * @author piggsoft@163.com
 */
public class ObjectUtils {

    public static boolean isEmpty(Object o) {
        if (null == o) {
            return true;
        }
        if (o instanceof String) {
            String s = cast(o);
            return StringUtils.isEmpty(s);
        }
        if (o instanceof Collection) {
            Collection c = cast(o);
            return CollectionUtils.isEmpty(c);
        }
        if (o instanceof Map) {
            Map m = cast(o);
            return MapUtils.isEmpty(m);
        }
        return true;
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static  <T> T cast(Object o) {
        return (T) o;
    }
}
