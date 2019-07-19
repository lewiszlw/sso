package lewiszlw.sso.sdk.util;

import com.alibaba.fastjson.JSONObject;
import lewiszlw.sso.sdk.constant.Constants;
import lewiszlw.sso.sdk.exception.HttpException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.rmi.Remote;
import java.util.Map;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-05
 */
public class HttpUtils {

    private static HttpClient httpClient = HttpClients.createDefault();

    public static <T> T doGet(String url, Class<T> returnType, Map<String, String> headers) {
        HttpGet httpGet = new HttpGet(url);
        // 超时 & headers 设置
        setTimeoutAndHeaders(httpGet, headers);
        // 执行请求
        return execute(httpGet, returnType);
    }

    public static <T> T doPost(String url, String content, Class<T> returnType, Map<String, String> headers) {
        HttpPost httpPost = new HttpPost(url);
        // 超时 & headers 设置
        setTimeoutAndHeaders(httpPost, headers);
        // 设置body
        httpPost.setEntity(new StringEntity(content, "utf-8"));
        // 执行请求
        return execute(httpPost, returnType);
    }

    public static <T> T execute(HttpUriRequest request, Class<T> returnType) {
        try {
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new HttpException("http响应状态码非200");
            }
            HttpEntity entity = response.getEntity();
            String content = entity != null ? EntityUtils.toString(entity, "utf-8") : null;
            return JSONObject.parseObject(content, returnType);
        } catch (IOException e) {
            throw new HttpException("发送http请求异常", e.getCause());
        }
    }

    private static void setTimeoutAndHeaders(HttpRequestBase request, Map<String, String> headers) {
        // 超时设置
        request.setConfig(RequestConfig.custom()
                .setSocketTimeout(Constants.SOCKET_TIMEOUT)
                .setConnectTimeout(Constants.CONNECT_TIMEOUT)
                .build());
        // headers设置
        if (!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, String> header : headers.entrySet()){
                request.addHeader(header.getKey(), header.getValue());
            }
        }
    }
}
