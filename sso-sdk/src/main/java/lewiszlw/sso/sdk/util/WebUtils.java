package lewiszlw.sso.sdk.util;

import lewiszlw.sso.sdk.config.SsoConfiguration;
import lewiszlw.sso.sdk.constant.Constants;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.util.WebUtils.INCLUDE_CONTEXT_PATH_ATTRIBUTE;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-04
 */
public class WebUtils {

    /**
     * 生成登录url
     */
    public static String genLoginUrL(HttpServletRequest request) {
        SsoConfiguration ssoConfiguration = SsoConfiguration.getInstance();
        // TODO
        // url编码
        return Constants.SSO_LOGIN_URI
                + "?redirect_uri=" + genCallbackUrl(request)
                + "&client_id=" + ssoConfiguration.getClientId();
    }

    /**
     * 生成回调url
     * 包含本次请求url
     */
    public static String genCallbackUrl(HttpServletRequest request) {
        // 加上origin_url 拼接 回调url
        String fullHost = getFullHost(request);
        return fullHost + Constants.RESERVE_CALLBACK_URI
                + "?origin_url=" + getOriginUrl(request);
    }

    public static String getOriginUrl(HttpServletRequest request) {
        // TODO
        // 原始访问url
        return request.getRequestURL().toString();
    }

    public static String getFullHost(HttpServletRequest request) {
        String schema = getSchema(request);
        String host = getHost(request);
        String webContext = getContextPath(request);
        return schema + "://" + host + webContext;
    }

    public static String getHost(HttpServletRequest request) {
        String host = request.getHeader("X-Forwarded-Host");
        if (host == null) {
            host = request.getHeader("Host");
        }
        return host;
    }

    public static String getContextPath(HttpServletRequest request) {
        String contextPath = (String) request.getAttribute(INCLUDE_CONTEXT_PATH_ATTRIBUTE);
        if (contextPath == null) {
            contextPath = request.getContextPath();
        }
        if (StringUtils.isEmpty(contextPath) || "/".equals(contextPath)) {
            // Invalid case, but happens for includes on Jetty: silently adapt it.
            return "";
        }
        return contextPath;
    }

    public static String getSchema(HttpServletRequest request) {
        String schema = request.getHeader("X-Forwarded-Proto");
        if (!"http".equals(schema) && !"https".equals(schema)) {
            // 默认http
            schema = "http";
        }
        return schema;
    }
}
