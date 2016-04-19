package com.piggsoft.sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * <br>Created by fire pigg on 2016/4/19.
 *
 * @author piggsoft@163.com
 */
public interface BaseMapper<Entity> {

    @SelectProvider(type = BaseSelectProvider.class, method = "search")
    List<Entity> search(@Param("entity") Entity entity);
}
