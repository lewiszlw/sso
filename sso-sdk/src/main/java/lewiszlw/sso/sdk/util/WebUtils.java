package lewiszlw.sso.sdk.util;

import lewiszlw.sso.sdk.config.SsoConfiguration;
import lewiszlw.sso.sdk.constant.Constants;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.springframework.web.util.WebUtils.INCLUDE_CONTEXT_PATH_ATTRIBUTE;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-04
 */
public class WebUtils {

    public static final String GMT_TIME_ZONE_ID = "GMT";
    public static final String COOKIE_DATE_FORMAT_STRING = "EEE, dd-MMM-yyyy HH:mm:ss z";

    /**
     * 生成登录url
     */
    public static String genLoginUrL(HttpServletRequest request) throws UnsupportedEncodingException {
        SsoConfiguration ssoConfiguration = SsoConfiguration.getInstance();
        return Constants.SSO_LOGIN_URI
                + "?redirect_uri=" + URLEncoder.encode(genCallbackUrl(request), "utf-8")
                + "&client_id=" + ssoConfiguration.getClientId();
    }

    /**
     * 生成回调url
     */
    public static String genCallbackUrl(HttpServletRequest request) {
        // 加上origin_url 拼接 回调url
        String protocal = getProtocal(request);
        String host = getHost(request);
        String webContext = getContextPath(request);
        // TODO 实际上还要考虑 Nginx rewriteLocation
        return protocal + "://" + host + webContext + Constants.RESERVE_CALLBACK_URI
                + "?origin_url=" + genOriginUrl(request);
    }

    /**
     * 生成本次用户请求的url (origin_url)
     */
    public static String genOriginUrl(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (StringUtils.isEmpty(queryString)) {
            return request.getRequestURL().toString();
        } else {
            return request.getRequestURL().toString() + "?" + queryString;
        }
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

    /**
     * 获取协议 (默认http)
     */
    public static String getProtocal(HttpServletRequest request) {
        String protocal = request.getHeader("X-Forwarded-Proto");
        if (!"http".equals(protocal) && !"https".equals(protocal)) {
            // 默认http
            protocal = "http";
        }
        return protocal;
    }

    /**
     * 设置cookie
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge, boolean httpOnly) {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("=").append(value).append(";");
        sb.append("Max-Age=").append(maxAge).append(";");
        if (maxAge <= 0) {
            sb.append("Expires=Thu Jan 01 08:00:00 CST 1970;");
        } else {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, maxAge);
            Date expires = cal.getTime();

            TimeZone tz = TimeZone.getTimeZone(GMT_TIME_ZONE_ID);
            DateFormat fmt = new SimpleDateFormat(COOKIE_DATE_FORMAT_STRING, Locale.US);
            fmt.setTimeZone(tz);
            sb.append("Expires=" + fmt.format(expires)).append(";");
        }
        sb.append("path=").append("/").append(";");
        if (httpOnly) {
            sb.append("HTTPOnly;");
        }
        response.addHeader("Set-Cookie", sb.toString());
    }

    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
