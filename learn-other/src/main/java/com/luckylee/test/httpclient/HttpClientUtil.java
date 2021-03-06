package com.luckylee.test.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
 * httpclient 调用工具
 *
 * @author pengdh
 * @date 2017/10/28
 */
public class HttpClientUtil {
    /**
     * 缺省超时时间 单位：ms
     */
    private static final int TIMEOUT = 60000;
    /**
     * 是否重定向标识
     */
    private static final boolean IS_REDIRECTS = false;
    private static String EMPTY_STR = "";
    /**
     * 字符集编码格式
     */
    private static String UTF_8 = "UTF-8";
    private HttpClientUtil() {
    }
    /**
     * 发送 get 请求
     *
     * @param url 请求地址
     * @return String
     */
    public static String httpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        return execute(httpGet);
    }
    /**
     * 发送 get 请求
     *
     * @param url 请求地址
     * @param headers 头信息
     * @return String
     */
    public static String httpGetRequestWithHeaders(String url, Map<String, Object> headers) {
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return execute(httpGet);
    }
    /**
     * 发送 get 请求
     *
     * @param url 请求地址
     * @param headers 头信息
     * @param params 参数
     * @return String
     */
    public static String httpGetRequest(String url, Map<String, Object> headers,
                                        Map<String, Object> params) {
        HttpGet httpGet = new HttpGet(createParamUrl(url, params));
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return execute(httpGet);
    }
    /**
     * 发送 get 请求
     *
     * @param url 请求地址
     * @return String
     */
    public static String httpGetRequestWithParams(String url, Map<String, Object> params) {
        HttpGet httpGet = new HttpGet(createParamUrl(url, params));
        return execute(httpGet);
    }
    /**
     * 创建带参数的 URL
     *
     * @param url 无参URL
     * @param params 参数
     * @return String 带参数URL
     */
    private static String createParamUrl(String url, Map<String, Object> params) {
        Iterator<String> it = params.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        boolean isIncludeQuestionMark = url.contains("?");
        if (!isIncludeQuestionMark) {
            sb.append("?");
        }
        while (it.hasNext()) {
            String key = it.next();
            String value = (String) params.get(key);
            sb.append("&");
            sb.append(key);
            sb.append("=");
            sb.append(value);
        }
        url += sb.toString();
        return url;
    }
    /**
     * 发送 post 请求
     *
     * @param url 请求地址
     * @return String
     */
    public static String httpPostRequest(String url) {
        HttpPost httpPost = new HttpPost(url);
        return execute(httpPost);
    }
    /**
     * 发送 post 请求
     *
     * @param url 地址
     * @param params 参数
     * @return String
     */
    public static String httpPostRequest(String url, Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return execute(httpPost);
    }
    /**
     * 发送 post 请求
     *
     * @param url 地址
     * @param headers 头信息
     * @param params 参数
     * @return String
     */
    public static String httpPostRequest(String url, Map<String, Object> headers,
                                         Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, Object> headerParam : headers.entrySet()) {
            httpPost.addHeader(headerParam.getKey(), String.valueOf(headerParam.getValue()));
        }
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return execute(httpPost);
    }
    /**
     * 发送 post 请求
     *
     * @param url 地址
     * @param headers 头信息
     * @param json json 格式参数
     * @return String
     */
    public static String httpPostRequestByJson(String url, Map<String, Object> headers,
                                               String json) {
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, Object> headerParam : headers.entrySet()) {
            httpPost.addHeader(headerParam.getKey(), String.valueOf(headerParam.getValue()));
        }
        try {
            httpPost.setEntity(new StringEntity(json, UTF_8));
        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        }
        return execute(httpPost);
    }
    /**
     * 把参数转换为名值对数组
     *
     * @param params 参数
     * @return ArrayList<NameValuePair>
     */
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return pairs;
    }
    /**
     * 执行 HTTP 请求 若重定向返回重定向地址
     *
     * @return String
     */
    private static String execute(HttpRequestBase request) {
        String result = EMPTY_STR;
        request.setConfig(createConfig(TIMEOUT, IS_REDIRECTS));
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            if (isRedirected(response)) {
                result = getRedirectedUrl(response);
            } else {
                result = getEntityData(response);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 创建HTTP请求配置
     *
     * @param timeout 超时时间
     * @param redirectsEnabled 是否开启重定向
     * @return RequestConfig
     */
    private static RequestConfig createConfig(int timeout, boolean redirectsEnabled) {
        return RequestConfig.custom()
                // 读取数据超时时间（毫秒）
                .setSocketTimeout(timeout)
                // 建立连接超时时间（毫秒）
                .setConnectTimeout(timeout)
                // 从连接池获取连接的等待时间（毫秒）
                .setConnectionRequestTimeout(timeout)
                // 当响应状态码为302时，是否进行重定向
                .setRedirectsEnabled(redirectsEnabled)
                .build();
    }
    /**
     * 通过连接池获取 httpclient
     */
    private static CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setConnectionManager(
                HttpConnectionManager.POOLING_CONNECTION_MANAGER).build();
    }
    /**
     * 判断发送请求是否重定向跳转过
     *
     * @param response 请求响应
     * @return boolean
     */
    private static boolean isRedirected(CloseableHttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        return statusCode == HttpStatus.SC_MOVED_PERMANENTLY
                || statusCode == HttpStatus.SC_MOVED_TEMPORARILY;
    }
    /**
     * 获得重定向跳转地址
     *
     * @param response 请求响应
     * @return String 重定向地址
     */
    private static String getRedirectedUrl(CloseableHttpResponse response) {
        String result = EMPTY_STR;
        Header[] hs = response.getHeaders("Location");
        if (hs.length > 0) {
            result = hs[0].getValue();
        }
        return result;
    }
    /**
     * 获得响应实体信息
     *
     * @param response 请求响应
     * @return String 消息实体信息
     */
    private static String getEntityData(CloseableHttpResponse response)
            throws ParseException, IOException {
        String result = EMPTY_STR;
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            result = EntityUtils.toString(entity);
            response.close();
        }
        return result;
    }
}
