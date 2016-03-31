package com.piggsoft.http.response;

import javax.servlet.http.HttpServletResponse;

/**
 * <br>Created by fire pigg on 2016/3/30.
 *
 * @author piggsoft@163.com
 */
public class ResponseUtils {

    public static void addContentType(HttpServletResponse response, ContentType contentType) {
        response.setContentType(contentType.toString());
        response.setCharacterEncoding(contentType.getCharset().toString());
    }


}
