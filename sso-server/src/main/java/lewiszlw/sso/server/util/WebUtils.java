package lewiszlw.sso.server.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-31
 */
public class WebUtils {

    public static String generateRedirectUri(String redirectUri, String code, String state) {
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
}
