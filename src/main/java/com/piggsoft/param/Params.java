package com.piggsoft.param;




import com.piggsoft.object.ObjectUtils;

import java.util.HashMap;

/**
 * <br>Created by fire pigg on 2016/3/30.
 *
 * @author piggsoft@163.com
 */
public class Params extends HashMap<String, Object> {

    public static Params create() {
        return new Params();
    }

    public Params add(String key, Object val) {
        this.put(key, val);
        return this;
    }

    public Params addNoNull(String key, Object val) {
        if (ObjectUtils.isNotEmpty(val)) {
            this.put(key, val);
        }
        return this;
    }
}
