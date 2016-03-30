package com.piggsoft.http;

import com.alibaba.fastjson.JSON;
import com.piggsoft.exception.HttpException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.T;

/**
 * <br>Created by fire pigg on 2016/3/28.
 *
 * @author piggsoft@163.com
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static int timeout = 10 * 1000;

    private static final String DEFAULT_ENCODING = "UTF-8";

    private static final RequestConfig CONFIG = RequestConfig.custom()
            .setConnectionRequestTimeout(timeout).setConnectTimeout(timeout)
            .setSocketTimeout(timeout).build();

    public static String post(String url, Map<String, String> params) {
        return post(url, DEFAULT_ENCODING, params, null);
    }

    public static String post(String url, String content) {
        return post(url, DEFAULT_ENCODING, null, content);
    }

    public static String post(String url, String charset, Map<String, String> params, String content) {
        return _post(url, charset, params, content);
    }

    public static String _post(String url, String charset, Map<String, String> params, String content) {
        CloseableHttpClient httpClient = null;
        HttpPost post = null;
        String responseMessage = null;
        try {
            httpClient = HttpClients.createDefault();
            post = new HttpPost(url);
            post.setConfig(CONFIG);
            HttpEntity httpEntity = getEntity(params, content, charset);
            post.setEntity(httpEntity);
            //不能加上Content-Type,加上后台servlet不会解析参数
            //post.setHeader("Content-Type", "text/xml;charset=" + charset);
            CloseableHttpResponse response = httpClient.execute(post);
            responseMessage = EntityUtils.toString(response.getEntity(), Charset.forName(charset));
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
            throw new HttpException("500", "error", e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new HttpException("500", "error", e);
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
            closeHttpClient(httpClient);
        }
        return responseMessage;
    }

    private static void closeHttpClient(CloseableHttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                throw new HttpException("500", "error", e);
            }
        }
    }


    private static HttpEntity getEntity(Map<String, String> params, String content, String charset) throws UnsupportedEncodingException {
        if (params != null && !params.isEmpty()) {
            logger.debug("请求：params({});charset({})", JSON.toJSONString(params), charset);
            List<NameValuePair> _params = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                _params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            return new UrlEncodedFormEntity(_params, charset);
        }
        if (StringUtils.isNotEmpty(content)) {
            logger.debug("请求：params({});charset({})", content, charset);
            return new StringEntity(content, ContentType.create("application/x-www-form-urlencoded", Charset.forName(charset)));
        }
        logger.debug("请求：没有参数");
        return new StringEntity("");
    }

    /**
     * 将json串转为对象
     *
     * @param s
     * @param clazz
     * @return
     */
    private static <T> T parse(String s, Class<T> clazz) {
        logger.debug("解析的字符串是==>", s);
        StringReader reader = new StringReader(s);
        T t = JSON.parseObject(s, clazz);//JAXB.unmarshal(reader, clazz);
        reader.close();
        return t;
    }

    public static String get(String url, Map<String, String> params) {
        return get(url, params, DEFAULT_ENCODING);
    }

    /**
     * 慎用 谨防乱码
     *
     * @param url
     * @param params
     * @param encoding
     * @return
     */
    public static String get(String url, Map<String, String> params, String encoding) {
        CloseableHttpClient httpClient = null;
        HttpUriRequest request = null;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            RequestBuilder requestBuilder = RequestBuilder.get();
            requestBuilder.setUri(url);
            requestBuilder.setHeader("Content-Type", "text/xml;charset=" + encoding);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                requestBuilder.addParameter(entry.getKey(), entry.getValue());
            }
            request = requestBuilder.build();
            logger.debug("Send Request : {}", request.getRequestLine().toString());
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, encoding);
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
            throw new HttpException("500", "error", e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new HttpException("500", "error", e);
        } finally {
            closeHttpClient(httpClient);
        }
        return result;
    }

}

