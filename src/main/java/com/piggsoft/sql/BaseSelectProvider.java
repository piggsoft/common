package com.piggsoft.sql;

import com.piggsoft.param.Params;
import org.apache.ibatis.jdbc.SQL;

/**
 * <br>Created by fire pigg on 2016/4/19.
 *
 * @author piggsoft@163.com
 */
public class BaseSelectProvider {

    public String search(final Object entity) {
        return new SQL(){{
            SELECT(ColumnUtils.getColumns(entity.getClass()));
            FROM(entity.getClass().getSimpleName());
        }}.toString();
    }


    public static void main(String[] args) {
        System.out.println(new BaseSelectProvider().search(Params.create()));
    }

}
