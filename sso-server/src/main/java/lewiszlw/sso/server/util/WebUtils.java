package lewiszlw.sso.server.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-31
 */
public class WebUtils {

    public static String genOAuthRedirectUri(String redirectUri, String code, String state) {
        StringBuilder sb = new StringBuilder(redirectUri);
        sb.append("?code=").append(code);
        if (!StringUtils.isEmpty(state)) {
            sb.append("&state=").append(state);
        }
        return sb.toString();
    }

    public static boolean isHttpUri(String uri) {
        String regex = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        Pattern pat = Pattern.compile(regex.trim());
        Matcher mat = pat.matcher(uri);
        return mat.matches();
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

            DateFormat fmt = new SimpleDateFormat();
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

    public static String joinCodeToRedirectUri(String redirectUri, String code) {
        if (redirectUri.contains("?")) {
            return redirectUri + "&code=" + code;
        } else {
            return redirectUri + "?code=" + code;
        }
    }
}
