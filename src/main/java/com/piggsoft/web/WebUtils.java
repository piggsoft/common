package com.piggsoft.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <br>Created by fire pigg on 2016/4/7.
 *
 * @author piggsoft@163.com
 */
public class WebUtils {

    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    public static void addContentType(HttpServletResponse response, ContentType contentType) {
        response.setContentType(contentType.toString());
        response.setCharacterEncoding(contentType.getCharset().toString());
    }

    public static void addContentType(HttpServletResponse response) {
        addContentType(response, ContentType.APPLICATION_JSON);
    }

}
