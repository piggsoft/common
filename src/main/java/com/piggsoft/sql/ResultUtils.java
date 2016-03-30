package com.piggsoft.sql;

import com.piggsoft.object.ObjectUtils;
import com.piggsoft.sql.exception.TooManyResultException;

import java.util.List;

/**
 * <br>Created by fire pigg on 2016/3/30.
 *
 * @author piggsoft@163.com
 */
public class ResultUtils {

    public static <T> T getOne(List<T> list) {
        if (ObjectUtils.isEmpty(list)) {
            return null;
        }
        if (list.size() > 1) {
            throw new TooManyResultException("1022", "结果集超过1个");
        }
        return list.get(0);
    }
}
