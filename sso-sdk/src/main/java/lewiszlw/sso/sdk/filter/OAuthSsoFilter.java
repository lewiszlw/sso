package lewiszlw.sso.sdk.filter;

import lewiszlw.sso.sdk.constant.Constants;
import lewiszlw.sso.sdk.handler.SsoUriHandler;
import lewiszlw.sso.sdk.handler.impl.*;
import lewiszlw.sso.sdk.util.SsoUtils;
import lewiszlw.sso.sdk.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
public class OAuthSsoFilter extends AbstractSsoFilter {

    @Override
    protected SsoUriHandler dispatch(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        if ("options".equalsIgnoreCase(request.getMethod())) {
            return new FreeAccessSsoUriHandler();
        }
        if (uri.contains(Constants.RESERVE_CALLBACK_URI)) {
            return new CallbackSsoUriHandler();
        }
        if (uri.contains(Constants.RESERVE_LOGOUT_URI)) {
            return new LogoutSsoUriHandler();
        }
        // 判断url是否免登陆
        if (SsoUtils.isUrlFreeAccess(uri, request.getContextPath())) {
            return new FreeAccessSsoUriHandler();
        }
        // 验证登录状态
        if (validateLogin(request)) {
            return new AuthorizedSsoUriHandler();
        } else {
            return new LoginSsoUriHandler();
        }
    }

    /**
     * 验证登录状态
     */
    private boolean validateLogin(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, SsoUtils.genCookieName());
        if (cookie != null) {
            // TODO 暂时认为有cookie就已登录
            return true;
        }
        // cookie 没有则检查 header
        String accessToken = request.getHeader(Constants.ACCESS_TOKEN_HEADER_NAME);
        if (!StringUtils.isEmpty(accessToken)) {
            // TODO 验证token
        }
        return false;
    }

}
