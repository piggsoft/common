package com.piggsoft.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * <br>Created by fire pigg on 2016/3/30.
 *
 * @author piggsoft@163.com
 */
public class IOUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOUtils.class);

    /**
     *  关闭流
     * @param closeable 可关闭的流之类
     */
    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            LOGGER.error("error on close");
        }
    }

}
