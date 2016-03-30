package com.piggsoft.mybaits.generator;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * <br>Created by fire pigg on 2016/3/30.
 *
 * @author piggsoft@163.com
 */
public class Plugin extends PluginAdapter {

    public boolean validate(List<String> list) {
        return true;
    }
}
