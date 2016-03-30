package com.piggsoft.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <br>Created by fire pigg on 2016/01/14.
 *
 * @author piggsoft@163.com
 */
public class ParamsValidateHandler extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParamsValidateHandler.class);

    private static Map<Method, ParamsValidate> CACHE = new ConcurrentHashMap<Method, ParamsValidate>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ParamsValidate paramsValidate = null;
        paramsValidate = CACHE.get(method);
        if (null == paramsValidate) {
            paramsValidate = AnnotationUtils.findAnnotation(method, ParamsValidate.class);
            if (null != paramsValidate) {
                CACHE.put(method, paramsValidate);
            }
        }
        if (null == paramsValidate) {
            return true;
        }

        ParamValidate[] pvs = paramsValidate.value();
        if (pvs != null) {
            for (ParamValidate pv : pvs) {
                String name = pv.name();
                String errorMsg = pv.errorMsg();
                String regexErrorMsg = pv.regexErrorMsg();
                String value = request.getParameter(name);
                String sp = request.getServletPath();
                if (!isEmpty(value)) {
                    String regex = pv.regex();
                    if (!"".equals(regex)) {//需要正则验证
                        if (!value.matches(regex)) {
                            LOGGER.warn(sp + ";参数格式错误" + name + "=" + value);
                            throw new ValidateException("1101", "".equals(regexErrorMsg) ? ("参数格式错误" + name + "=" + value) : regexErrorMsg);
                        }
                    }
                } else if (pv.required()) {
                    LOGGER.warn(sp + ";缺少必要参数：" + name);
                    throw new ValidateException("1102", "".equals(errorMsg) ? ("缺少必要参数：" + name) : errorMsg);
                }
            }
        } else {
            LOGGER.warn("ParamValidate is empty");
        }

        return true;
    }

    private boolean isEmpty(Object o) {
        return o == null || o instanceof String && "".equals(o);
    }
}
