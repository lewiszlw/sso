package lewiszlw.sso.sdk.util;

import lewiszlw.sso.sdk.config.SsoConfiguration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-04
 */
public class SsoUtils {

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();
    /**
     * 判断url是否免登陆
     * true免登陆、false需登录
     */
    public static boolean isUrlFreeAccess(String uri, String contextPath) {
        SsoConfiguration ssoConfiguration = SsoConfiguration.getInstance();

        String uriWithoutContext = uri.substring(contextPath.length());

        // includeUriList 优先级高于 excludeUriList
        // 先检查includeUriList设置
        String includeUriList = ssoConfiguration.getIncludeUriList();
        if (!StringUtils.isEmpty(includeUriList)) {
            String[] uriList = includeUriList.split(",");
            if (isPathMatch(uri, uriList) || isPathMatch(uriWithoutContext, uriList)) {
                return false;
            }
            return true;
        }

        // includeUriList为空才会检查excludeUriList
        String excludeUriList = ssoConfiguration.getExcludeUriList();
        if (!StringUtils.isEmpty(excludeUriList)) {
            String[] uriList = excludeUriList.split(",");
            if (isPathMatch(uri, uriList) || isPathMatch(uriWithoutContext, uriList)) {
                return true;
            }
            return false;
        }
        // 都为空则默认全部url都需要登录
        return false;
    }

    /**
     * 判断是否匹配ant表达式
     * true匹配、false不匹配
     */
    private static boolean isPathMatch(String path, String... patterns) {
        for (String uriPattern : patterns) {
            uriPattern = uriPattern.trim();
            if (antPathMatcher.match(uriPattern, path)) {
                return true;
            }
        }
        return false;
    }

    public static String genCookieName() {
        SsoConfiguration ssoConfiguration = SsoConfiguration.getInstance();
        return ssoConfiguration.getAppSimpleName() + "_sso_token";
    }
}
